package com.bazar.bazar.service;

import com.bazar.bazar.model.Endereco;
import com.bazar.bazar.model.Usuario;
import com.bazar.bazar.repositories.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvar(Endereco endereco, Usuario usuario) {
        endereco.setUsuario(usuario); // Associa o usuário ao endereço
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(UUID id) {
        return enderecoRepository.findById(id);
    }
    
    public Optional<Endereco> buscarEnderecoPorUsuarioId(UUID usuarioId) {
        return enderecoRepository.findByUsuarioId(usuarioId);
    }
    public void deletar(UUID id) {
        enderecoRepository.deleteById(id);
    }
}