package med.voll.api.medico;

public record MedicoDTO(String nome,
                        String email,
                        String crm,
                        Especialidade especialidade) {
}
