package com.rafaelfq.sistema_gestao_estacionamento.web.controller;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import com.rafaelfq.sistema_gestao_estacionamento.service.UsuarioService;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioCreateDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioResponseDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioSenhaDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.mapper.UsuarioMapper;
import com.rafaelfq.sistema_gestao_estacionamento.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um usuário")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

   private final UsuarioService usuarioService;

   public UsuarioController(UsuarioService usuarioService) {
      this.usuarioService = usuarioService;
   }

   @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário",
   responses = {
         @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
               content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
         @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema",
               content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
         @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos",
               content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
      })
   @PostMapping
   public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO createDTO){
      Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDTO));
      return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
   }

   @Operation(summary = "Recuperar um usuário pelo id", description = "Recuperar um usuário pelo id",
         responses = {
               @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
               @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
         })
   @GetMapping("/{id}")
   public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id){
      Usuario user = usuarioService.buscarPorId(id);
      return ResponseEntity.ok(UsuarioMapper.toDto(user));
   }

   @Operation(summary = "Atualizar senha", description = "Atualizar senha",
         responses = {
               @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
               @ApiResponse(responseCode = "400", description = "Senha não confere",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
               @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
         })
   @PatchMapping("/{id}")
   public ResponseEntity<Void> updatePassword(@Valid @PathVariable Long id, @RequestBody UsuarioSenhaDTO dto){
      Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
      return ResponseEntity.noContent().build();
   }

   @Operation(summary = "Listar todos os usuários", description = "Listar todos os usuários cadastrados",
         responses = {
               @ApiResponse(responseCode = "200", description = "Listar com todos os usuários cadastrados",
                     content = @Content(mediaType = "application/json",
                     array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDTO.class))))
         })
   @GetMapping
   public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
      List<Usuario> users = usuarioService.buscarTodos();
      return ResponseEntity.ok(UsuarioMapper.toListDto(users));
   }
}
