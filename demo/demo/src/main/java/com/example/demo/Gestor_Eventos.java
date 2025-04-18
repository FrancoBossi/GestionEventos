package com.example.demo;

import java.util.List;

/**
 * Interfaz que define las operaciones para gestionar eventos y reservas.
 */
public interface Gestor_Eventos {

    /**
     * Cancela las reservas de un evento para una lista de clientes.
     *
     * @param evento           Evento del cual se cancelarán las reservas.
     * @param nombres_clientes Lista de nombres de los clientes cuyas reservas serán canceladas.
     * @pre evento != null
     * @pre nombres_clientes != null && !nombres_clientes.isEmpty()
     * @post true
     */
    void cancelarReserva(Eventos evento, List<String> nombres_clientes);

    /**
     * Muestra todas las reservas asociadas a un evento.
     *
     * @param evento Evento cuyas reservas se mostrarán.
     * @pre evento != null
     * @post true
     */
    void mostrarReservas(Eventos evento);

    /**
     * Muestra el historial de reservas de todos los clientes.
     *
     * @pre true
     * @post true
     */
    void motrar_historial_cliente();

    /**
     * Guarda el historial de reservas de un cliente para un evento.
     *
     * @param nombreCliente Nombre del cliente.
     * @param evento        Evento asociado a la reserva.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @pre evento != null
     * @post true
     */
    void guardarHistorial(String nombreCliente, Eventos evento);
}