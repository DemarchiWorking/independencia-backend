package com.bazar.bazar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazar.bazar.dto.ItemPedidoDTO;
import com.bazar.bazar.model.ItemPedido;
import com.bazar.bazar.response.ItensUsuarioResponse;
import com.bazar.bazar.service.ItemPedidoService;
import com.bazar.bazar.service.PedidoService;

@RestController
@RequestMapping("/api/item")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @Autowired
    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }
    @GetMapping("/comprador")
    public ResponseEntity<List<ItemPedidoDTO>> getItensPorCompradorLogado() {
        List<ItemPedidoDTO> itens = itemPedidoService.buscarItensPorCompradorLogado();
        return ResponseEntity.ok(itens);
    } 
    @GetMapping("/vendedor")
    public ResponseEntity<List<ItemPedidoDTO>> getItensPorVendedorLogado() {
        List<ItemPedidoDTO> itens = itemPedidoService.buscarItensPorVendedorLogado();
        return ResponseEntity.ok(itens);
    } 
}