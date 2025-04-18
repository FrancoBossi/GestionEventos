package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventosTest {

    @Test
    void agregarTipoEntrada() {
        // Arrange
        Eventos eventos = new Recitales();
        String tipoEntrada = "Campo";
        Integer cantidad = 10;

        // Act
        eventos.agregarTipoEntrada(tipoEntrada, cantidad);

        // Assert
        assertTrue(eventos.getEntradas().containsKey(tipoEntrada), "El tipo de entrada no fue agregado.");
        assertEquals(cantidad, eventos.getEntradas().get(tipoEntrada), "La cantidad no coincide.");

    }

    @Test
    void reservarEntrada() {
        // Arrange
        Eventos eventos = new Recitales();
        String cliente = "Juan";
        String tipoEntrada = "Campo";
        Integer cantidad = 2;
        String turno = "Mañana";
        String nombreAsistente = "Pedro";

        // Act
        boolean resultado = eventos.reservarEntrada(cliente, tipoEntrada, cantidad, turno, nombreAsistente);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void mostrarReservas() {
        // Arrange
        Eventos eventos = new Recitales();
        String cliente = "Juan";
        String tipoEntrada = "Campo";
        Integer cantidad = 2;
        String turno = "Mañana";
        String nombreAsistente = "Pedro";

        // Act
        eventos.reservarEntrada(cliente, tipoEntrada, cantidad, turno, nombreAsistente);
        eventos.mostrarReservas();

        // Assert
        assertEquals(1, eventos.getReservas().size());
    }

    @Test
    void getEntradas() {
        // Arrange
        Eventos eventos = new Recitales();
        String tipoEntrada = "Premium";
        Integer cantidadEsperada = 20;

        // Act
        eventos.agregarTipoEntrada(tipoEntrada, cantidadEsperada);

        // Assert
        assertEquals(cantidadEsperada, eventos.getEntradas().get(tipoEntrada));
    }

    @Test
    void getReservas() {
        // Arrange
        Eventos eventos = new Recitales();
        String cliente = "Juan";
        String tipoEntrada = "Campo";
        Integer cantidad = 2;
        String turno = "Mañana";
        String nombreAsistente = "Pedro";

        // Act
        eventos.reservarEntrada(cliente, tipoEntrada, cantidad, turno, nombreAsistente);
        eventos.mostrarReservas();

        // Assert
        assertEquals(1, eventos.getReservas().size());
    }

    @Test
    void getHorarios() {
        // Arrange
        Eventos eventos = new Recitales();
        String turno = "Madrugada";
        String horario = "00:02 AM";
        String horarioEsperado = "00:02 AM";

        // Act
        eventos.agregarHorario(turno, horario);


        // Assert
        assertEquals(horario, eventos.getHorarios().get(turno));
    }
}