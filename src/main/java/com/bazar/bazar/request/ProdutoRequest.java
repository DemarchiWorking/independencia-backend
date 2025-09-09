package com.bazar.bazar.request;


import java.util.UUID;

import com.bazar.bazar.model.Usuario;

import lombok.Getter;

public class ProdutoRequest {
    private String nome;
    private double preco;
    private int quantidade;
    private String imagem;
    private String icone;
    private UUID categoriaId;

    // Add constructors, getters and setters
    // (Lombok can be used for brevity with @Getter @Setter)
    
    // Example constructor
    public ProdutoRequest(String nome, double preco, int quantidade, String imagem, String icone, UUID categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.icone = icone;
        this.categoriaId = categoriaId;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public String getImagem() { return imagem; }
    public String getIcone() { return icone; }
    public UUID getCategoriaId() { return categoriaId; }

}