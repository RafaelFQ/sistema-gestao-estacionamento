package com.rafaelfq.sistema_gestao_estacionamento.service;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
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
      return usuarioRepository.save(usuario);
   }

   @Transactional(readOnly = true)
   public Usuario buscarPorId(Long id){
      return usuarioRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Usuário não encontrado.")
      );
   }

   @Transactional
   public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha){
      if(!novaSenha.equals(confirmaSenha)){
         throw new RuntimeException("Nova senha não confere com confirmação de senha.");
      }
      Usuario user = buscarPorId(id);
      if(!user.getPassword().equals(senhaAtual)){
         throw new RuntimeException("Sua senha não confere.");
      }
      user.setPassword(novaSenha);
      return user;
   }

   public List<Usuario> buscarTodos(){
      return usuarioRepository.findAll();
   }
}
