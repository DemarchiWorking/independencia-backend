package com.bazar.bazar.response;


import com.bazar.bazar.model.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ItemPedidoResponse {
    private UUID id;
    private UUID produtoId;
    private String produtoNome;
    private double produtoPreco;
    private int quantidade;

    public ItemPedidoResponse(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.produtoId = itemPedido.getProduto().getId();
        this.produtoNome = itemPedido.getProduto().getNome();
        this.produtoPreco = itemPedido.getProduto().getPreco();
        this.quantidade = itemPedido.getQuantidade();
    }
}
