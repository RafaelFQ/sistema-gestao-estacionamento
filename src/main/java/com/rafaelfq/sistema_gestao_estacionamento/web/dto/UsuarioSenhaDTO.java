package com.rafaelfq.sistema_gestao_estacionamento.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioSenhaDTO {

   @NotBlank
   @Size(min = 6, max = 6)
   private String senhaAtual;
   @NotBlank
   @Size(min = 6, max = 6)
   private String novaSenha;
   @NotBlank
   @Size(min = 6, max = 6)
   private String confirmaSenha;

   public UsuarioSenhaDTO() {
   }

   public UsuarioSenhaDTO(String senhaAtual, String novaSenha, String confirmaSenha) {
      this.senhaAtual = senhaAtual;
      this.novaSenha = novaSenha;
      this.confirmaSenha = confirmaSenha;
   }

   public String getSenhaAtual() {
      return senhaAtual;
   }

   public void setSenhaAtual(String senhaAtual) {
      this.senhaAtual = senhaAtual;
   }

   public String getNovaSenha() {
      return novaSenha;
   }

   public void setNovaSenha(String novaSenha) {
      this.novaSenha = novaSenha;
   }

   public String getConfirmaSenha() {
      return confirmaSenha;
   }

   public void setConfirmaSenha(String confirmaSenha) {
      this.confirmaSenha = confirmaSenha;
   }

   @Override
   public String toString() {
      return "UsuarioSenhaDTO{" +
            "senhaAtual='" + senhaAtual + '\'' +
            ", novaSenha='" + novaSenha + '\'' +
            ", confirmaSenha='" + confirmaSenha + '\'' +
            '}';
   }
}
