package br.ufrn.imd.e_commerce.exception;

public class CrashException extends RuntimeException{
    public CrashException(String message){
        super(message);
    }
}
