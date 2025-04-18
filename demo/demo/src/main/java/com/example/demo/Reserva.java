package com.example.demo;

import lombok.Setter;

import java.util.UUID;

/**
 * Clase que representa una reserva para un evento.
 */
public class Reserva {
    private String nombreCliente;
    private String tipoEntrada;
    private String nombreAsistente;
    private String idReserva;
    @Setter
    private String estado; // "Pendiente", "Confirmada", "Cancelada"
    private String horario;

    /**
     * Constructor de la clase Reserva.
     *
     * @param nombreCliente   Nombre del cliente que realiza la reserva.
     * @param tipoEntrada     Tipo de entrada seleccionada.
     * @param horario         Horario del evento.
     * @param nombreAsistente Nombre del asistente asignado.
     * @pre nombreCliente != null && !nombreCliente.isEmpty()
     * @pre tipoEntrada != null && !tipoEntrada.isEmpty()
     * @pre horario != null && !horario.isEmpty()
     * @pre nombreAsistente != null && !nombreAsistente.isEmpty()
     * @post this.estado.equals("Pendiente")
     * @post this.idReserva != null
     */
    public Reserva(String nombreCliente, String tipoEntrada, String horario, String nombreAsistente) {
        this.nombreCliente = nombreCliente;
        this.tipoEntrada = tipoEntrada;
        this.horario = horario;
        this.estado = "Pendiente";
        this.nombreAsistente = nombreAsistente;
        this.idReserva = UUID.randomUUID().toString(); // Generar un ID único basado en la hora actual
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     * @pre true
     * @post result != null
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Obtiene el tipo de entrada.
     *
     * @return Tipo de entrada.
     * @pre true
     * @post result != null
     */
    public String getTipoEntrada() {
        return tipoEntrada;
    }

    /**
     * Obtiene el estado de la reserva.
     *
     * @return Estado de la reserva.
     * @pre true
     * @post result != null
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtiene el horario de la reserva.
     *
     * @return Horario de la reserva.
     * @pre true
     * @post result != null
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Confirma la reserva.
     *
     * @pre this.estado.equals("Pendiente")
     * @post this.estado.equals("Confirmada")
     */
    public void confirmar() {
        this.estado = "Confirmada";
    }

    /**
     * Cancela la reserva.
     *
     * @pre this.estado.equals("Pendiente") || this.estado.equals("Confirmada")
     * @post this.estado.equals("Cancelada")
     */
    public void cancelar() {
        this.estado = "Cancelada";
    }

    /**
     * Obtiene el nombre del asistente asignado.
     *
     * @return Nombre del asistente.
     * @pre true
     * @post result != null
     */
    public String getNombreAsistente() {
        return nombreAsistente;
    }

    /**
     * Obtiene el ID único de la reserva.
     *
     * @return ID de la reserva.
     * @pre true
     * @post result != null
     */
    public String getIdReserva() {
        return idReserva;
    }

    /**
     * Establece un nuevo ID para la reserva.
     *
     * @param idReserva Nuevo ID de la reserva.
     * @pre idReserva != null && !idReserva.isEmpty()
     * @post this.idReserva.equals(idReserva)
     */
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Representación en cadena de la reserva.
     *
     * @return Cadena con los detalles de la reserva.
     * @pre true
     * @post result != null
     */
    @Override
    public String toString() {
        return "Reserva{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", tipoEntrada='" + tipoEntrada + '\'' +
                ", horario='" + horario + '\'' +
                ", estado='" + estado + '\'' +
                ", nombreAsistente='" + nombreAsistente + '\'' +
                '}';
    }
}
