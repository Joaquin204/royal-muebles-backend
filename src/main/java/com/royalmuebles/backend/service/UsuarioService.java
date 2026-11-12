package com.royalmuebles.backend.service;

import com.royalmuebles.backend.model.Usuario;
import com.royalmuebles.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario registrar(Usuario usuario) {
        usuario.setId(null);
        return usuarioRepository.save(usuario);
    }

    // Lógica de Login básica
    public boolean login(String username, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        
        // Si el usuario existe y la contraseña coincide, devuelve true
        return usuario.isPresent() && usuario.get().getPassword().equals(password);
    }
}