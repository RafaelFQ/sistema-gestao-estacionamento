package com.rafaelfq.sistema_gestao_estacionamento.web.dto;

public class UsuarioCreateDTO {

   private String username;
   private String password;

   public UsuarioCreateDTO() {
   }

   public UsuarioCreateDTO(String username, String password) {
      this.username = username;
      this.password = password;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "UsuarioCreateDTO{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
   }
}
