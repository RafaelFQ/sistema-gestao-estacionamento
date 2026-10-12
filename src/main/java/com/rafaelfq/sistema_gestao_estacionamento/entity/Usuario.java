package com.rafaelfq.sistema_gestao_estacionamento.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_usuarios")
   private Long id;

   @Column(name = "username", nullable = false, unique = true, length = 100)
   private String username;

   @Column(name = "password", nullable = false, length = 200)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(name = "role", nullable = false, length = 25)
   private Role role = Role.ROLE_CLIENTE;

   @CreatedDate
   @Column(name = "data_criacao")
   private LocalDateTime dataCriacao;

   @LastModifiedDate
   @Column(name = "data_modificacao")
   private LocalDateTime dataModificacao;

   @CreatedBy
   @Column(name = "criado_por")
   private String criadoPor;

   @LastModifiedBy
   @Column(name = "modificado_por")
   private String modificadoPor;

   public enum Role{
      ROLE_ADMIN, ROLE_CLIENTE
   }

   public Usuario() {
   }

   public Usuario(String username, String password, Role role) {
      this.username = username;
      this.password = password;
      this.role = role;
   }

   public Long getId() {
      return id;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public Role getRole() {
      return role;
   }

   public LocalDateTime getDataCriacao() {
      return dataCriacao;
   }

   public LocalDateTime getDataModificacao() {
      return dataModificacao;
   }

   public String getCriadoPor() {
      return criadoPor;
   }

   public String getModificadoPor() {
      return modificadoPor;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;

      Usuario usuario = (Usuario) o;
      return id.equals(usuario.id);
   }

   @Override
   public int hashCode() {
      return id.hashCode();
   }

   @Override
   public String toString() {
      return "Usuario{" +
            "id=" + id +
            '}';
   }
}
