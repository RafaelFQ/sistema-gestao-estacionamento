package com.rafaelfq.sistema_gestao_estacionamento.web.controller;

import com.rafaelfq.sistema_gestao_estacionamento.jwt.JwtToken;
import com.rafaelfq.sistema_gestao_estacionamento.jwt.JwtUserDetailsService;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioLoginDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {

   private final JwtUserDetailsService detailsService;
   private final AuthenticationManager authenticationManager;

   public AutenticacaoController(JwtUserDetailsService detailsService, AuthenticationManager authenticationManager) {
      this.detailsService = detailsService;
      this.authenticationManager = authenticationManager;
   }

   @PostMapping("/auth")
   public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDTO dto, HttpServletRequest request){
      log.info("Processo de autenticação pelo login{}", dto.getUsername());
      try{
         UsernamePasswordAuthenticationToken authenticationToken =
               new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

         authenticationManager.authenticate(authenticationToken);

         JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());

         return ResponseEntity.ok(token);
      } catch (AuthenticationException ex){
         log.warn("Bad Credentials from username '{}'", dto.getUsername());
      }
      return ResponseEntity
            .badRequest()
            .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
   }
}
