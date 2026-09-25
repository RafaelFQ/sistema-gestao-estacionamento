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
   public Usuario editarSenha(Long id, String password){
      Usuario user = buscarPorId(id);
      user.setPassword(password);
      return user;
   }

   public List<Usuario> buscarTodos(){
      return usuarioRepository.findAll();
   }
}
