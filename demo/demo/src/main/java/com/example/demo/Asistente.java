package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa un asistente encargado de gestionar eventos y reservas.
 */
public class Asistente implements Gestor_Eventos {
    private HashMap<String, List<String>> historial_clientes = new HashMap<>();
    protected List<String> asistentes_disponibles = List.of("Samuel", "Camila", "Jeremias");
    private HashMap<String, Integer> asistencias_de_clientes = new HashMap<>();

    /**
     * Cancela las reservas de un evento para una lista de clientes.
     *
     * @param evento           Evento del cual se cancelarán las reservas.
     * @param nombres_clientes Lista de nombres de los clientes cuyas reservas serán canceladas.
     * @pre evento != null
     * @pre nombres_clientes != null && !nombres_clientes.isEmpty()
     * @post true
     */
    @Override
    public void cancelarReserva(Eventos evento, List<String> nombres_clientes) {
        for (String nombre : nombres_clientes) {
            evento.cancelarReserva(nombre);
        }
    }

    /**
     * Verifica si un cliente tiene derecho a una entrada gratis y actualiza su contador de asistencias.
     *
     * @param nombreCliente Nombre del cliente.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @post asistencias_de_clientes.containsKey(nombreCliente)
     */
    public void tieneDerechoAEntradaGratis(String nombreCliente) {
        if (asistencias_de_clientes.containsKey(nombreCliente)) {
            int asistencias = asistencias_de_clientes.get(nombreCliente);
            asistencias++;
            asistencias_de_clientes.put(nombreCliente, asistencias);
            if (asistencias == 5) {
                System.out.println("Felicidades " + nombreCliente + ", tu próximo evento es gratis!");
                asistencias_de_clientes.put(nombreCliente, 0);
            }
        } else {
            asistencias_de_clientes.put(nombreCliente, 1);
        }
    }

    /**
     * Muestra todas las reservas asociadas a un evento.
     *
     * @param evento Evento cuyas reservas se mostrarán.
     * @pre evento != null
     * @post true
     */
    @Override
    public void mostrarReservas(Eventos evento) {
        evento.mostrarReservas();
    }

    /**
     * Muestra el historial de reservas de todos los clientes.
     *
     * @pre true
     * @post true
     */
    @Override
    public void motrar_historial_cliente() {
        System.out.println("Historial de clientes:");
        for (Map.Entry<String, List<String>> entry : historial_clientes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Guarda el historial de reservas de un cliente para un evento.
     *
     * @param nombreCliente Nombre del cliente.
     * @param evento        Evento asociado a la reserva.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @pre evento != null
     * @post historial_clientes.containsKey(nombreCliente)
     */
    @Override
    public void guardarHistorial(String nombreCliente, Eventos evento) {
        if (!historial_clientes.containsKey(nombreCliente)) {
            historial_clientes.put(nombreCliente, new ArrayList<>());
        }
        historial_clientes.get(nombreCliente).add(evento.getNombreEvento());
        System.out.println("Reserva guardada en el historial de " + nombreCliente);
    }

    /**
     * Muestra el historial de reservas de un cliente específico.
     *
     * @param nombreCliente Nombre del cliente.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @post true
     */
    public void mostrarHistorialDe(String nombreCliente) {
        List<String> historial = historial_clientes.get(nombreCliente);
        if (historial != null) {
            System.out.println("Historial de " + nombreCliente + ": " + historial);
        } else {
            System.out.println(nombreCliente + " no tiene historial registrado.");
        }
    }

    /**
     * Obtiene la lista de nombres de asistentes disponibles.
     *
     * @return Lista de nombres de asistentes.
     * @pre true
     * @post result != null
     */
    public List<String> getNombresAsistentes() {
        return asistentes_disponibles;
    }

    /**
     * Obtiene el historial de clientes.
     *
     * @return Mapa con el historial de clientes.
     * @pre true
     * @post result != null
     */
    public HashMap<String, List<String>> getHistorialClientes() {
        return historial_clientes;
    }

    /**
     * Obtiene los clientes con derecho a una entrada gratis.
     *
     * @return Cadena con los nombres de los clientes con derecho a entrada gratis.
     * @pre true
     * @post result != null
     */
    public String getClientesConDerechoAEntradaGratis() {
        StringBuilder clientesConDerecho = new StringBuilder();
        for (Map.Entry<String, Integer> entry : asistencias_de_clientes.entrySet()) {
            if (entry.getValue() >= 5) {
                clientesConDerecho.append(entry.getKey()).append(", ");
            }
        }
        if (clientesConDerecho.length() > 0) {
            clientesConDerecho.setLength(clientesConDerecho.length() - 2);
        }
        return clientesConDerecho.toString();
    }
}