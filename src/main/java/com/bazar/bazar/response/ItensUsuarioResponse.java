package com.bazar.bazar.response;

import java.util.List;

import com.bazar.bazar.model.ItemPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItensUsuarioResponse {
    private List<ItemPedido> itensComoVendedor;
    private List<ItemPedido> itensComoComprador;
    private String mensagem;

    // Construtor para facilitar a criação do objeto
    public ItensUsuarioResponse(List<ItemPedido> itensComoVendedor, List<ItemPedido> itensComoComprador, String mensagem) {
        this.itensComoVendedor = itensComoVendedor;
        this.itensComoComprador = itensComoComprador;
        this.mensagem = mensagem;
    }
}
