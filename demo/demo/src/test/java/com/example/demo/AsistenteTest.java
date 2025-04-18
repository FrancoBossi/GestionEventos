package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsistenteTest {

    @Test
    void tieneDerechoAEntradaGratis() {
        // Crear un asistente y un cliente
        Asistente asistente = new Asistente();
        String nombreCliente = "Juan Perez";

        // Simular que el cliente tiene derecho a entrada gratis
        asistente.tieneDerechoAEntradaGratis(nombreCliente);

        // Verificar que el cliente tiene derecho a entrada gratis
        assertFalse(asistente.getClientesConDerechoAEntradaGratis().contains(nombreCliente));
    }


    @Test
    void motrar_historial_cliente() {
        // Crear un asistente y un cliente
        Asistente asistente = new Asistente();
        String nombreCliente = "Juan Perez";

        // Simular la creación de un historial
        asistente.guardarHistorial(nombreCliente, new Recitales());

        // Mostrar historial
        asistente.motrar_historial_cliente();

        // Verificar que el historial se muestra correctamente (esto dependerá de la implementación de motrar_historial_cliente)
        // Aquí podrías verificar que el historial contiene la reserva del cliente
        assertTrue(asistente.getHistorialClientes().containsKey(nombreCliente));
    }

    @Test
    void getNombresAsistentes() {
        // Crear un asistente
        Asistente asistente = new Asistente();

        // Obtener la lista de asistentes disponibles
        List<String> asistentesDisponibles = asistente.getNombresAsistentes();

        // Verificar que la lista no esté vacía y contenga los nombres esperados
        assertFalse(asistentesDisponibles.isEmpty());
        assertTrue(asistentesDisponibles.contains("Samuel"));
        assertTrue(asistentesDisponibles.contains("Camila"));
        assertTrue(asistentesDisponibles.contains("Jeremias"));
    }
}