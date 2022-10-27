package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.model.Contas;

import java.math.BigDecimal;
import java.util.List;

public interface TransacoesInterface {
    public void realizarDeposito(Contas conta, BigDecimal valorDeposito);

    public List<TransacoesDTO> recuperarExtrato(Contas conta);
}
