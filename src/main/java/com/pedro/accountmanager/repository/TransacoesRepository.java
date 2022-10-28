package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :id AND DATE(data_transacao) = :localDate AND VALOR < 0", nativeQuery = true)
    List<Transacoes> findLimiteDiarioUtilizado(Long id, LocalDate localDate);
}
