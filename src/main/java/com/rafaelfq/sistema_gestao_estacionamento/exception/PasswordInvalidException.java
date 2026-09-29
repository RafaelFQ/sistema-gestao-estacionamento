package com.rafaelfq.sistema_gestao_estacionamento.exception;

public class PasswordInvalidException extends RuntimeException {
   public PasswordInvalidException(String message) {
      super(message);
   }
}
