package com.example.demo;

public class Obras_de_Teatros extends Eventos {

    public  Obras_de_Teatros() {
        agregarTipoEntrada("General", 100);
        agregarTipoEntrada("VIP", 50);
        agregarHorario("Mañana", "10:00 AM");
        agregarHorario("Tarde", "03:00 PM");
        agregarHorario("Noche", "08:00 PM");
        this.nombreEvento = "Obras de Teatros";


    }


}
