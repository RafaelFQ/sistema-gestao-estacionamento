package com.rafaelfq.sistema_gestao_estacionamento.jwt;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetais extends User {

   private Usuario usuario;

   public JwtUserDetais(Usuario usuario) {
      super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
      this.usuario = usuario;
   }

   public Long getId(){
      return this.usuario.getId();
   }

   private String getRole(){
      return this.usuario.getRole().name();
   }
}
