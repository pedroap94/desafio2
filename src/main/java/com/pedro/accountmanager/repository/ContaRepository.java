package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Contas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Contas, Long> {
}
