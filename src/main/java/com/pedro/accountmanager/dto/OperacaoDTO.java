package com.pedro.accountmanager.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OperacaoDTO {
    @NotNull(message = "idConta não pode ser nulo")
    private Long idConta;
    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;
}
