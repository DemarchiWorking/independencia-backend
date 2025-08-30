package com.bazar.bazar.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Entity // Define esta classe como uma entidade JPA
@Table(name = "evento") // Mapeia para a tabela 'evento' no banco de dados
@Setter // Adiciona setters Lombok
@Getter // Adiciona getters Lombok
@NoArgsConstructor // Construtor sem argumentos Lombok
@AllArgsConstructor // Construtor com todos os argumentos Lombok
public class Evento {

    @Id // Define 'id' como chave primária
    @GeneratedValue // Permite que o provedor de persistência gere o valor do ID
    private UUID id;

    private String titulo;
    private String descricao;
    private UUID pedidoId; // Verifique se isso deve ser um UUID simples ou uma relação @ManyToOne
    private LocalDateTime dataCriacao;
    private Boolean remote;
    private String evento_url;

    // Os campos comentados são válidos, se você precisar deles no futuro:
    // private String code;
    // private Integer desconto;
    // private Date valid; // Lembre-se de importar java.util.Date ou usar java.time.LocalDate/LocalDateTime
}