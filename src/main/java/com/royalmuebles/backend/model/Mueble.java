package com.royalmuebles.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "muebles")
public class Mueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 50)
    private String categoria; // Sala, Dormitorio, Comedor, etc.

    @Column(length = 255)
    private String descripcion;

    @Positive(message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private int stock;

    @Column(length = 50)
    private String color;

    @Column(length = 50)
    private String material; // Madera, Metal, Tela

    // --- REGLA DE NEGOCIO: Estado del stock ---
    @Transient
    public String getEstadoStock() {
        if (stock == 0) return "AGOTADO";
        if (stock < 5) return "BAJO STOCK";
        return "DISPONIBLE";
    }

    // --- REGLA DE NEGOCIO: Descuento ---
    @Transient
    public double getPrecioConDescuento() {
        // Si el mueble cuesta más de 1000, aplicamos 10% de descuento
        return (precio > 1000) ? precio * 0.90 : precio;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}