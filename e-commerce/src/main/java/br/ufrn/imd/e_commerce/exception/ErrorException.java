package br.ufrn.imd.e_commerce.exception;

public class ErrorException extends RuntimeException{
    public ErrorException(String message){
        super(message);
    }
}
