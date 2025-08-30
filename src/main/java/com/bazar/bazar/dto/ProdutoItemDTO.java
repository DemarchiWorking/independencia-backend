package com.bazar.bazar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoItemDTO {

    private UUID id;
    private String nome;
    private double preco;
    private String descricao;
    private String imagem;
    //private String categoria;
    //private CategoriaDTO categoriaId;
    //private UsuarioDTO autorId;
    //private int quantidade;
    //private String icone;
    //private LocalDateTime dataCriacao;
}
