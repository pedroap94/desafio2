package com.pedro.accountmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PessoaDTO {
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;
    @NotNull(message = "CPF não pode ser nulo")
    private String cpf;
    @NotNull(message = "Data de nascimento não pode ser nula")
    private LocalDate dataNascimento;
}
