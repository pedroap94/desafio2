package com.pedro.accountmanager.service;

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

    @Override
    public void realizarDeposito(Contas conta, BigDecimal valorDeposito) {
        gerarTransacao(conta, valorDeposito);
    }

    @Override
    public List<TransacoesDTO> recuperarExtrato(Long idConta) {
        List<Transacoes> transacoes = transacoesRepository.findAllByIdConta(idConta);
        return transacoes.stream().map(transacao -> modelMapper.map(transacao, TransacoesDTO.class)).toList();
    }

    public BigDecimal limiteDiarioUtilizado(Long idConta) {
        List<Transacoes> transacoes = transacoesRepository.findLimiteDiarioUtilizado(idConta, LocalDate.now());
        return transacoes.stream().map(Transacoes::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void realizarSaque(Contas conta, BigDecimal valorRetirado) {
        gerarTransacao(conta, valorRetirado.negate());
    }

    private void gerarTransacao(Contas conta, BigDecimal valor) {
        transacoesRepository.save(new Transacoes(conta, valor));
    }
}
