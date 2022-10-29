package com.pedro.accountmanager.dto;

import com.pedro.accountmanager.model.Pessoas;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ContaDTO {
    @NotNull(message = "É necessário ter uma pessoa relacionada a conta")
    private Pessoas idPessoa;
    private BigDecimal saldo;
    @NotNull(message = "É necessário definir um limite para saque diário")
    private BigDecimal limiteSaqueDiario;
    private Integer tipoConta;
}
