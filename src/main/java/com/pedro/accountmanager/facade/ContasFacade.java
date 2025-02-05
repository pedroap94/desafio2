package com.pedro.accountmanager.facade;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.dto.OperacaoDTO;
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

    /**
     * Criar conta bancária
     * @param contaDTO — objeto de dados da conta bancária a ser criada
     */

    public void criarConta(ContaDTO contaDTO) {
        Contas novaConta = contaService.criarConta(contaDTO);
        transacoesService.realizarDeposito(novaConta, novaConta.getSaldo());
    }

    /**
     * Depositar valor em conta bancária existente
     * @param operacaoDTO — objeto contendo dados de conta bancária para realizar depósito e valor do depósito.
     */
    public void depositar(OperacaoDTO operacaoDTO) {
        try {
            Contas contas = contaService.depositoConta(operacaoDTO.getIdConta(), operacaoDTO.getValor());
            transacoesService.realizarDeposito(contas, operacaoDTO.getValor());
        } catch (Exception e) {
            log.error("Falha ao depositar. Id conta: {}", operacaoDTO.getIdConta());
            throw e;
        }
    }

    /**
     * Realizar operação de saque em conta bancária existente
     * @param operacaoDTO - objeto contendo dados de conta bancária para realizar saque e valor do saque.
     */
    public void sacar(OperacaoDTO operacaoDTO) {
        BigDecimal valorDiarioUtilizado = transacoesService.limiteDiarioUtilizado(operacaoDTO.getIdConta());
        try {
            Contas conta = contaService.saqueConta(operacaoDTO.getIdConta(), operacaoDTO.getValor(), valorDiarioUtilizado);
            transacoesService.realizarSaque(conta, operacaoDTO.getValor());
        } catch (ContaException e) {
            log.error("Falha ao realizar saque. Id conta: {}", operacaoDTO.getIdConta());
            throw e;
        }

    }
}
