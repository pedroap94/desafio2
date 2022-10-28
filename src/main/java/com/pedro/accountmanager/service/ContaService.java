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

import javax.transaction.Transactional;
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
        Contas contas = findContasAtivas(id);
        if (contas != null) {
            contas.setSaldo(contas.getSaldo().add(valorDeposito));
            return atualizarContas(contas);
        }
        log.error("Conta id {} não encontrada, ou inativa", id);
        throw new ContaException("Conta não encontrada");
    }

    @Override
    public BigDecimal saldoConta(Long id) {
        Contas conta = findContasAtivas(id);
        if (conta != null) {
            return conta.getSaldo();
        }
        log.error("Conta id {} não encontrada ou inativa", id);
        throw new ContaException("Conta não encontrada");
    }

    @Override
    public Contas saqueConta(Long id, BigDecimal valorSaque, BigDecimal valorDiarioUtilizado) {
        Contas conta = findContasAtivas(id);
        if (conta != null && conta.getSaldo().compareTo(valorSaque) >= 0
                && (conta.getLimiteSaqueDiario().subtract(valorDiarioUtilizado.abs()).subtract(valorSaque)).compareTo(BigDecimal.ZERO) >= 0) {
            conta.setSaldo(conta.getSaldo().subtract(valorSaque));
            return atualizarContas(conta);
        }
        throw new ContaException("Conta não encontrada, inativa ou com saldo insuficiente");
    }

    @Transactional
    @Override
    public void bloquearConta(Long id) {
        int contasModificadas = contaRepository.updateFlagAtivoFalse(id);
        if(contasModificadas == 0) {
            throw new ContaException("Nenhuma conta encontrada");
        }
    }

    private Contas findContasAtivas(Long id) {
        return contaRepository.findByIdContaAndFlagAtivoTrue(id).orElse(null);
    }

    private Contas atualizarContas(Contas contas) {
        return contaRepository.save(contas);
    }
}
