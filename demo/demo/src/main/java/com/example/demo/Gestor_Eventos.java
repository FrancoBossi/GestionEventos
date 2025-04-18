package com.example.demo;

import java.util.List;

public interface Gestor_Eventos {



    void cancelarReserva(Eventos evento, List<String> nombres_clientes);
    void mostrarReservas(Eventos evento);
    void motrar_historial_cliente();
    void guardarHistorial(String nombreCliente, Eventos evento);
}