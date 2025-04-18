package com.example.demo;

public class Recitales extends Eventos {

    public  Recitales() {
        agregarTipoEntrada("Campo", 100);
        agregarTipoEntrada("Platea", 50);
        agregarTipoEntrada("Palco", 20);
        agregarHorario("Mañana", "11:00 AM");
        agregarHorario("Tarde", "07:00 PM");
        agregarHorario("Noche", "11:00 PM");
        this.nombreEvento = "Recitales";
    }




}
