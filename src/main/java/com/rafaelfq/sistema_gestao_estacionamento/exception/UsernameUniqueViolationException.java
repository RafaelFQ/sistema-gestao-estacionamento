package com.rafaelfq.sistema_gestao_estacionamento.exception;

public class UsernameUniqueViolationException extends RuntimeException{
   public UsernameUniqueViolationException(String message){
      super(message);
   }
}
