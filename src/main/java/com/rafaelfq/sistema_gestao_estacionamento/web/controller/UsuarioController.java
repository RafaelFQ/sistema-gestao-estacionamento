package com.rafaelfq.sistema_gestao_estacionamento.web.controller;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import com.rafaelfq.sistema_gestao_estacionamento.service.UsuarioService;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioCreateDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioResponseDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioSenhaDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

   private final UsuarioService usuarioService;

   public UsuarioController(UsuarioService usuarioService) {
      this.usuarioService = usuarioService;
   }

   @PostMapping
   public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO createDTO){
      Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDTO));
      return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
   }

   @GetMapping("/{id}")
   public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id){
      Usuario user = usuarioService.buscarPorId(id);
      return ResponseEntity.ok(UsuarioMapper.toDto(user));
   }

   @PatchMapping("/{id}")
   public ResponseEntity<Void> updatePassword(@Valid @PathVariable Long id, @RequestBody UsuarioSenhaDTO dto){
      Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
      return ResponseEntity.noContent().build();
   }

   @GetMapping
   public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
      List<Usuario> users = usuarioService.buscarTodos();
      return ResponseEntity.ok(UsuarioMapper.toListDto(users));
   }
}
