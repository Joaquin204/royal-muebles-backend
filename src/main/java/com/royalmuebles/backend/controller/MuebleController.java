package com.royalmuebles.backend.controller;

import com.royalmuebles.backend.model.Mueble;
import com.royalmuebles.backend.service.MuebleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    private final MuebleService muebleService;

    public MuebleController(MuebleService muebleService) {
        this.muebleService = muebleService;
    }

    @GetMapping
    public List<Mueble> listar() {
        return muebleService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mueble> buscarPorId(@PathVariable Long id) {
        return muebleService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mueble> registrar(@RequestBody Mueble mueble) {
        Mueble guardado = muebleService.registrar(mueble);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mueble> actualizar(@PathVariable Long id, @RequestBody Mueble mueble) {
        return muebleService.actualizar(id, mueble)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!muebleService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}