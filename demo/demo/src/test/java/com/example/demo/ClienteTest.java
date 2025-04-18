package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void getNombre() {
        // Arrange
        Cliente cliente = new Cliente("Juan");

        // Act
        String nombre = cliente.getNombre();

        // Assert
        assertEquals("Juan", nombre);
    }

    @Test
    void setNombre() {
        // Arrange
        Cliente cliente = new Cliente();
        String nuevoNombre = "Maria";

        // Act
        cliente.setNombre(nuevoNombre);
        String nombre = cliente.getNombre();

        // Assert
        assertEquals(nuevoNombre, nombre);
    }
}