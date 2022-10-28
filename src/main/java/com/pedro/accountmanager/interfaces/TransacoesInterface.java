package com.pedro.accountmanager.interfaces;

import com.pedro.accountmanager.dto.ExtratoPeriodoDTO;
import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.model.Contas;

import java.math.BigDecimal;
import java.util.List;

public interface TransacoesInterface {
    void realizarDeposito(Contas conta, BigDecimal valorDeposito);

    List<TransacoesDTO> recuperarExtrato(Long idConta);

    BigDecimal limiteDiarioUtilizado(Long idConta);

    void realizarSaque(Contas conta, BigDecimal valorRetirado);

    List<TransacoesDTO> recuperarExtratoPeriodo(ExtratoPeriodoDTO extratoPeriodoDTO);
}
