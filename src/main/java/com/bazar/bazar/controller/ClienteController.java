package com.bazar.bazar.controller;
import com.bazar.bazar.model.Cliente;
import com.bazar.bazar.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/clientes") // Define o caminho base para todos os endpoints neste controlador
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Endpoint para criar um novo cliente
    // POST http://localhost:8081/api/clientes
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.createCliente(cliente);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Em um ambiente de produção, você mapearia isso para 409 Conflict (para email duplicado) ou 400 Bad Request
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para buscar um cliente pelo ID
    // GET http://localhost:8081/api/clientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable UUID id) {
        return clienteService.getClienteById(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para buscar todos os clientes
    // GET http://localhost:8081/api/clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Endpoint para buscar clientes por nome
    // GET http://localhost:8081/api/clientes/search?nome=joao
    @GetMapping("/search")
    public ResponseEntity<List<Cliente>> searchClientesByName(@RequestParam String nome) {
        List<Cliente> clientes = clienteService.getClientesByNome(nome);
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Endpoint para atualizar um cliente
    // PUT http://localhost:8081/api/clientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteAtualizado = clienteService.updateCliente(id, cliente);
            return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Mapeie para 404 Not Found ou 400 Bad Request / 409 Conflict conforme o tipo de erro
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para deletar um cliente
    // DELETE http://localhost:8081/api/clientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
        try {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}