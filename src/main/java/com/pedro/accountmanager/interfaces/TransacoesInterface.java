package com.pedro.accountmanager.interfaces;

import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.model.Contas;

import java.math.BigDecimal;
import java.util.List;

public interface TransacoesInterface {
    void realizarDeposito(Contas conta, BigDecimal valorDeposito);

    List<TransacoesDTO> recuperarExtrato(Contas conta);

    BigDecimal limiteDiarioUtilizado(Long idConta);

    void realizarSaque(Contas conta, BigDecimal valorRetirado);
}
