package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}
