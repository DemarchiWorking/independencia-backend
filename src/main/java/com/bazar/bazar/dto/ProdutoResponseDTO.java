package com.bazar.bazar.dto;

import com.bazar.bazar.model.Categoria;
import com.bazar.bazar.model.Produto;
import com.bazar.bazar.model.Usuario;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO {
    
    private UUID id;
    private String nome;
    private double preco;
    private int quantidade;
    private String imagem;
    private String icone;
    private Categoria categoria; 
    private Usuario autor;
    private String descricao;
    private boolean ehAutor;

    // Construtor que recebe um objeto Produto e um boolean para a autoria
    public ProdutoResponseDTO(Produto produto, boolean isAutor) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
        this.imagem = produto.getImagem();
        this.descricao = produto.getDescricao();
        this.icone = produto.getIcone();
        this.categoria = produto.getCategoria();

        // Verifique se a categoria não é nula antes de tentar pegar o ID
        if (produto.getCategoria() != null) {
            this.categoria = produto.getCategoria();
        }
        
        this.autor = produto.getAutor();
        this.ehAutor = isAutor;
    }
}