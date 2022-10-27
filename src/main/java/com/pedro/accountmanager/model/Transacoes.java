package com.pedro.accountmanager.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;
    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Contas idConta;
    private BigDecimal valor;
    @NotNull(message = "Data da transação não pode ser nula")
    private LocalDateTime dataTransacao;

    public Transacoes(Contas idConta, BigDecimal valor, LocalDateTime dataTransacao) {
        this.idConta = idConta;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
    }
}
