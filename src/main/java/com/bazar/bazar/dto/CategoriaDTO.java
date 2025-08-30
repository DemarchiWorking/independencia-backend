package com.bazar.bazar.dto;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String descricao;
    private String icone;
}