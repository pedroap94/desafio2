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
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Cadastrar nova Pessoa no sistema
     * @param pessoaDTO — Objeto contendo dados de cadastro de nova pessoa
     * @exception PessoaException — exceção propagada em caso de a pessoa a ser cadastrada já existir no sistema
     */
    public void cadastrarPessoa(PessoaDTO pessoaDTO) {
        if (verificarExistenciaCadastro(pessoaDTO)) {
            pessoaRepository.save(modelMapper.map(pessoaDTO, Pessoas.class));
            return;
        }
        log.error("Pessoa já existe no banco de dados. Nome: {}", pessoaDTO.getNome());
        throw new PessoaException("Pessoa já existe no banco de dados");
    }

    private boolean verificarExistenciaCadastro(PessoaDTO pessoaDTO) {
        Pessoas pessoa = pessoaRepository.findByNome(pessoaDTO.getNome());
        return pessoa == null;
    }
}
