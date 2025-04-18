package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asistente implements Gestor_Eventos {
    private HashMap<String, List<String>> historial_clientes = new HashMap<>();
    protected  List<String> asistentes_disponibles = List.of("Samuel","Camila", "Jeremias");
    private HashMap<String,Integer> asistencias_de_clientes = new HashMap<>(); //si un cliente asiste a un evento se le suma 1 a su contador de asistencias, y cuando llegue a 5 se le avisa que su proximo evento es gratis, y se le reinicia el contador

    @Override
    public void cancelarReserva(Eventos evento, List<String> nombres_clientes) {
        for (String nombre : nombres_clientes) {
            evento.cancelarReserva(nombre);
        }
    }

    public void tieneDerechoAEntradaGratis(String nombreCliente) {
        //si el cliente ya esta en la lista de asistencias, se le suma 1 a su contador
        //si no, se le agrega a la lista con 1 asistencia
        //si el contador llega a 5, se le avisa que su proximo evento es gratis y se le reinicia el contador
        if (asistencias_de_clientes.containsKey(nombreCliente)) {
            int asistencias = asistencias_de_clientes.get(nombreCliente);
            asistencias++;
            asistencias_de_clientes.put(nombreCliente, asistencias);
            if (asistencias == 5) {
                System.out.println("Felicidades " + nombreCliente + ", tu proximo evento es gratis!");
                asistencias_de_clientes.put(nombreCliente, 0); // Reiniciar contador
            }
        } else {
            asistencias_de_clientes.put(nombreCliente, 1);
        }
    }

    @Override
    public void mostrarReservas(Eventos evento) {
        evento.mostrarReservas();
    }

    @Override
    public void motrar_historial_cliente() {
        System.out.println("Historial de clientes:");
        for (Map.Entry<String, List<String>> entry : historial_clientes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Override
    public void guardarHistorial(String nombreCliente, Eventos evento) {
        if (!historial_clientes.containsKey(nombreCliente)) {
            historial_clientes.put(nombreCliente, new ArrayList<>());
        }
        historial_clientes.get(nombreCliente).add(evento.getNombreEvento());
        System.out.println("Reserva guardada en el historial de " + nombreCliente);
    }

    public void mostrarHistorialDe(String nombreCliente) {
        List<String> historial = historial_clientes.get(nombreCliente);
        if (historial != null) {
            System.out.println("Historial de " + nombreCliente + ": " + historial);
        } else {
            System.out.println(nombreCliente + " no tiene historial registrado.");
        }
    }


    public List<String> getNombresAsistentes() {
        return asistentes_disponibles;
    }

    public HashMap<String, List<String>> getHistorialClientes() {
        return historial_clientes;
    }

    public String getClientesConDerechoAEntradaGratis() {
        StringBuilder clientesConDerecho = new StringBuilder();
        for (Map.Entry<String, Integer> entry : asistencias_de_clientes.entrySet()) {
            if (entry.getValue() >= 5) {
                clientesConDerecho.append(entry.getKey()).append(", ");
            }
        }
        if (clientesConDerecho.length() > 0) {
            clientesConDerecho.setLength(clientesConDerecho.length() - 2); // Eliminar la última coma y espacio
        }
        return clientesConDerecho.toString();
    }
}

