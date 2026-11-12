package com.royalmuebles.backend.service;

import com.royalmuebles.backend.model.*;
import com.royalmuebles.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final MuebleRepository muebleRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public VentaService(VentaRepository ventaRepository, MuebleRepository muebleRepository, 
                        ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.ventaRepository = ventaRepository;
        this.muebleRepository = muebleRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional // Si algo falla, no guarda nada a medias
    public Venta registrarVenta(Long clienteId, Long vendedorId, String metodoPago, List<DetalleVenta> detallesRequest) {
        // 1. Buscar cliente y vendedor
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
        Usuario vendedor = usuarioRepository.findById(vendedorId).orElseThrow();

        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setCliente(cliente);
        venta.setVendedor(vendedor);
        venta.setMetodoPago(metodoPago);

        double totalVenta = 0;
        List<DetalleVenta> detallesGuardados = new ArrayList<>();

        // 2. Procesar cada mueble vendido
        for (DetalleVenta det : detallesRequest) {
            Mueble mueble = muebleRepository.findById(det.getMueble().getId()).orElseThrow();
            
            // REGLA DE NEGOCIO: Verificar stock
            if (mueble.getStock() < det.getCantidad()) {
                throw new RuntimeException("No hay stock suficiente para: " + mueble.getNombre());
            }

            // Restar stock
            mueble.setStock(mueble.getStock() - det.getCantidad());
            muebleRepository.save(mueble);

            // Calcular subtotal usando el precio con descuento
            double precioFinal = mueble.getPrecioConDescuento();
            det.setSubtotal(precioFinal * det.getCantidad());
            det.setMueble(mueble);
            det.setVenta(venta);
            
            totalVenta += det.getSubtotal();
            detallesGuardados.add(det);
        }

        venta.setTotal(totalVenta);
        venta.setDetalles(detallesGuardados);

        return ventaRepository.save(venta);
    }
}