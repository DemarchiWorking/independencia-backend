// src/main/java/com/bazar/bazar/request/PedidoRequest.java
package com.bazar.bazar.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoRequest {
    
    //private UUID clienteId;
    private UUID vendedorId;
    //private UUID enderecoEntregaId;
    private Boolean remote;
    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;
    private List<ItemPedidoRequest> itens;

}