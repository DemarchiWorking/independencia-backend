package com.bazar.bazar.controller;


import com.bazar.bazar.model.Categoria;
import com.bazar.bazar.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // Marca a classe como um controlador REST
@RequestMapping("/api/categorias") // Define o caminho base para todos os endpoints deste controlador
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria novaCategoria = categoriaService.createCategoria(categoria);
            return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED); // 201 Created
        } catch (RuntimeException e) {
            // Ex: "Já existe uma categoria com este nome: ..."
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable UUID id) {
        return categoriaService.getCategoriaById(id)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK)) // 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
    }


    @GetMapping("")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        if (categorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(categorias, HttpStatus.OK); // 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable UUID id, @RequestBody Categoria categoriaAtualizada) {
        try {
            Categoria categoria = categoriaService.updateCategoria(id, categoriaAtualizada);
            return new ResponseEntity<>(categoria, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable UUID id) {
        try {
            categoriaService.deleteCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Categoria>> searchCategoriasByName(@RequestParam String nome) {
        List<Categoria> categorias = categoriaService.getCategoriasByNome(nome);
        if (categorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(categorias, HttpStatus.OK); // 200 OK
    }
}