package com.royalmuebles.backend.service;

import com.royalmuebles.backend.model.Cliente;
import com.royalmuebles.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Inyección por constructor
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente registrar(Cliente cliente) {
        cliente.setId(null); // Aseguramos que se cree uno nuevo
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> actualizar(Long id, Cliente datos) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(datos.getNombre());
                    cliente.setTelefono(datos.getTelefono());
                    cliente.setCorreo(datos.getCorreo());
                    cliente.setDocumento(datos.getDocumento());
                    cliente.setDireccion(datos.getDireccion());
                    return clienteRepository.save(cliente);
                });
    }

    public boolean eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
}