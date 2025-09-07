package com.bazar.bazar.service;

import com.bazar.bazar.model.Endereco;
import com.bazar.bazar.model.Usuario;
import com.bazar.bazar.repositories.EnderecoRepository;
import com.bazar.bazar.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
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
    public Optional<Endereco> buscarEnderecoPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado Email " + email));
        return enderecoRepository.findByUsuarioId(usuario.getId());
    }
    public void deletar(UUID id) {
        enderecoRepository.deleteById(id);
    }
}