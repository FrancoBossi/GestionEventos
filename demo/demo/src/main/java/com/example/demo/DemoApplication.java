package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/*
* El codigo consiste en un sistema de gestion de eventos.
*
* Solucion de problemas:
* -Los asistentes llaman o escriben para reservar, y a veces se olvidan de pagar o
confirman tarde. Para solucionarlo, si hizo que todas las reservas queden en pendiente, entonces hasta que el cliente no confirme la reserva,o no la pague, no se le confirma la reserva.
* -En algunos eventos, las entradas se agotan, pero seguimos recibiendo reservas
sin control. Para la solucion: directamente no se puede reservar si no hay entradas disponibles.
* -Queremos fomentar la fidelización, premiando a quienes asisten a varios
eventos. Para la soulcion: l aweb lleva una historial de cada cliente, y si asiste a 5 eventos, el proximo evento es gratis.
* Para correr la web:
1- El entorno debe dejar correr directamente con el boton de run/debug.(Recomiendo tener Intellij IDEA). Esto levantara la Web, y nosotros en el navegador deberemos escribir localhost:8080
2- Ejecutar en la terminal en el directorio del proyecto: ./mvnw spring-boot:run
Y despues ir al http://localhost:8080. (Si no se tiene JDK instalado ni java en la computadora, no funciona)
*
* Tambien se puede correr el proyecto desde la terminal, hize un modelado de como seria el sistema de reservas:
* - el IDE debe dejar correrlo con el boton  de run/debug.
* -Si se quiere correr desde la terminal, se debe ejecutar el siguiente comando:
* javac -d out src/main/java/com/example/demo/Main.java (esto compila el proyecto)
* y para ejecutarlo, se debe ejecutar el siguiente comando: java com.example.demo.Main



Dependencias instaladas:
-Maven
-JDK (En este caso el proyecto ya viene con JDK default), Java JDK 17 o superior,aunque en caso de no tenerlo deben instalarlo en si SO, y para verificar su instalacion hacer: java -version.
-Junit 5.8.1
------
Spring Web

Thymeleaf (motor de plantillas para HTML)
* Spring Boot Starter Web (para crear aplicaciones web)
Lombok
Spring Boot DevTools (opcional, para recarga automática)

Spring Data JPA + H2 (si quieres una base de datos)
*
* Idea del planteamiento:
* EXPLICACION DEL DIAGRAMA UML - SISTEMA DE GESTION DE EVENTOS

ESTRUCTURA GENERAL
Este sistema está diseñado para gestionar eventos y sus reservas. Incluye:
- Eventos y sus distintos tipos (Recitales, Obras de Teatro, Charlas y Conferencias)
- Reservas realizadas por clientes
- Clientes y asistentes que interactúan con el sistema
- Un gestor que administra las operaciones principales

CLASES Y RELACIONES

1. Eventos (Clase Abstracta)
- Superclase de: Recitales, Obras_de_Teatro, Charlas_y_Conferencias
- Contiene:
  - entradas: HashMap<String, Integer>
  - reservas: List<Reserva>
  - horarios: HashMap<String, String>
- Métodos:
  - agregarTipoEntrada(), agregarHorario()
  - reservarEntrada(), confirmarReserva(), cancelarReserva()
- Relación:
  - Herencia con las subclases
  - Agregación con Reserva (un evento contiene muchas reservas)

2. Reserva
- Representa una reserva específica
- Atributos:
  - nombreCliente, tipoEntrada, cantidad, estado, horario
- Métodos:
  - cancelar(), confirmar()
- Relación:
  - Asociada a Eventos (los eventos contienen reservas)
  - Contiene datos de Cliente por nombre

3. Asistente
- Controlador de historial de clientes
- Atributo:
  - historialClientes: HashMap<String, List<String>>
- Métodos:
  - mostrarHistorialDe(), getClientesConDerechoAEntradaGratis()
- Relación:
  - Asociado a Reserva, Eventos y Cliente (por nombre)

4. Cliente
- Representa un cliente del sistema
- Atributo: nombre
- Método: getNombre()
- Relación:
  - Asociado de forma indirecta a Reserva y Asistente

5. Gestor_Eventos
- Controlador general del sistema
- Métodos:
  - cancelarReserva(), mostrarReservas(), mostrar_historial_cliente(), guardarHistorial()
- Relación:
  - Asociado a Asistente y Eventos

RESUMEN DE RELACIONES
- Recitales, Obras_de_Teatro, Charlas_y_Conferencias heredan de Eventos
- Eventos agrega múltiples Reservas
- Asistente gestiona historial y se comunica con Cliente y Eventos
- Gestor_Eventos coordina acciones usando Asistente y Eventos
- Reserva utiliza Cliente a través del nombre

IDEAS CLAVE DEL DISEÑO
- Abstracción: Eventos como clase base común
- Separación de responsabilidades entre clases
- Escalabilidad: Fácil de ampliar con nuevos tipos de eventos o lógicas
*- Las ventajas de dividir el comportamiento en varias clases, es que delegamos el comportamiento.
* Esto permite que cada clase tenga su propia responsabilidad y no se mezclen las responsabilidades de cada una.
* Esto hace que el código sea más fácil de mantener y extender.


--------*/
