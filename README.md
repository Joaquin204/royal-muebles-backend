# Royal Muebles - Backend API

API REST para la gestión de la tienda de muebles Royal Muebles, desarrollada con Spring Boot, Spring Data JPA y MySQL.

## Cómo ejecutar el proyecto
1. Crear la base de datos en MySQL: `CREATE DATABASE royal_muebles;`
2. Configurar las credenciales en `src/main/resources/application.properties`.
3. Ejecutar el proyecto: `./mvnw spring-boot:run`
4. La API estará disponible en `http://localhost:8080`

## Endpoints principales
*   **Muebles:** `GET /api/muebles`, `POST /api/muebles`, `PUT /api/muebles/{id}`, `DELETE /api/muebles/{id}`
*   **Clientes:** `GET /api/clientes`, `POST /api/clientes`, etc.
*   **Usuarios (Login):** `POST /api/usuarios/login` (envía `{"username": "...", "password": "..."}`)
*   **Ventas:** `POST /api/ventas` (registra una venta y descuenta stock automáticamente)

## Reglas de Negocio Implementadas
*   Si el mueble cuesta más de 1000 soles, se aplica un 10% de descuento automático.
*   El estado del stock se calcula dinámicamente (`DISPONIBLE`, `BAJO STOCK`, `AGOTADO`).
*   Al registrar una venta, se valida el stock y se descuenta automáticamente de la base de datos.
