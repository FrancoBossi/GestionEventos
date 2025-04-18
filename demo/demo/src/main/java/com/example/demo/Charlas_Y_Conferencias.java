package com.example.demo;

public class Charlas_Y_Conferencias extends  Eventos {

    public Charlas_Y_Conferencias() {
        agregarTipoEntrada("SinAccesoMeet&Greet", 100);
        agregarTipoEntrada("ConAccesoMeet&Greet", 50);
        agregarHorario("Mañana", "08:00 AM");
        agregarHorario("Tarde", "01:00 PM");
        agregarHorario("Noche", "09:00 PM");
        this.nombreEvento = "Charlas y Conferencias";
    }


}