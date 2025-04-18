package com.example.demo;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Asistente asistente = new Asistente();
        Eventos recitales = new Recitales();
        Eventos obras = new Obras_de_Teatros();
        Eventos charlas = new Charlas_Y_Conferencias();

        while (true) {
            mostrarMenu();
            int opcion = leerInt("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> hacerReserva(asistente, recitales, obras, charlas);
                case 2 -> {
                    String nombre = leerLinea("Ingrese el nombre del cliente: ");
                    asistente.mostrarHistorialDe(nombre);
                }
                case 3 -> {
                    String clientesGratis = asistente.getClientesConDerechoAEntradaGratis();
                    if (clientesGratis.isEmpty()) {
                        System.out.println("No hay clientes con derecho a un próximo evento gratis.");
                    } else {
                        System.out.println("Clientes con próximo evento gratis: " + clientesGratis);
                    }
                }
                case 4 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void hacerReserva(Asistente asistente, Eventos... eventosDisponibles) {
        String cliente = leerLinea("Ingrese su nombre: ");
        Eventos evento = seleccionarEvento(eventosDisponibles);
        String tipo = seleccionarEntrada(evento);
        int cantidad = seleccionarCantidad(evento, tipo);
        String horario = seleccionarHorario(evento);
        String asist = seleccionarAsistente(asistente);

        if (evento.reservarEntrada(cliente, tipo, cantidad, horario, asist)) {
            asistente.guardarHistorial(cliente, evento);
            System.out.println("✅ Reserva realizada con éxito.");
        } else {
            System.out.println("❌ No se pudo realizar la reserva.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                \n========================================
                         SISTEMA DE GESTIÓN DE EVENTOS
                ========================================
                1. Hacer una reserva
                2. Ver historial de un cliente
                3. Ver clientes con proximo evento gratis.
                4. Salir
                ========================================""");
    }

    private static Eventos seleccionarEvento(Eventos[] eventos) {
        while (true) {
            System.out.println("Seleccione un evento:");
            for (int i = 0; i < eventos.length; i++) {
                System.out.printf("%d. %s%n", i + 1, eventos[i].getClass().getSimpleName());
            }
            int index = leerInt("Opción: ") - 1;
            if (index >= 0 && index < eventos.length) return eventos[index];
            System.out.println("Opción inválida.");
        }
    }

    private static String seleccionarEntrada(Eventos evento) {
        while (true) {
            evento.mostrarTipoEntradas();
            String entrada = leerLinea("Seleccione el tipo de entrada: ");
            if (evento.getEntradas().containsKey(entrada)) return entrada;
            System.out.println("Tipo de entrada inválido.");
        }
    }

    private static int seleccionarCantidad(Eventos evento, String tipo) {
        while (true) {
            int disponibles = evento.getEntradas().get(tipo);
            int cantidad = leerInt("Ingrese la cantidad de entradas: ");
            if (cantidad > 0 && cantidad <= disponibles) return cantidad;
            System.out.println("Cantidad inválida. Disponibles: " + disponibles);
        }
    }

    private static String seleccionarHorario(Eventos evento) {
        while (true) {
            evento.mostrarHorarios();
            String horario = leerLinea("Seleccione el turno: ");
            if (evento.getHorarios().containsKey(horario)) return horario;
            System.out.println("Turno inválido.");
        }
    }

    private static String seleccionarAsistente(Asistente asistente) {
        while (true) {
            System.out.println("Asistentes disponibles: " + asistente.getNombresAsistentes());
            String nombre = leerLinea("Seleccione un asistente: ");
            if (asistente.getNombresAsistentes().contains(nombre)) return nombre;
            System.out.println("Asistente inválido.");
        }
    }

    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerInt(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}
