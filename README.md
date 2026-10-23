# ServiciosUniversidad
API.-ServiciosUniversidad
## Descripción de la API
El proyecto **Servicios Universidad** es una API REST académica diseñada para organizar y gestionar operaciones clave del ámbito escolar, específicamente enfocada en los módulos de **Estudiantes** (trámite de constancias con flujo simulado) y **Biblioteca** (préstamo y devolución de libros)[cite: 2]. 

El objetivo principal es aplicar los principios del diseño orientado a servicios, estructurando la lógica de negocio en capas claras (`Controller`, `Service`, `Repository` y `Model`)[cite: 2].

---

## Tecnologías utilizadas
* **Java**[cite: 10]
* **Spring Boot** (Spring Web, Spring Data JPA)[cite: 2, 10]
* **H2 Database** (Base de datos en memoria para pruebas rápidas)[cite: 2]
* **Postman** (Herramienta para pruebas y validación de endpoints)[cite: 8, 10]

---

## Pasos de instalación y ejecución
Sigue estos pasos para clonar y ejecutar el proyecto en tu entorno local:

 **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/200810030-JFRS/Servicios](https://github.com/200810030-JFRS/Servicios) Universidad.git
   cd ServiciosUniversidad
   mvn spring-boot:run
   server.port=8080
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.h2.console.enabled=true

## 4. Listado de endpoints

Módulo de Estudiantes

POST /api/v1/estudiantes - Registrar un nuevo estudiante.
GET /api/v1/estudiantes/{matricula}/inscripcion - Verificar si la inscripción del estudiante está activa.
POST /api/v1/estudiantes/{matricula}/constancia/solicitar - Iniciar la solicitud de constancia.
POST /api/v1/estudiantes/{matricula}/constancia/pago - Registrar el pago simulado del trámite.
POST /api/v1/estudiantes/{matricula}/constancia/pdf - Simular la generación del documento PDF.
POST /api/v1/estudiantes/{matricula}/constancia/correo - Simular el envío del documento por correo. 

Módulo de Biblioteca

POST /api/v1/libros - Registrar un nuevo libro.
POST /api/v1/libros/{codigo}/prestar - Marcar un libro como prestado (disponible = false).
POST /api/v1/libros/{codigo}/devolver - Registrar la devolución de un libro (disponible = true).   


Ejemplos de peticiones y respuestas

A. Registrar un estudiantePetición: POST /api/v1/estudiantes   Body (JSON):JSON{
  "matricula": "202600001",
  "nombre": "Juan Perez",
  "activo": true
}
Respuesta (HTTP 200 OK):   JSON{
  "matricula": "202600001",
  "nombre": "Juan Perez",
  "activo": true
}
B. Solicitar constancia de estudiosPetición:

POST /api/v1/estudiantes/202600001/constancia/solicitar   Respuesta (HTTP 200 OK):   PlaintextSolicitud registrada para Juan Perez. Pendiente de pago simulado.

C. Registrar libro en bibliotecaPetición: POST /api/v1/libros   Body (JSON):JSON{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": true
}
Respuesta (HTTP 200 OK):   JSON{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": true
}
D. Devolver libroPetición: POST /api/v1/libros/L001/devolver   Respuesta (HTTP 200 OK):   JSON{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": true
}
