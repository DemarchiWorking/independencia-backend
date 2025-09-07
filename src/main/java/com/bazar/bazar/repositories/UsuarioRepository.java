package com.bazar.bazar.repositories;

import com.bazar.bazar.model.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    
    Page<Usuario> findByNomeContainingIgnoreCaseAndLojaTrue(String nome, Pageable pageable);

    Page<Usuario> findByLojaTrue(Pageable pageable);

}
