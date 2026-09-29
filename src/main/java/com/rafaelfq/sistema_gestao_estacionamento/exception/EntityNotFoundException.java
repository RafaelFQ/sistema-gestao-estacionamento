package com.rafaelfq.sistema_gestao_estacionamento.exception;

public class EntityNotFoundException extends RuntimeException{
   public EntityNotFoundException(String message) {
      super(message);
   }
}
