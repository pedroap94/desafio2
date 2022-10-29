package com.pedro.accountmanager.exception;

/**
 * Classe de exceção relacionada ao gerenciamento de Pessoas
 */
public class PessoaException extends RuntimeException {
    /**
     * Exceção para Pessoas
     * @param message — mensagem a ser propagada durante exceção
     */
    public PessoaException(String message) {
        super(message);
    }
}
