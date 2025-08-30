package com.bazar.bazar.response;

import com.bazar.bazar.model.ItemPedido;
import com.bazar.bazar.model.Pedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
public class PedidoResponse {
    private UUID id;
    private UUID clienteId;
    private String clienteNome; // Adiciona nome do cliente para melhor visualização
    private LocalDateTime dataCriacao;
    private Boolean remote;
    private List<ItemPedidoResponse> itens; // DTO para os itens do pedido

    public PedidoResponse(Pedido pedido) {
        this.id = pedido.getId();
        this.clienteId = pedido.getCliente().getId();
        this.clienteNome = pedido.getCliente().getNome();
        this.dataCriacao = pedido.getDataCriacao();
        this.remote = pedido.getRemote();
        this.itens = pedido.getItens().stream()
                .map(ItemPedidoResponse::new)
                .collect(Collectors.toList());
    }
}