package com.pedro.accountmanager.facade;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.model.Contas;
import com.pedro.accountmanager.service.ContaInterface;
import com.pedro.accountmanager.service.TransacoesInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ContasFacade {
    private ContaInterface contaService;
    private TransacoesInterface transacoesService;

    public void criarConta(ContaDTO contaDTO) {
        Contas novaConta = contaService.criarConta(contaDTO);
        transacoesService.realizarDeposito(novaConta, novaConta.getSaldo());
    }
}
