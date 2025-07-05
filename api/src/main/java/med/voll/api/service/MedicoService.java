package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;


    public Page<DadosListagemMedico> listagemDeMedico(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedico::new);
    }

    @Transactional
    public Medico atualizaDadosMedico(DadosAtualizacaoMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return medico;
    }

    @Transactional
    public void exluirDadosMedico(Long id) {
//        exclui os dados do banco de dados
//        medicoRepository.deleteById(id);

//        executa uma exclusão lógica(não exlcui do banco de dados)
        var medico = medicoRepository.getReferenceById(id);
        medico.inativarMedico();
    }

    public DadosDetalhamentoMedico detalhamentoMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        var dtoMedico= new DadosDetalhamentoMedico(medico);
        return dtoMedico;
    }
}
