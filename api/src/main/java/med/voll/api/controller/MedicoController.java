package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoService medicoService;

    @PostMapping()
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroMedico dados){
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping()
    public ResponseEntity <Page<DadosListagemMedico>> listar (@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao){
        var page = medicoService.listagemDeMedico(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping()
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = medicoService.atualizaDadosMedico(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir (@PathVariable Long id){
        medicoService.exluirDadosMedico(id);

        return ResponseEntity.noContent().build();
    }



}
