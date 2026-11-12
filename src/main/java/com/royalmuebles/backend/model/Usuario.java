package com.royalmuebles.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(unique = true, nullable = false, length = 50)
    private String username; // Ej: "joaquin_vendedor"

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String password; // Para pruebas, texto plano. Luego se encripta.

    @NotBlank(message = "El rol es obligatorio")
    @Column(nullable = false, length = 30)
    private String rol; // "ADMIN", "VENDEDOR", "ALMACEN"

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}