package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Contas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Contas, Long> {
    Optional<Contas> findByIdContaAndFlagAtivoTrue(Long id);

    @Modifying
    @Query(value = "UPDATE contas SET flag_ativo = false where id_conta = :id", nativeQuery = true)
    int updateFlagAtivoFalse(Long id);
}
