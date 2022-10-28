package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :idConta AND DATE(data_transacao) = :localDate AND VALOR < 0", nativeQuery = true)
    List<Transacoes> findLimiteDiarioUtilizado(Long idConta, LocalDate localDate);

    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :idConta", nativeQuery = true)
    List<Transacoes> findAllByIdConta(Long idConta);

    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :idConta AND DATE(data_transacao) >= :dataInicial AND DATE(data_transacao) <= :dataFinal", nativeQuery = true)
    List<Transacoes> findTransacoesPeriodo(Long idConta, LocalDate dataInicial, LocalDate dataFinal);
}
