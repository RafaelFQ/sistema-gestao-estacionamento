package com.rafaelfq.sistema_gestao_estacionamento.repository;

import com.rafaelfq.sistema_gestao_estacionamento.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}