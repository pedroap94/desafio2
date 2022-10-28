package com.pedro.accountmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ExtratoPeriodoDTO {
    @NotNull(message = "id da conta não pode ser nulo")
    private Long idConta;
    @NotNull(message = "Data inicial não pode ser nula")
    private LocalDate dataInicial;
    @NotNull(message = "Data final não pode ser nula")
    private LocalDate dataFinal;
}
