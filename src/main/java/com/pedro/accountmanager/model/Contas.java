package com.pedro.accountmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Contas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoas idPessoa;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private boolean flagAtivo = true;
    private Integer tipoConta;
    private final LocalDate dataCriacao = LocalDate.now();

    public Contas(Pessoas idPessoa, BigDecimal saldo, BigDecimal limiteSaqueDiario, Integer tipoConta) {
        this.idPessoa = idPessoa;
        this.saldo = saldo;
        this.limiteSaqueDiario = limiteSaqueDiario;
        this.tipoConta = tipoConta;
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

    public void setIdPessoa(Pessoas idPessoa) {
        this.idPessoa = idPessoa;
    }
}
