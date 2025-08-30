package com.bazar.bazar.repositories;

import com.bazar.bazar.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    // JpaRepository já fornece os métodos básicos de CRUD (save, findById, findAll, delete, etc.).
    // Caso precise de métodos de busca personalizados, você pode declará-los aqui.
    // Exemplo:
    //List<Endereco> findByUsuarioId(UUID usuarioId);
    Optional<Endereco> findByUsuarioId(UUID usuarioId);

}