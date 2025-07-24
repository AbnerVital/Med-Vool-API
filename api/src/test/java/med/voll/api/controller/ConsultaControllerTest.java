package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informações estão validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.ORTOPEDIA;

        var medico = criarMedico();
        var paciente = criarPaciente();
        var consulta = criarConsulta(medico, paciente, data);

        when(agendaDeConsultas.agendar(any(DadosAgendamentoConsulta.class)))
                .thenReturn(consulta);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta(medico.getId(), paciente.getId(), data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                new DadosDetalhamentoConsulta(consulta)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private Medico criarMedico() {
        var endereco = new Endereco(
                "Avenida Central",
                "Bloco B",
                "100",
                "Centro",
                "70700000",
                "Brasilia",
                "DF"
        );

        return new Medico(
                2L,
                "Marina Silva",
                "marina.silva@voll.med",
                "98988776644",
                "982134",
                Especialidade.ORTOPEDIA,
                false,
                endereco
        );
    }

    private Paciente criarPaciente() {
        var endereco = new Endereco(
                "Rua Bahia",
                "Sala 5",
                "99",
                "Pituba",
                "41830000",
                "Salvador",
                "BA"
        );

        return new Paciente(
                1L,
                "abner vital gomes chaves",
                "bruno.lima@example.com",
                "98988889999",
                "112.233.445-56",
                endereco,
                false
        );
    }

    private Consulta criarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        return new Consulta(10L, medico, paciente, data, null);
    }




}