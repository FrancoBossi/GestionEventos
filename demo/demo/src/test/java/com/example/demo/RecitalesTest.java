package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

class RecitalesTest {
    Recitales recitales;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        recitales = new Recitales();
    }

    @org.junit.jupiter.api.Test
    void testRecitales() {
        assertEquals("Recitales", recitales.getNombreEvento());
        assertEquals(3, recitales.getHorarios().size());
        assertEquals(3, recitales.getEntradas().size());
    }

    @org.junit.jupiter.api.Test
    void testAgregarTipoEntrada() {
        recitales.agregarTipoEntrada("VIP", 10);
        assertEquals(4, recitales.getEntradas().size());
        assertTrue(recitales.getEntradas().containsKey("VIP"));
    }



}