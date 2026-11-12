package com.royalmuebles.backend.repository;

import com.royalmuebles.backend.model.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuebleRepository extends JpaRepository<Mueble, Long> {
    // Búsqueda personalizada por nombre (útil para el buscador)
    List<Mueble> findByNombreContainingIgnoreCase(String nombre);
}