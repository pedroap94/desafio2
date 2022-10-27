package com.pedro.accountmanager.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;
    @NotNull(message = "Cpf não pode ser nulo")
    private String cpf;
    @NotNull(message = "Data de nascimento não pode ser nula")
    private LocalDate dataNascimento;

    public Pessoas(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}
