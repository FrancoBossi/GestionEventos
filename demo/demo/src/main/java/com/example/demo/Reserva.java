package com.example.demo;

import lombok.Setter;

import java.util.UUID;

public class Reserva {
    private String nombreCliente;
    private String tipoEntrada;
    private String nombreAsistente;
    private String idReserva;
    @Setter
    private String estado; // "Pendiente", "Confirmada", "Cancelada"
    private String horario;

    public Reserva(String nombreCliente, String tipoEntrada, String horario, String nombreAsistente) {
        this.nombreCliente = nombreCliente;
        this.tipoEntrada = tipoEntrada;
        this.horario = horario;
        this.estado = "Pendiente";
        this.nombreAsistente = nombreAsistente;
        this.idReserva = UUID.randomUUID().toString(); // Generar un ID único basado en la hora actual
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }



    public String getEstado() {
        return estado;
    }

    public String getHorario() {
        return horario;
    }

    public void confirmar() {
        this.estado = "Confirmada";
    }

    public void cancelar() {
        this.estado = "Cancelada";
    }
    public String getNombreAsistente() {
        return nombreAsistente;
    }


    // Getters y setters
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

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
