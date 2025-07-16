package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    AgendaDeConsultas agenda;

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var consulta = agenda.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharConsulta(@PathVariable Long id){
        var dadosConsulta = consultaRepository.getReferenceById(id);
        System.out.println(dadosConsulta);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(dadosConsulta));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
