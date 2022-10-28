package com.pedro.accountmanager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaqueOuDepositoDTO {
    private Long idConta;
    private BigDecimal valor;
}
