package com.rafaelfq.sistema_gestao_estacionamento.service;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import com.rafaelfq.sistema_gestao_estacionamento.exception.EntityNotFoundException;
import com.rafaelfq.sistema_gestao_estacionamento.exception.PasswordInvalidException;
import com.rafaelfq.sistema_gestao_estacionamento.exception.UsernameUniqueViolationException;
import com.rafaelfq.sistema_gestao_estacionamento.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

   private final UsuarioRepository usuarioRepository;

   public UsuarioService(UsuarioRepository usuarioRepository) {
      this.usuarioRepository = usuarioRepository;
   }

   @Transactional
   public Usuario salvar(Usuario usuario){
      try{
         return usuarioRepository.save(usuario);
      } catch(org.springframework.dao.DataIntegrityViolationException ex) {
         throw new UsernameUniqueViolationException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
      }
   }

   @Transactional(readOnly = true)
   public Usuario buscarPorId(Long id){
      return usuarioRepository.findById(id).orElseThrow(
            ()-> new EntityNotFoundException(String.format("Usuário id=%s não encontrado.", id))
      );
   }

   @Transactional
   public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha){
      if(!novaSenha.equals(confirmaSenha)){
         throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
      }
      Usuario user = buscarPorId(id);
      if(!user.getPassword().equals(senhaAtual)){
         throw new PasswordInvalidException("Sua senha não confere.");
      }
      user.setPassword(novaSenha);
      return user;
   }

   public List<Usuario> buscarTodos(){
      return usuarioRepository.findAll();
   }
}
