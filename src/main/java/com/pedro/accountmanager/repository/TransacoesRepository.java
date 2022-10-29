package com.pedro.accountmanager.repository;

import com.pedro.accountmanager.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

    /**
     * Buscar transacoes realizadas em certa data
     * @param idConta - id da conta bancária
     * @param localDate - data que deve ser realizada a busca de transacoes
     * @return {@literal List<Transacoes>} - lista contendo transacoes de uma conta bancária na data especificada
     */
    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :idConta AND DATE(data_transacao) = :localDate AND VALOR < 0", nativeQuery = true)
    List<Transacoes> findTransacoesByData(Long idConta, LocalDate localDate);

    /**
     * Buscar todas as transacoes realizadas por uma conta bancária
     * @param idConta - id da conta bancária
     * @return {@literal List<Transacoes>} - lista contendo todas as transações de uma conta bancária
     */
    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :idConta", nativeQuery = true)
    List<Transacoes> findAllByIdConta(Long idConta);

    /**
     * Buscar transacoes de conta bancária realizadas em período estabelecido
     * @param idConta - id da conta bancária
     * @param dataInicial - data inicial das transacoes
     * @param dataFinal - data final das transacoes
     * @return {@literal List<Transacoes>} - lista contendo transacoes de conta bancária dentro do período estabelecido
     */
    @Query(value = "SELECT * FROM transacoes WHERE id_conta = :idConta AND DATE(data_transacao) >= :dataInicial AND DATE(data_transacao) <= :dataFinal", nativeQuery = true)
    List<Transacoes> findTransacoesPeriodo(Long idConta, LocalDate dataInicial, LocalDate dataFinal);
}
