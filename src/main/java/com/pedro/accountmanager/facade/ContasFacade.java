package com.pedro.accountmanager.facade;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.dto.SaqueOuDepositoDTO;
import com.pedro.accountmanager.exception.ContaException;
import com.pedro.accountmanager.interfaces.ContaInterface;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import com.pedro.accountmanager.model.Contas;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
@Slf4j
public class ContasFacade {
    private ContaInterface contaService;
    private TransacoesInterface transacoesService;

    public void criarConta(ContaDTO contaDTO) {
        Contas novaConta = contaService.criarConta(contaDTO);
        transacoesService.realizarDeposito(novaConta, novaConta.getSaldo());
    }

    public void depositar(SaqueOuDepositoDTO saqueOuDepositoDTO) {
        try {
            Contas contas = contaService.depositoConta(saqueOuDepositoDTO.getIdConta(), saqueOuDepositoDTO.getValor());
            transacoesService.realizarDeposito(contas, saqueOuDepositoDTO.getValor());
        } catch (Exception e) {
            log.error("Falha ao depositar. Id conta: {}", saqueOuDepositoDTO.getIdConta());
            throw e;
        }
    }

    public void sacar(SaqueOuDepositoDTO saqueOuDepositoDTO) {
        BigDecimal valorDiarioUtilizado = transacoesService.limiteDiarioUtilizado(saqueOuDepositoDTO.getIdConta());
        try {
            Contas conta = contaService.saqueConta(saqueOuDepositoDTO.getIdConta(), saqueOuDepositoDTO.getValor(), valorDiarioUtilizado);
            transacoesService.realizarSaque(conta, saqueOuDepositoDTO.getValor());
        } catch (ContaException e) {
            log.error("Falha ao realizar saque. Id conta: {}", saqueOuDepositoDTO.getIdConta());
            throw e;
        }

    }
}
