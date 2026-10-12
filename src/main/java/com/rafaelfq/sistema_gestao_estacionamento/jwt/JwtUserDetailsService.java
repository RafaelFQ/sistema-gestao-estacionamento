package com.rafaelfq.sistema_gestao_estacionamento.jwt;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import com.rafaelfq.sistema_gestao_estacionamento.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

   private final UsuarioService usuarioService;

   public JwtUserDetailsService(UsuarioService usuarioService) {
      this.usuarioService = usuarioService;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario = usuarioService.buscarPorUsername(username);
      return new JwtUserDetais(usuario);
   }

   public JwtToken getTokenAuthenticated(String username){
      Usuario.Role role = usuarioService.buscarRolePorUsername(username);
      return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
   }
}
