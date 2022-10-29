package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
    /**
     * Encontrar Pessoas através do parâmetro nome
     * @param nome — parâmetro para busca de pessoa em banco de dados
     * @return Pessoas — entidade contendo dados de Pessoas
     */
    Pessoas findByNome(String nome);
}
