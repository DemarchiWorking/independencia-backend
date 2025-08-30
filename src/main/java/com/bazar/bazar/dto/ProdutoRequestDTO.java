package com.bazar.bazar.dto;

import java.util.UUID;

import com.bazar.bazar.model.Categoria;
import com.bazar.bazar.model.Usuario;

public class ProdutoRequestDTO {
    private String nome;
    private double preco;
    private int quantidade;
    private String imagem;
    private String icone;
    private String categoria;
    private UUID categoriaId;
    private boolean ehAutor;
    private Usuario usuario;

    // Add constructors, getters and setters
    // (Lombok can be used for brevity with @Getter @Setter)
    
    // Example constructor
    public ProdutoRequestDTO(String nome, double preco, int quantidade, String imagem, String icone, String categoria, UUID categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.icone = icone;
        this.categoria = categoria;       
        this.categoriaId = categoriaId;

    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public String getImagem() { return imagem; }
    public String getIcone() { return icone; }
    public String getCategoria() { return categoria; }
    public UUID getCategoriaId() { return categoriaId; }

}