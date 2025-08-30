package com.bazar.bazar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name= "produto")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue
    public UUID id;
    private String nome;
    private double preco;
    private String categoria;
    @Column(name = "categoria_id")
    private UUID categoriaId;
    @Column(name = "autor_id")
    private UUID autorId;
    private int quantidade;
    private String imagem;
    private String descricao;
    private String icone;
    private LocalDateTime dataCriacao;

}