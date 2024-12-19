package br.ufrn.imd.store.exception;

public class OmissionException extends RuntimeException{
    public OmissionException(String message){
        super(message);
    }
}
