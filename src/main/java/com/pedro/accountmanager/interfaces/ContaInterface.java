package com.pedro.accountmanager.interfaces;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.model.Contas;

import java.math.BigDecimal;

public interface ContaInterface {
    Contas criarConta(ContaDTO contaDTO);

    Contas depositoConta(Long id, BigDecimal valorDeposito);

    BigDecimal saldoConta(Long id);

    Contas saqueConta(Long id, BigDecimal valorSaque, BigDecimal valorDiarioUtilizado);

    void bloquearConta(Long id);
}
