package com.pedro.accountmanager.enums;

public enum TipoContaEnum {
    CORRENTE(1),
    POUPANCA(2),
    SALARIO(3);

    private final int tipoConta;

    TipoContaEnum(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public int getTipoConta() {
        return tipoConta;
    }
}
