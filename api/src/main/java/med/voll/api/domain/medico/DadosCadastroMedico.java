package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank //valida se o campo está nulo ou ou vázio
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //valida se o campo tem 4-6 digitos
        String crm,

        @NotNull
        Especialidade especialidade,

        //é necessário colocar o @valid para o Spring poder validar o outro DTO.
        @NotNull @Valid DadosEndereco endereco


) {

}
