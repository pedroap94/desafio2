package com.pedro.accountmanager.exception;

/**
 * Classe de exceção relacionada a Conta Bancária
 */
public class ContaException extends RuntimeException {

    /**
     * Exceção para conta bancária
     * @param message — mensagem a ser propagada durante exceção
     */
    public ContaException(String message) {
        super(message);
    }
}
