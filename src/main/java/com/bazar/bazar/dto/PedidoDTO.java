package com.bazar.bazar.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private UUID id;
    private UsuarioDTO cliente;
    private UsuarioDTO vendedor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEntrega;
    private UUID enderecoEntrega;
    private Boolean remote;
    //private List<ItemPedidoDTO> itens;

}