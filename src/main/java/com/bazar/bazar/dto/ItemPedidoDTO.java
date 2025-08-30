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
public class ItemPedidoDTO {

    private UUID id;
    private int quantidade;
    private ProdutoItemDTO produto; // Apenas o ID para evitar a recursão
    private PedidoDTO pedido; // Usamos o DTO do Pedido aqui
    private UsuarioDTO vendedor; // Usamos o DTO do Vendedor aqui
    private UsuarioDTO comprador; // Usamos o DTO do Vendedor aqui

}