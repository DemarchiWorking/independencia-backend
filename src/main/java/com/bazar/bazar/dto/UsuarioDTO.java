package com.bazar.bazar.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String cnpj;
    private String telefone;
    private String foto; 
    private Long pontos;
    private Boolean loja; 
    
}              