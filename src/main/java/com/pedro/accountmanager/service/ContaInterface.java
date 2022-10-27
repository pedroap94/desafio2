package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.model.Contas;

import java.math.BigDecimal;

public interface ContaInterface {
    public Contas criarConta(ContaDTO contaDTO);

    public void depositoConta(Long id, BigDecimal valorDeposito);

    public BigDecimal saldoConta(Long id);

    public void saqueConta(Long id, BigDecimal valorSaque);

    public void bloquearConta(Long id);
}
