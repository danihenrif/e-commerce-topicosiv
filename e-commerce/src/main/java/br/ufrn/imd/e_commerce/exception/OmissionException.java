package br.ufrn.imd.e_commerce.exception;

public class OmissionException extends RuntimeException{
    public OmissionException(String message){
        super(message);
    }
}
