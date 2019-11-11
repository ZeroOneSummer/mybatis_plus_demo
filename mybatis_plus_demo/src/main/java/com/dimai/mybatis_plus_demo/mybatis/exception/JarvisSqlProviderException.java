package com.dimai.mybatis_plus_demo.mybatis.exception;

public class JarvisSqlProviderException extends RuntimeException {
    public JarvisSqlProviderException(String message) {
        super(message);
    }

    public JarvisSqlProviderException(String message, Throwable t) {
        super(message, t);
    }

    public JarvisSqlProviderException(Throwable t) {
        super(t);
    }
}