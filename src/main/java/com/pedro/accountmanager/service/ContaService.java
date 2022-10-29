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

    /**
     * Criar nova conta bancária
     * @param contaDTO — objeto com dados para criação de uma nova conta bancária
     * @return void
     */
    @Override
    public Contas criarConta(ContaDTO contaDTO) {
        return contaRepository.save(modelMapper.map(contaDTO, Contas.class));
    }

    /**
     * Depositar em conta bancária existente
     * @param id - id da conta bancária
     * @param valorDeposito — valor a ser depositado em conta bancária
     * @return void
     */
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

    /**
     * Resgatar saldo em conta bancária existente
     * @param id - id da conta bancária
     * @return BigDecimal — valor da conta bancária
     */
    @Override
    public BigDecimal saldoConta(Long id) {
        Contas conta = findContasAtivas(id);
        if (conta != null) {
            return conta.getSaldo();
        }
        log.error("Conta id {} não encontrada ou inativa", id);
        throw new ContaException("Conta não encontrada");
    }

    /**
     * Realizar saque em conta bancária existente.
     * Essa operação só irá ocorrer caso o valor do saque não ultrapasse o valor limite diário
     * @param id - id da conta bancária
     * @param valorSaque - valor a ser sacado da conta bancária
     * @param valorDiarioUtilizado — valor diário utilizado da conta bancária
     * @return Contas - objeto contendo todos os dados da conta bancária
     * @exception ContaException — lançado em caso de conta nula, com valor de saque superior ao saldo da conta bancária ou saque que excede limite diário de saques.
     */
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

    /**
     * Realizar bloqueio de conta bancária existente
     * @param id - id da conta bancária
     * @exception ContaException — exceção lançada em caso de nenhuma conta bancária ser encontrada
     */
    @Transactional
    @Override
    public void bloquearConta(Long id) {
        int contasModificadas = contaRepository.updateFlagAtivoFalse(id);
        if (contasModificadas == 0) {
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
