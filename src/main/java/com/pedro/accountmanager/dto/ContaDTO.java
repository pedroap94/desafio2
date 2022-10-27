package com.pedro.accountmanager.dto;

import com.pedro.accountmanager.model.Pessoas;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ContaDTO {
    private Pessoas idPessoa;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private Integer tipoConta;
}
