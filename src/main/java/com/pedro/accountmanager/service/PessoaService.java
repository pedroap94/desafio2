package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.PessoaDTO;
import com.pedro.accountmanager.exception.PessoaException;
import com.pedro.accountmanager.model.Pessoas;
import com.pedro.accountmanager.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PessoaService {
    private PessoaRepository pessoaRepository;

    public Pessoas cadastrarPessoa(PessoaDTO pessoaDTO) {
        Pessoas pessoa = pessoaRepository.findByNome(pessoaDTO.getNome());
        if (pessoa == null) {
            ModelMapper modelMapper = new ModelMapper();
            return pessoaRepository.save(modelMapper.map(pessoaDTO, Pessoas.class));
        }
        log.error(String.format("Pessoa já existe no banco de dados. Nome: %s", pessoaDTO.getNome()));
        throw new PessoaException("Pessoa já existe no banco de dados");
    }
}
