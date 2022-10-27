package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import com.pedro.accountmanager.model.Contas;
import com.pedro.accountmanager.model.Transacoes;
import com.pedro.accountmanager.repository.TransacoesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TransacoesService implements TransacoesInterface {
    private TransacoesRepository transacoesRepository;
    @Override
    public void realizarDeposito(Contas conta, BigDecimal valorDeposito) {
        transacoesRepository.save(new Transacoes(conta, valorDeposito));
    }

    @Override
    public List<TransacoesDTO> recuperarExtrato(Contas conta) {
        return null;
    }
}
