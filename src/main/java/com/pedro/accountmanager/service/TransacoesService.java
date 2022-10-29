package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.ExtratoPeriodoDTO;
import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import com.pedro.accountmanager.model.Contas;
import com.pedro.accountmanager.model.Transacoes;
import com.pedro.accountmanager.repository.TransacoesRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransacoesService implements TransacoesInterface {
    private TransacoesRepository transacoesRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Criar registro de transação para depósito bancário em conta
     * @param conta - objeto Contas contendo dados da conta a realizar depósito
     * @param valorDeposito — valor a ser depositado em conta bancária
     */
    @Override
    public void realizarDeposito(Contas conta, BigDecimal valorDeposito) {
        gerarTransacao(conta, valorDeposito);
    }

    /**
     * Recuperar extrato bancário total de conta bancária
     * @param idConta - id da conta bancária na qual será recuperado o extrato
     * @return {@literal List<TransacoesDTO>} — lista de objetos com dados de extrato
     */
    @Override
    public List<TransacoesDTO> recuperarExtrato(Long idConta) {
        List<Transacoes> transacoes = transacoesRepository.findAllByIdConta(idConta);
        return transacoes.stream().map(transacao -> modelMapper.map(transacao, TransacoesDTO.class)).toList();
    }

    /**
     * Limite diário utilizado de uma conta bancária
     * @param idConta — id da conta bancária a ser recuperado o limite diário utilizado
     * @return BigDecimal — valor do limite diário já utilizado
     */
    public BigDecimal limiteDiarioUtilizado(Long idConta) {
        List<Transacoes> transacoes = transacoesRepository.findTransacoesByData(idConta, LocalDate.now());
        return transacoes.stream().map(Transacoes::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Gera extrato contendo dados de saque bancário
     * @param conta — objeto Contas contendo os dados da conta bancária que realizou a operação
     * @param valorRetirado - valor que foi retirado da conta bancária
     */
    @Override
    public void realizarSaque(Contas conta, BigDecimal valorRetirado) {
        gerarTransacao(conta, valorRetirado.negate());
    }

    /**
     * Recuperar extrato em período desejado
     * @param extratoPeriodoDTO — objeto contendo dados de conta bancária e período a ser recuperado extrato
     * @return {@literal List<TransacoesDTO>} — lista de objetos com dados de extrato
     */
    @Override
    public List<TransacoesDTO> recuperarExtratoPeriodo(ExtratoPeriodoDTO extratoPeriodoDTO) {
        return transacoesRepository.findTransacoesPeriodo(
                extratoPeriodoDTO.getIdConta(),
                extratoPeriodoDTO.getDataInicial(),
                extratoPeriodoDTO.getDataFinal()
        ).stream().map(transacao -> modelMapper.map(transacao, TransacoesDTO.class)).toList();
    }

    private void gerarTransacao(Contas conta, BigDecimal valor) {
        transacoesRepository.save(new Transacoes(conta, valor));
    }
}
