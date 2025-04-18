# Sistema de Gestión de Eventos

Este proyecto consiste en un sistema de gestión de eventos y reservas, desarrollado con Java y Spring Boot. La aplicación permite registrar eventos, gestionar reservas por parte de los clientes, llevar un historial de asistencia y aplicar beneficios de fidelización.

## Solución a Problemas Frecuentes

- **Reservas no confirmadas o sin pago**: Para evitar que se reserven entradas sin confirmación o sin pago, todas las reservas quedan en estado *pendiente* hasta que el cliente confirme o abone la misma.

- **Entradas agotadas**: Cuando se agotan las entradas de un evento, el sistema impide realizar nuevas reservas, evitando así la sobreventa.

- **Fidelización de clientes**: Para premiar la constancia, el sistema lleva un historial de asistencia de cada cliente. Una vez que un cliente asiste a 5 eventos, se le otorga una entrada gratuita para el siguiente evento.

## Cómo ejecutar la aplicación

### Opción 1: Desde un IDE (recomendado: IntelliJ IDEA)
1. Abrir el proyecto en el IDE.
2. Ejecutar con el botón *Run/Debug*. Esto levantará la aplicación.
3. Acceder desde el navegador a `http://localhost:8080`.

### Opción 2: Desde la terminal
1. Posicionarse en el directorio raíz del proyecto.
2. Ejecutar el siguiente comando:
   ```
   ./mvnw spring-boot:run
   ```
3. Ingresar a `http://localhost:8080` en el navegador.

> **Nota:** Es necesario tener Java JDK 17 o superior instalado. Para verificarlo, ejecutar `java -version`.

### Ejecución modo consola (modelo de reserva por línea de comandos)

1. Compilar el proyecto:
   ```
   javac -d out src/main/java/com/example/demo/Main.java
   ```
2. Ejecutar:
   ```
   java com.example.demo.Main
   ```
3. "La mas simple": correr el Main con el boton Run del IDE.
---

## Dependencias

- **Java JDK 17+**
- **Maven**
- **Spring Boot Starter Web** – Para desarrollo de aplicaciones web.
- **Thymeleaf** – Motor de plantillas para HTML.
- **Lombok** – Para simplificar el código Java.
- **Spring Boot DevTools** *(opcional)* – Recarga automática durante desarrollo.
- **Spring Data JPA + H2** – Persistencia de datos en memoria.
- **JUnit 5.8.1** – Para pruebas automatizadas.

---

## Diseño del Sistema (UML - Sistema de Gestión de Eventos)

### Estructura General

El sistema está orientado a la gestión de eventos y reservas de entradas. Contempla:

- Diferentes tipos de eventos: Recitales, Obras de Teatro, Charlas y Conferencias.
- Gestión de reservas por cliente.
- Control de historial de asistencia.
- Reglas de negocio como confirmación de reserva y entradas gratuitas por fidelidad.

### Clases y Relaciones

#### 1. `Eventos` *(Clase abstracta)*
- Superclase de: `Recitales`, `ObrasDeTeatro`, `CharlasYConferencias`.
- Contiene:
    - `entradas`: HashMap<String, Integer>
    - `reservas`: List<Reserva>
    - `horarios`: HashMap<String, String>
- Métodos principales: `agregarTipoEntrada()`, `agregarHorario()`, `reservarEntrada()`, `confirmarReserva()`, `cancelarReserva()`

#### 2. `Reserva`
- Representa una reserva individual.
- Atributos: `nombreCliente`, `tipoEntrada`, `cantidad`, `estado`, `horario`.
- Métodos: `confirmar()`, `cancelar()`

#### 3. `Asistente`
- Controlador del historial de asistencia de clientes.
- Atributo: `historialClientes`: HashMap<String, List<String>>
- Métodos: `mostrarHistorialDe()`, `getClientesConDerechoAEntradaGratis()`

#### 4. `Cliente`
- Representa al usuario que realiza reservas.
- Atributo: `nombre`
- Método: `getNombre()`

#### 5. `Gestor_Eventos`
- Controlador principal del sistema.
- Métodos: `cancelarReserva()`, `mostrarReservas()`, `mostrar_historial_cliente()`, `guardarHistorial()`

### Relaciones Principales

- `Eventos` es clase base de los diferentes tipos de eventos.
- `Eventos` contiene múltiples `Reserva`.
- `Asistente` administra el historial y se comunica con `Cliente` y `Eventos`.
- `Gestor_Eventos` coordina operaciones generales.
- `Reserva` se vincula al `Cliente` a través de su nombre.

---

## Principios de Diseño

- **Abstracción**: La clase `Eventos` funciona como base común para distintos tipos de eventos.
- **Responsabilidad única**: Cada clase tiene una función claramente definida.
- **Escalabilidad**: El sistema está preparado para agregar nuevas funcionalidades o tipos de eventos fácilmente.
- **Mantenibilidad**: El diseño modular permite entender y modificar cada componente de forma independiente.

> Dividir el comportamiento en distintas clases permite una mejor organización, facilita la extensión del sistema y mejora la legibilidad del código.

