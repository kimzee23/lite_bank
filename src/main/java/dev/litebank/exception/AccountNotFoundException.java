package dev.litebank.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountNotFound) {
    }
}
