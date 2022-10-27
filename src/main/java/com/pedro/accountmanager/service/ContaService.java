package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.exception.ContaException;
import com.pedro.accountmanager.interfaces.ContaInterface;
import com.pedro.accountmanager.model.Contas;
import com.pedro.accountmanager.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class ContaService implements ContaInterface {
    private ContaRepository contaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Contas criarConta(ContaDTO contaDTO) {
        return contaRepository.save(modelMapper.map(contaDTO, Contas.class));
    }

    @Override
    public Contas depositoConta(Long id, BigDecimal valorDeposito) {
        Contas contas = findContas(id);
        if (contas != null && contas.isFlagAtivo()) {
            contas.setSaldo(contas.getSaldo().add(valorDeposito));
            return atualizarContas(contas);
        }
        log.error("Conta id {} não encontrada, ou inativa", id);
        throw new ContaException("Conta não encontrada");
    }

    @Override
    public BigDecimal saldoConta(Long id) {
        return null;
    }

    @Override
    public void saqueConta(Long id, BigDecimal valorSaque) {

    }

    @Override
    public void bloquearConta(Long id) {

    }

    private Contas findContas(Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    private Contas atualizarContas(Contas contas) {
        return contaRepository.save(contas);
    }
}
