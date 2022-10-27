package com.pedro.accountmanager.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
public class Contas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoas idPessoa;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private boolean flagAtivo;
    private Integer tipoConta;
    @NotNull(message = "Data de criação obrigatória")
    private LocalDate dataCriacao;

    public Contas(Pessoas idPessoa, BigDecimal saldo, BigDecimal limiteSaqueDiario, boolean flagAtivo, Integer tipoConta, LocalDate dataCriacao) {
        this.idPessoa = idPessoa;
        this.saldo = saldo;
        this.limiteSaqueDiario = limiteSaqueDiario;
        this.flagAtivo = flagAtivo;
        this.tipoConta = tipoConta;
        this.dataCriacao = dataCriacao;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public void setFlagAtivo(boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public void setTipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }
}
