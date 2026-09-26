package com.rafaelfq.sistema_gestao_estacionamento.web.dto.mapper;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioCreateDTO;
import com.rafaelfq.sistema_gestao_estacionamento.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

   public static Usuario toUsuario(UsuarioCreateDTO createDTO){
      return new ModelMapper().map(createDTO, Usuario.class);
   }

   public static UsuarioResponseDTO toDto(Usuario usuario){
      String role = usuario.getRole().name().substring("ROLE_".length());
      PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>(){
         @Override
         protected void configure(){
            map().setRole(role);
         }
      };
      ModelMapper mapper = new ModelMapper();
      mapper.addMappings(props);
      return mapper.map(usuario, UsuarioResponseDTO.class);
   }

   public static List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios){
      return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
   }
}
