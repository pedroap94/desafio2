package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.ContaDTO;
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
public class ContaService implements ContaInterface{
    private ContaRepository contaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Contas criarConta(ContaDTO contaDTO) {
        return contaRepository.save(modelMapper.map(contaDTO, Contas.class));
    }

    @Override
    public void depositoConta(Long id, BigDecimal valorDeposito) {

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
}
