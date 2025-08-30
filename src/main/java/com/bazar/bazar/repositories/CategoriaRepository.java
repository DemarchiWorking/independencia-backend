package com.bazar.bazar.repositories;

import com.bazar.bazar.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository // Indica que é um componente de repositório do Spring Data JPA
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
        Optional<Categoria> findByNomeIgnoreCase(String nome);
}
