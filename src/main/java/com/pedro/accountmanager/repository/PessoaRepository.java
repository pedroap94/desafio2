package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
    public Pessoas findByNome(String nome);
}
