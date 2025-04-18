package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {

    private Map<String, Eventos> eventosDisponibles = new LinkedHashMap<>();
    private Asistente asistente = new Asistente();

    public HomeController() {
        eventosDisponibles.put("Recitales", new Recitales());
        eventosDisponibles.put("Obras de teatro", new Obras_de_Teatros());
        eventosDisponibles.put("Charlas", new Charlas_Y_Conferencias());
    }

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("eventos", eventosDisponibles);
        model.addAttribute("eventoSeleccionado", "");
        model.addAttribute("entradasDisponibles", Collections.emptyMap());
        model.addAttribute("horariosDisponibles", Collections.emptyMap());
        Map<String, List<Reserva>> reservasPorEvento = new LinkedHashMap<>();
        for (Map.Entry<String, Eventos> entry : eventosDisponibles.entrySet()) {
            reservasPorEvento.put(entry.getKey(), entry.getValue().mostrarReservas());
        }
        model.addAttribute("reservasPorEvento", reservasPorEvento);

        model.addAttribute("reservaExitosa", null);
        model.addAttribute("asistentesDisponibles", asistente.getNombresAsistentes()); // nuevo
        return "formulario";
    }

    @PostMapping("/")
    public String procesarFormulario(
            @ModelAttribute Cliente cliente,
            @RequestParam String eventoSeleccionado,
            @RequestParam String tipoEntrada,
            @RequestParam int cantidad,
            @RequestParam String turno,
            @RequestParam String nombreAsistente,
            Model model
    ) {
        Eventos evento = eventosDisponibles.get(eventoSeleccionado);

        // Validar asistente
        boolean asistenteValido = asistente.getNombresAsistentes().contains(nombreAsistente);
        boolean reservaExitosa = false;

        if (asistenteValido) {
            reservaExitosa = evento.reservarEntrada(
                    cliente.getNombre(),
                    tipoEntrada,
                    cantidad,
                    turno,
                    nombreAsistente
            );

            if (reservaExitosa) {
                asistente.guardarHistorial(cliente.getNombre(), evento);
            }
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("eventos", eventosDisponibles);
        model.addAttribute("eventoSeleccionado", eventoSeleccionado);
        model.addAttribute("entradasDisponibles", evento.getEntradas());
        model.addAttribute("horariosDisponibles", evento.getHorarios());
        Map<String, List<Reserva>> reservasPorEvento = new LinkedHashMap<>();
        for (Map.Entry<String, Eventos> entry : eventosDisponibles.entrySet()) {
            reservasPorEvento.put(entry.getKey(), entry.getValue().mostrarReservas());
        }
        model.addAttribute("reservasPorEvento", reservasPorEvento);

        model.addAttribute("reservaExitosa", reservaExitosa);
        model.addAttribute("asistentesDisponibles", asistente.getNombresAsistentes());

        return "formulario";
    }

    @PostMapping("/confirmar")
    public String confirmarReserva(
            @RequestParam String idReserva,
            @RequestParam String eventoSeleccionado,
            Model model
    ) {
        Eventos evento = eventosDisponibles.get(eventoSeleccionado);
        if (evento != null) {
            evento.confirmarReserva(idReserva);
        }

        // Recargar datos para la vista
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("eventos", eventosDisponibles);
        model.addAttribute("eventoSeleccionado", eventoSeleccionado);
        model.addAttribute("entradasDisponibles", evento != null ? evento.getEntradas() : Collections.emptyMap());
        model.addAttribute("horariosDisponibles", evento != null ? evento.getHorarios() : Collections.emptyMap());
        model.addAttribute("reservaExitosa", null);
        model.addAttribute("asistentesDisponibles", asistente.getNombresAsistentes());


        Map<String, List<Reserva>> reservasPorEvento = new LinkedHashMap<>();
        for (Map.Entry<String, Eventos> entry : eventosDisponibles.entrySet()) {
            reservasPorEvento.put(entry.getKey(), entry.getValue().mostrarReservas());
        }
        model.addAttribute("reservasPorEvento", reservasPorEvento);

        return "formulario";
    }

}


