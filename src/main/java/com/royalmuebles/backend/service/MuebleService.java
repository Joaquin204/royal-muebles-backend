package com.royalmuebles.backend.service;

import com.royalmuebles.backend.model.Mueble;
import com.royalmuebles.backend.repository.MuebleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuebleService {

    private final MuebleRepository muebleRepository;

    public MuebleService(MuebleRepository muebleRepository) {
        this.muebleRepository = muebleRepository;
    }

    public List<Mueble> listarTodos() {
        return muebleRepository.findAll();
    }

    public Optional<Mueble> buscarPorId(Long id) {
        return muebleRepository.findById(id);
    }

    public Mueble registrar(Mueble mueble) {
        mueble.setId(null);
        return muebleRepository.save(mueble);
    }

    public Optional<Mueble> actualizar(Long id, Mueble datos) {
        return muebleRepository.findById(id)
                .map(mueble -> {
                    mueble.setNombre(datos.getNombre());
                    mueble.setCategoria(datos.getCategoria());
                    mueble.setDescripcion(datos.getDescripcion());
                    mueble.setPrecio(datos.getPrecio());
                    mueble.setStock(datos.getStock());
                    mueble.setColor(datos.getColor());
                    mueble.setMaterial(datos.getMaterial());
                    return muebleRepository.save(mueble);
                });
    }

    public boolean eliminar(Long id) {
        if (!muebleRepository.existsById(id)) {
            return false;
        }
        muebleRepository.deleteById(id);
        return true;
    }
}