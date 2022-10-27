package com.pedro.accountmanager.facade;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.dto.DepositoDTO;
import com.pedro.accountmanager.interfaces.ContaInterface;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import com.pedro.accountmanager.model.Contas;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public void depositar(DepositoDTO depositoDTO) {
        try {
            Contas contas = contaService.depositoConta(depositoDTO.getIdConta(), depositoDTO.getValor());
            transacoesService.realizarDeposito(contas, depositoDTO.getValor());
        } catch (Exception e) {
            log.error("Falha ao depositar. Id conta: {}", depositoDTO.getIdConta());
            throw e;
        }

    }
}
