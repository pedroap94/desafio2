package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Contas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Contas, Long> {
    public Optional<Contas> findByIdContaAndFlagAtivoTrue(Long id);
}
