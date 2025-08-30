// src/main/java/com/bazar/api/bazar/model/Cliente.java
package com.bazar.bazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "cliente")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue
    public UUID id;
    private String nome;
    private String email; // Exemplo de campo adicional
    private LocalDateTime dataCriacao;
}


/*

package com.bazar.api.bazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name= "cliente")
@Entity
@Setter // Adicionado para consistência
@Getter // Adicionado para consistência
@NoArgsConstructor // Adicionado para consistência
@AllArgsConstructor // Adicionado para consistência
public class Cliente {

    @Id
    @GeneratedValue
    public UUID id;
    private String nome;

    // Correção na relação de Endereco:
    // Se um cliente pode ter *muitos* endereços:
    @OneToMany(mappedBy = "cliente") // Mapeia o campo 'cliente' na classe Endereco
    private List<Endereco> enderecos; // Alterado para 'enderecos' (plural)

    private double creditos;
    private LocalDateTime dataCriacao;
}*/