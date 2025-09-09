package com.bazar.bazar.request;

//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.PositiveOrZero;
//import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

// Usar records é uma forma moderna e concisa de criar DTOs imutáveis no Java.
// Se preferir, pode usar uma classe normal com getters.
public record ProdutoRecordRequest(
    
    //@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    String nome,

    // É uma boa prática usar BigDecimal para valores monetários para evitar problemas de arredondamento.
    //@DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    BigDecimal preco,
    
    UUID categoriaId,
    
    //@PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidade,

    String imagem,

    //@Size(max = 500, message = "A descrição não pode exceder 500 caracteres")
    String descricao,

    String icone
) {
    // Nota: Para um PATCH, todos os campos são opcionais. 
    // A lógica no serviço irá verificar quais campos foram enviados (não são nulos) e atualizar apenas esses.
}