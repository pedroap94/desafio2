package com.pedro.accountmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;
    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Contas idConta;
    private BigDecimal valor;
    private final LocalDateTime dataTransacao = LocalDateTime.now();

    public Transacoes(Contas idConta, BigDecimal valor) {
        this.idConta = idConta;
        this.valor = valor;
    }

    public void setIdConta(Contas idConta) {
        this.idConta = idConta;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
