package med.voll.api.service;

import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;


    public List<DadosListagemMedico> listagemDeMedico() {
        return medicoRepository.findAll().stream()
                .map(DadosListagemMedico::new)
                .sorted(Comparator.comparing(DadosListagemMedico::nome))
                .toList();
    }
}
