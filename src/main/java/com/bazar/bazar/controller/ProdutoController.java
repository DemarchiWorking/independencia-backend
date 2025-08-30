package com.bazar.bazar.controller;

import com.bazar.bazar.dto.ProdutoRequestDTO;
import com.bazar.bazar.dto.ProdutoResponseDTO;
import com.bazar.bazar.model.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;

// src/main/java/com/bazar/api/bazar/controller/ProdutoController.java

import com.bazar.bazar.model.Produto;
import com.bazar.bazar.model.Usuario;
import com.bazar.bazar.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/produtos") // Define o caminho base para todos os endpoints neste controlador
public class ProdutoController {

    private final ProdutoService produtoService;

    
    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Endpoint para criar um novo produto
    // POST http://localhost:8081/api/produtos
    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoRequestDTO requestDTO) {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Produto novoProduto = new Produto();
        novoProduto.setNome(requestDTO.getNome());
        novoProduto.setPreco(requestDTO.getPreco());
        novoProduto.setQuantidade(requestDTO.getQuantidade());
        novoProduto.setImagem(requestDTO.getImagem());
        novoProduto.setIcone(requestDTO.getIcone());
        novoProduto.setCategoria(requestDTO.getCategoria());
        novoProduto.setCategoriaId(requestDTO.getCategoriaId());
        novoProduto.setDataCriacao(LocalDateTime.now());

        novoProduto.setAutorId(usuarioLogado.getId()); // Usando o id do autor
        novoProduto = produtoService.createProduto(novoProduto);
        System.out.println("Produto criado: " + novoProduto.getCategoriaId());
        System.out.println("Produto criado. Usuário ID: " + usuarioLogado.getId());

        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // Endpoint para buscar um produto pelo ID
    // GET http://localhost:8081/api/produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoById(@PathVariable UUID id) {
        return produtoService.getProdutoById(id)
                .map(produto -> {
                    boolean ehAutor = false; // Valor padrão: false
                    
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    
                    if (principal instanceof Usuario) {
                        Usuario usuarioLogado = (Usuario) principal;
                        if (produto.getAutorId() != null) {
                            ehAutor = produto.getAutorId().equals(usuarioLogado.getId());
                        }
                    }
                    
                    ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(produto, ehAutor);
                    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para buscar todos os produtos
    // GET http://localhost:8081/api/produtos
    @GetMapping
    public ResponseEntity<Page<Produto>> getAllProdutos(
        @PageableDefault(page = 0, size = 12, sort = "nome") Pageable pageable) {
        
        Page<Produto> produtosPage = produtoService.getAllProdutos(pageable);
        return new ResponseEntity<>(produtosPage, HttpStatus.OK);

    }
    @GetMapping("/filtro")
    public ResponseEntity<Page<Produto>> getAllProdutosFiltro(
            @RequestParam(required = false) UUID categoriaId,
            @PageableDefault(page = 0, size = 12, sort = "nome") Pageable pageable) {
        
        Page<Produto> produtosPage;

        if (categoriaId != null) {
            // Se o categoriaId for fornecido, filtra por ele
            produtosPage = produtoService.getProdutosByCategoria(categoriaId, pageable);
        } else {
            // Caso contrário, retorna todos os produtos
            produtosPage = produtoService.getAllProdutos(pageable);
        }

        return new ResponseEntity<>(produtosPage, HttpStatus.OK);
    }
    // Endpoint para buscar produtos por nome
    // GET http://localhost:8081/api/produtos/search?nome=exemplo
    @GetMapping("/search")
    public ResponseEntity<List<Produto>> searchProdutosByName(@RequestParam String nome) {
        List<Produto> produtos = produtoService.getProdutosByNome(nome);
        if (produtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    @GetMapping("/meus-produtos")
    public ResponseEntity<List<Produto>> getMeusProdutos() {
        List<Produto> meusProdutos = produtoService.getMeusProdutos();
        return ResponseEntity.ok(meusProdutos);
    }
    // Endpoint para atualizar um produto
    // PUT http://localhost:8081/api/produtos/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(
        @PathVariable UUID id, 
        @RequestBody Produto produto) {
        
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        produto.setAutorId(usuarioLogado.getId()); // Assumindo que o getId() retorna um tipo que pode ser convertido para String.

        if (produto.getAutorId() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            Produto produtoAtualizado = produtoService.updateProduto(id, produto, produto.getAutorId());
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable UUID id) {
        try {
            produtoService.deleteProduto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getCategoriasDeProdutos() {
        List<Categoria> categorias = produtoService.getCategoriasDosProdutos();

        if (categorias.isEmpty()) { 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        }
    }

    @PatchMapping("icone/{id}") 
    public ResponseEntity<Produto> updateProdutoIcone(
            @PathVariable UUID id,
            @RequestBody String icone) {
        try {
            Produto produtoAtualizado = produtoService.updateIconeProduto(id, icone);
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    /*
    @Transactional
    public Categoria createCategoria(Categoria categoria) {
        if (produtoRepository.findByNomeIgnoreCase(categoria.getNome()).isPresent()) {
            throw new RuntimeException("Já existe uma categoria com este nome: " + categoria.getNome());
        }
        return categoriaRepository.save(categoria);
    }*/
     //   return produtoService.getCategoria();//.stream() // Pega todos os produtos
                //.filter(produto -> produto.getCa() != null) // Filtra produtos que realmente têm categoria
                //.map(Produto::getCategoria) // Mapeia para pegar apenas o objeto Categoria de cada produto
                //.distinct() // Remove categorias duplicadas (importante!)
                //.collect(Collectors.toList()); // Coleta em uma lista
}
