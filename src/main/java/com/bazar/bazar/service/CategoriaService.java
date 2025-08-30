package com.bazar.bazar.service;

import com.bazar.bazar.model.Categoria;
import com.bazar.bazar.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importe para controle de transação

import java.time.LocalDateTime; // Se você tiver data de criação para Categoria
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // Indica que esta classe é um componente de serviço do Spring
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired // Injeção de dependência do CategoriaRepository
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional // Garante que a operação seja transacional
    public Categoria createCategoria(Categoria categoria) {
        // Validação para evitar nomes duplicados, ignorando maiúsculas/minúsculas
        if (categoriaRepository.findByNomeIgnoreCase(categoria.getNome()).isPresent()) {
            throw new RuntimeException("Já existe uma categoria com este nome: " + categoria.getNome());
        }

        return categoriaRepository.save(categoria);
    }


    @Transactional(readOnly = true) // Apenas leitura, otimiza performance
    public Optional<Categoria> getCategoriaById(UUID id) {
        return categoriaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria updateCategoria(UUID id, Categoria categoriaAtualizada) {
        return categoriaRepository.findById(id).map(categoriaExistente -> {
            categoriaExistente.setNome(categoriaAtualizada.getNome());
            categoriaExistente.setIcone(categoriaAtualizada.getIcone()); // Atualiza também a foto
            return categoriaRepository.save(categoriaExistente);
        }).orElseThrow(() -> new RuntimeException("Categoria com ID " + id + " não encontrada."));
    }


    @Transactional
    public void deleteCategoria(UUID id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria com ID " + id + " não encontrada para exclusão.");
        }
        categoriaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Categoria> getCategoriasByNome(String nome) {
        return categoriaRepository.findByNomeIgnoreCase(nome)
                .map(List::of) // Se encontrar, retorna uma lista com ela
                .orElse(List.of()); // Se não encontrar, retorna uma lista vazia

    }
}