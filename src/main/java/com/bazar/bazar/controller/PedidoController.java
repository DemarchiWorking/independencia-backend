// src/main/java/com/bazar/bazar/controller/PedidoController.java
package com.bazar.bazar.controller;

import com.bazar.bazar.model.Pedido;
import com.bazar.bazar.model.Usuario;
import com.bazar.bazar.request.ItemPedidoRequest;
import com.bazar.bazar.request.PedidoRequest;
import com.bazar.bazar.response.PedidoResponse;
import com.bazar.bazar.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Endpoint para criar um novo pedido
    // POST http://localhost:8081/api/pedidos
    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoRequest) {

        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Pedido novoPedido = pedidoService.criarPedido(pedidoRequest, usuarioLogado);

        return new ResponseEntity<>(new PedidoResponse(novoPedido), HttpStatus.CREATED);
    }

    // Endpoint para buscar um pedido pelo ID
    // GET http://localhost:8081/api/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPedidoPorId(@PathVariable UUID id) {
        
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        // Se o pedido não for encontrado, o service já lança a exceção 404.
        return new ResponseEntity<>(new PedidoResponse(pedido), HttpStatus.OK);
    }

    // Endpoint para buscar todos os pedidos
    // GET http://localhost:8081/api/pedidos
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarTodosPedidos() {
        List<PedidoResponse> pedidos = pedidoService.listarTodosPedidos().stream()
                .map(PedidoResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Endpoint para atualizar um pedido existente
    // PUT http://localhost:8081/api/pedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(@PathVariable UUID id, @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoRequest);
        // O service lida com os erros, o controller apenas retorna a resposta de sucesso.
        return new ResponseEntity<>(new PedidoResponse(pedidoAtualizado), HttpStatus.OK);
    }

    // Endpoint para deletar um pedido
    // DELETE http://localhost:8081/api/pedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        pedidoService.deletarPedido(id);
        // O service lida com os erros, o controller apenas retorna a resposta de sucesso.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para adicionar um item a um pedido existente
    // POST http://localhost:8081/api/pedidos/{pedidoId}/items
    @PostMapping("/{pedidoId}/items")
    public ResponseEntity<PedidoResponse> addItemToPedido(@PathVariable UUID pedidoId, @RequestBody ItemPedidoRequest itemRequest) {
        // CORREÇÃO: O tipo do path variable `pedidoId` foi alterado para UUID para consistência.
        Pedido pedidoAtualizado = pedidoService.addItemToPedido(pedidoId, itemRequest);
        return new ResponseEntity<>(new PedidoResponse(pedidoAtualizado), HttpStatus.OK);
    }

    // Endpoint para remover um item de um pedido existente
    // DELETE http://localhost:8081/api/pedidos/{pedidoId}/items/{itemId}
    @DeleteMapping("/{pedidoId}/items/{itemId}")
    public ResponseEntity<PedidoResponse> removeItemFromPedido(@PathVariable UUID pedidoId, @PathVariable UUID itemId) {
        Pedido pedidoAtualizado = pedidoService.removeItemFromPedido(pedidoId, itemId);
        return new ResponseEntity<>(new PedidoResponse(pedidoAtualizado), HttpStatus.OK);
    }
}