package com.bazar.bazar.dto;

import com.bazar.bazar.model.Produto;
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
    private String categoria; 
    private UUID categoriaId; 
    private UUID autorId;
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
        this.categoriaId = produto.getCategoriaId();

        // Verifique se a categoria não é nula antes de tentar pegar o ID
        if (produto.getCategoria() != null) {
            this.categoria = produto.getCategoria();
        }
        
        this.autorId = produto.getAutorId();
        this.ehAutor = isAutor;
    }
}