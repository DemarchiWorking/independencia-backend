package com.bazar.bazar.mapper;


import com.bazar.bazar.model.ItemPedido;
import com.bazar.bazar.model.Pedido;
import com.bazar.bazar.model.Usuario;
import com.bazar.bazar.dto.ItemPedidoDTO;
import com.bazar.bazar.dto.PedidoDTO;
import com.bazar.bazar.dto.UsuarioDTO;

import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
/*
    public ItemPedidoDTO toDTO(ItemPedido itemPedido) {
        if (itemPedido == null) {
            return null;
        }

        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(itemPedido.getId());
        dto.setQuantidade(itemPedido.getQuantidade());

        // Usar IDs para evitar a recursão infinita e o erro
        if (itemPedido.getProduto() != null) {
            dto.setProdutoId(itemPedido.getProduto().getId());
        }
        
        // Mapear Pedido para PedidoDTO
        if (itemPedido.getPedido() != null) {
            dto.setPedido(toPedidoDTO(itemPedido.getPedido()));
        }

        // Mapear Vendedor (Usuario) para UsuarioDTO
        if (itemPedido.getVendedor() != null) {
            dto.setVendedor(toUsuarioDTO(itemPedido.getVendedor()));
        }

        return dto;
    }

    public PedidoDTO toPedidoDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setDataCriacao(pedido.getDataCriacao());
        dto.setDataEntrega(pedido.getDataEntrega());
        if (pedido.getCliente() != null) {
            dto.setCliente(toUsuarioDTO(pedido.getCliente()));
        }
        return dto;
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }*/
}
