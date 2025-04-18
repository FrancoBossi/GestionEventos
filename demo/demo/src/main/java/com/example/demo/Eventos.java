package com.example.demo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Eventos {
    protected Map<String, Integer> entradas = new HashMap<>();
    protected List<Reserva> reservas = new ArrayList<>();
    private Asistente asistente = new Asistente();
    protected String nombreEvento;
    //funcion para obtener los horarios
    @Getter
    protected Map<String, String> horarios = new HashMap<>();

    public void agregarTipoEntrada(String tipo, int cantidad) {
        entradas.put(tipo, cantidad);
    }

    public void agregarHorario(String turno, String hora) {
        horarios.put(turno, hora);
    }

    public void mostrarHorarios() {
        System.out.println("Horarios disponibles:");
        for (Map.Entry<String, String> horario : horarios.entrySet()) {
            System.out.println(horario.getKey() + ": " + horario.getValue());
        }
    }

    public boolean reservarEntrada(String nombreCliente, String tipoEntrada, int cantidad, String turno, String nombreAsistente) {
        if (!horarios.containsKey(turno)) {
            System.out.println("El turno especificado no está disponible.");
            return false;
        }

        String horario = horarios.get(turno); // Obtener el horario asociado al turno

        if (entradas.containsKey(tipoEntrada) && entradas.get(tipoEntrada) >= cantidad) {
            entradas.put(tipoEntrada, entradas.get(tipoEntrada) - cantidad);
            reservas.add(new Reserva(nombreCliente, tipoEntrada, horario, nombreAsistente));
            System.out.println("Reserva creada para " + nombreCliente + " con estado 'Pendiente' en el horario: " + horario);
            asistente.tieneDerechoAEntradaGratis(nombreCliente); // Verificar si el cliente tiene derecho a entrada gratis
            return true;
        } else {
            System.out.println("No hay suficientes entradas disponibles para el tipo: " + tipoEntrada);
            return false;
        }
    }



    public void cancelarReserva(String nombreCliente) {
        for (Reserva reserva : reservas) {
            if (reserva.getNombreCliente().equals(nombreCliente) && reserva.getEstado().equals("Pendiente")) {
                reserva.cancelar();
                System.out.println("Reserva cancelada para " + nombreCliente);
                return;
            }
        }
        System.out.println("No se encontró una reserva pendiente para " + nombreCliente);
    }

    public List<Reserva> mostrarReservas() {
        return reservas;
    }
    public void mostrarTipoEntradas() {
        System.out.println("Tipos de entradas disponibles:");
        for (Map.Entry<String, Integer> entrada : entradas.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }

    //funcion para obtener las entradas
    public Map<String, Integer> getEntradas() {
        return entradas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }


    public void confirmarReserva(String idReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                reserva.setEstado("Confirmada");
                break;
            }
        }
    }

    public String getNombreEvento() {
        return nombreEvento;
    }
}
