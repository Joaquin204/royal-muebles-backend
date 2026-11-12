package com.royalmuebles.backend.controller;

import com.royalmuebles.backend.model.DetalleVenta;
import com.royalmuebles.backend.model.Venta;
import com.royalmuebles.backend.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Usamos una clase interna simple para recibir los datos del JSON
    public static class VentaRequest {
        public Long clienteId;
        public Long vendedorId;
        public String metodoPago;
        public List<DetalleVenta> detalles;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody VentaRequest request) {
        try {
            Venta nuevaVenta = ventaService.registrarVenta(
                request.clienteId, 
                request.vendedorId, 
                request.metodoPago, 
                request.detalles
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}