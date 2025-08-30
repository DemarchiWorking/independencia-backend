package com.bazar.bazar.service;


import com.bazar.bazar.model.Cliente;
import com.bazar.bazar.model.Endereco;
import com.bazar.bazar.repositories.ClienteRepository;
import com.bazar.bazar.repositories.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import para @Transactional

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // Indica que esta classe é um componente de serviço do Spring
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired // Injeção de dependência do ClienteRepository
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método para criar um novo cliente
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        // Validação básica: verificar se o email já existe
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um cliente com este email: " + cliente.getEmail());
            // Em um projeto real, você usaria uma exceção mais específica (ex: DuplicateEmailException)
        }
        if (cliente.getDataCriacao() == null) {
            cliente.setDataCriacao(LocalDateTime.now());
        }
        // Se houver lógica de negócio adicional (ex: definir créditos iniciais, criptografar senha), seria aqui.
        return clienteRepository.save(cliente);
    }

    // Método para buscar um cliente pelo ID
    public Optional<Cliente> getClienteById(UUID id) {
        return clienteRepository.findById(id);
    }

    // Método para buscar todos os clientes
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Método para buscar clientes por nome (ignorando case)
    public List<Cliente> getClientesByNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Método para atualizar um cliente existente
    @Transactional
    public Cliente updateCliente(UUID id, Cliente clienteDetails) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    // Evitar atualizar o ID
                    clienteExistente.setNome(clienteDetails.getNome());
                    clienteExistente.setEmail(clienteDetails.getEmail());
                    // Créditos ou outros campos podem ser atualizados aqui também
                    // clienteExistente.setCreditos(clienteDetails.getCreditos());

                    // Validação para email: se o email foi alterado e já existe, impedir a atualização
                    if (!clienteExistente.getEmail().equals(clienteDetails.getEmail())) {
                        if (clienteRepository.findByEmail(clienteDetails.getEmail()).isPresent()) {
                            throw new RuntimeException("O novo email já está em uso por outro cliente: " + clienteDetails.getEmail());
                        }
                    }

                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        // Em um projeto real, você usaria uma exceção mais específica (ex: ResourceNotFoundException)
    }

    // Método para deletar um cliente pelo ID
    @Transactional
    public void deleteCliente(UUID id) {
        if (clienteRepository.existsById(id)) {
            // Considerações importantes ao deletar um cliente:
            // O que acontece com os pedidos associados a ele?
            // Dependendo da sua regra de negócio e do relacionamento no DB (CASCADE, RESTRICT),
            // você pode precisar deletar pedidos primeiro ou impedir a exclusão.
            // Para simplicidade, assumimos que o banco de dados lidará com CASCADE ou que não há FK restrict.
            // No entanto, se o relacionamento for bidirecional e você carregar os pedidos,
            // pode precisar limpar a lista de pedidos do cliente antes de deletá-lo para evitar erros de JPA.
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
            // Em um projeto real, você usaria uma exceção mais específica
        }
    }
}
