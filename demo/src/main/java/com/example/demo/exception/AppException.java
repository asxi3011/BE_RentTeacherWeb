package com.example.demo.exception;

public class AppException extends RuntimeException{
    private ErrorApp error;

    public AppException(ErrorApp error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorApp getError() {
        return error;
    }

    public void setError(ErrorApp error) {
        this.error = error;
    }
}
