package com.bazar.bazar.service;

import com.bazar.bazar.dto.ItemPedidoDTO;
import com.bazar.bazar.dto.PedidoDTO;
import com.bazar.bazar.dto.ProdutoDTO;
import com.bazar.bazar.dto.ProdutoItemDTO;
import com.bazar.bazar.dto.UsuarioDTO;
import com.bazar.bazar.model.ItemPedido;
import com.bazar.bazar.model.Usuario;
import com.bazar.bazar.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }
        
    public List<ItemPedidoDTO> buscarItensPorVendedorLogado() {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID usuarioId = usuarioLogado.getId();

        List<ItemPedido> itens = itemPedidoRepository.buscarPorVendedorId(usuarioId);
        return itens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ItemPedidoDTO> buscarItensPorCompradorLogado() {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID usuarioId = usuarioLogado.getId();

        List<ItemPedido> itens = itemPedidoRepository.buscarPorCompradorId(usuarioId);
        return itens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private ItemPedidoDTO convertToDTO(ItemPedido item) {
        // Converter vendedor (usando o vendedor do produto)
        UsuarioDTO vendedorDTO = new UsuarioDTO(
                item.getPedido().getVendedor().getId(),
                item.getPedido().getVendedor().getNome(),
                item.getPedido().getVendedor().getEmail(),
                item.getPedido().getVendedor().getCpf(),
                item.getPedido().getVendedor().getCnpj(),
                item.getPedido().getVendedor().getTelefone(),
                item.getPedido().getVendedor().getFoto(),
                item.getPedido().getVendedor().getPontos()
        );

        // Converter comprador (usando o cliente do pedido)
        UsuarioDTO compradorDTO = new UsuarioDTO(
                item.getPedido().getCliente().getId(),
                item.getPedido().getCliente().getNome(),
                item.getPedido().getCliente().getEmail(),
                item.getPedido().getCliente().getCpf(),
                item.getPedido().getCliente().getCnpj(),
                item.getPedido().getCliente().getTelefone(),
                item.getPedido().getCliente().getFoto(),
                item.getPedido().getCliente().getPontos()
        );

        // Converter produto
        ProdutoItemDTO produtoDTO = new ProdutoItemDTO(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getProduto().getPreco(),
                item.getProduto().getDescricao(),
                item.getProduto().getImagem()
                //,vendedorDTO
        );

        // Converter pedido
        PedidoDTO pedidoDTO = new PedidoDTO(
                item.getPedido().getId(),
                compradorDTO,
                vendedorDTO,
                item.getPedido().getDataCriacao(),
                item.getPedido().getDataEntrega(),
                item.getPedido().getEnderecoEntrega(),
                item.getPedido().getRemote()
        );

        // Criar ItemPedidoDTO
        return new ItemPedidoDTO(
                item.getId(),
                item.getQuantidade(),
                produtoDTO,
                pedidoDTO,
                vendedorDTO,
                compradorDTO
        );
    }
}