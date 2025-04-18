package com.example.demo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase abstracta que representa un evento.
 */
public abstract class Eventos {
    protected Map<String, Integer> entradas = new HashMap<>();
    protected List<Reserva> reservas = new ArrayList<>();
    private Asistente asistente = new Asistente();
    protected String nombreEvento;
    @Getter
    protected Map<String, String> horarios = new HashMap<>();

    /**
     * Agrega un tipo de entrada al evento.
     *
     * @param tipo     Tipo de entrada.
     * @param cantidad Cantidad disponible de este tipo de entrada.
     * @pre tipo != null && !tipo.isEmpty()
     * @pre cantidad > 0
     * @post entradas.containsKey(tipo)
     */
    public void agregarTipoEntrada(String tipo, int cantidad) {
        entradas.put(tipo, cantidad);
    }

    /**
     * Agrega un horario al evento.
     *
     * @param turno Turno del horario (ejemplo: "Mañana").
     * @param hora  Hora asociada al turno.
     * @pre turno != null && !turno.isEmpty()
     * @pre hora != null && !hora.isEmpty()
     * @post horarios.containsKey(turno)
     */
    public void agregarHorario(String turno, String hora) {
        horarios.put(turno, hora);
    }

    /**
     * Muestra los horarios disponibles del evento.
     *
     * @pre true
     * @post true
     */
    public void mostrarHorarios() {
        System.out.println("Horarios disponibles:");
        for (Map.Entry<String, String> horario : horarios.entrySet()) {
            System.out.println(horario.getKey() + ": " + horario.getValue());
        }
    }

    /**
     * Realiza una reserva de entrada para el evento.
     *
     * @param nombreCliente   Nombre del cliente.
     * @param tipoEntrada     Tipo de entrada a reservar.
     * @param cantidad        Cantidad de entradas a reservar.
     * @param turno           Turno del horario.
     * @param nombreAsistente Nombre del asistente asignado.
     * @return true si la reserva fue exitosa, false en caso contrario.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @pre tipoEntrada != null && !tipoEntrada.isEmpty()
     * @pre cantidad > 0
     * @pre turno != null && !turno.isEmpty()
     * @pre nombreAsistente != null && !nombreAsistente.isEmpty()
     * @post reservas.size() == \old(reservas.size()) + 1 || !result
     */
    public boolean reservarEntrada(String nombreCliente, String tipoEntrada, int cantidad, String turno, String nombreAsistente) {
        if (!horarios.containsKey(turno)) {
            System.out.println("El turno especificado no está disponible.");
            return false;
        }

        String horario = horarios.get(turno);

        if (entradas.containsKey(tipoEntrada) && entradas.get(tipoEntrada) >= cantidad) {
            entradas.put(tipoEntrada, entradas.get(tipoEntrada) - cantidad);
            reservas.add(new Reserva(nombreCliente, tipoEntrada, horario, nombreAsistente));
            System.out.println("Reserva creada para " + nombreCliente + " con estado 'Pendiente' en el horario: " + horario);
            asistente.tieneDerechoAEntradaGratis(nombreCliente);
            return true;
        } else {
            System.out.println("No hay suficientes entradas disponibles para el tipo: " + tipoEntrada);
            return false;
        }
    }

    /**
     * Cancela una reserva pendiente para un cliente.
     *
     * @param nombreCliente Nombre del cliente cuya reserva será cancelada.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @post reservas.stream().noneMatch(r -> r.getNombreCliente().equals(nombreCliente) && r.getEstado().equals("Pendiente"))
     */
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

    /**
     * Muestra todas las reservas del evento.
     *
     * @return Lista de reservas.
     * @pre true
     * @post result != null
     */
    public List<Reserva> mostrarReservas() {
        return reservas;
    }

    /**
     * Muestra los tipos de entradas disponibles.
     *
     * @pre true
     * @post true
     */
    public void mostrarTipoEntradas() {
        System.out.println("Tipos de entradas disponibles:");
        for (Map.Entry<String, Integer> entrada : entradas.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }

    /**
     * Obtiene los tipos de entradas disponibles.
     *
     * @return Mapa de tipos de entradas y sus cantidades.
     * @pre true
     * @post result != null
     */
    public Map<String, Integer> getEntradas() {
        return entradas;
    }

    /**
     * Obtiene la lista de reservas.
     *
     * @return Lista de reservas.
     * @pre true
     * @post result != null
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Confirma una reserva específica.
     *
     * @param idReserva ID de la reserva a confirmar.
     * @pre idReserva != null && !idReserva.isEmpty()
     * @post reservas.stream().anyMatch(r -> r.getIdReserva().equals(idReserva) && r.getEstado().equals("Confirmada"))
     */
    public void confirmarReserva(String idReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                reserva.setEstado("Confirmada");
                break;
            }
        }
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return Nombre del evento.
     * @pre true
     * @post result != null
     */
    public String getNombreEvento() {
        return nombreEvento;
    }
}