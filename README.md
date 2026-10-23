# ServiciosUniversidad
API.-ServiciosUniversidad

1. Descripción de la API Servicios Universidad es una API REST académica desarrollada con el objetivo de aplicar los principios del Diseño Orientado a Servicios (SOA) mediante la identificación de operaciones, especificación de reglas de negocio y propuesta de patrones de coordinación. La API se encarga de organizar y gestionar operaciones distribuidas en dos módulos principales.

2. Tecnologías utilizadasLenguaje de Programación: Java (versión 17+)Framework Backend: Spring Boot (Spring Web, Spring Data JPA)Base de Datos: H2 Database Engine (Base de datos relacional en memoria)Gestor de Dependencias: MavenHerramientas de Prueba: Postman (para pruebas de carga e interacción con endpoints REST)

3. Pasos de instalación y ejecuciónPrerrequisitosJava JDK 17 o superior instalado.Apache Maven 3.8+ instalado (o utilizar el wrapper ./mvnw incluido).Git instalado.PasosClonar el repositorio:git clone https://github.com/200810030-JFRS/Servicios%20Universidad.git
cd "Servicios Universidad"
Compilar el proyecto:mvn clean package
Ejecutar la aplicación:mvn spring-boot:run
Acceso:La API estará escuchando peticiones HTTP en: http://localhost:8080Consola Web H2 (si se habilita en propiedades): http://localhost:8080/h2-console

4. Variables de entorno necesariasLa aplicación utiliza la base de datos H2 en memoria, por lo que no requiere variables de entorno obligatorias para su ejecución básica.Si deseas personalizar el comportamiento predeterminado, puedes agregar o modificar las siguientes claves en el archivo src/main/resources/application.properties o pasarlas como variables de entorno al sistema:Variable / PropiedadValor por defectoDescripciónserver.port8080Puerto en el que se ejecuta el servidor Spring Boot.spring.datasource.urljdbc:h2:mem:testdbURL de conexión de la base de datos H2 en memoria.spring.datasource.driverClassNameorg.h2.DriverDriver JDBC de H2.spring.datasource.usernamesaUsuario predeterminado de la base de datos.spring.datasource.password(Vacío)Contraseña de la base de datos.spring.h2.console.enabledtrueHabilita la interfaz web de administración de H2.

5. Listado de endpoints Módulo: Estudiantes y Constancias (/api/v1/estudiantes) MétodoEndpointDescripción
   POST/api/v1/estudiantesRegistra un nuevo estudiante en el sistema.
   GET/api/v1/estudiantes/{matricula}/inscripcion Verifica si el estudiante existe y está activo.
   POST/api/v1/estudiantes/{matricula}/constancia/solicitar Inicia el trámite de solicitud de constancia.
   POST/api/v1/estudiantes/{matricula}/constancia/pago Confirma el pago simulado de la constancia.
   POST/api/v1/estudiantes/{matricula}/constancia/pdf Simula la generación del documento PDF de la constancia.
   POST/api/v1/estudiantes/{matricula}/constancia/correo Simula el envío por correo electrónico de la constancia.

Módulo: Biblioteca (/api/v1/libros)
MétodoEndpointDescripción
POST/api/v1/librosRegistra un nuevo libro en el catálogo de la biblioteca.
POST/api/v1/libros/{codigo}/prestarPresta un libro cambiando su estado a no disponible (disponible = false).
POST/api/v1/libros/{codigo}/devolverDevuelve un libro asignando su disponibilidad a true.

6. Ejemplos de peticiones y respuestas1. Registrar un estudianteEndpoint:
  
POST /api/v1/estudiantesHeaders: Content-Type: application/jsonCuerpo de la petición (JSON):{
  "matricula": "202600001",
  "nombre": "Juan Perez",
  "activo": true
}
Respuesta (200 OK):{
  "matricula": "202600001",
  "nombre": "Juan Perez",
  "activo": true
}

Verificar estado de inscripciónEndpoint: GET /api/v1/estudiantes/202600001/inscripcionRespuesta (200 OK):true

Solicitar trámite de constanciaEndpoint: POST /api/v1/estudiantes/202600001/constancia/solicitarRespuesta (200 OK):Solicitud registrada para Juan Perez. Pendiente de pago simulado.

11. Flujo de simulación de constancia (Pago, PDF y Correo)Petición (Pago): POST /api/v1/estudiantes/202600001/constancia/pagoRespuesta (200 OK): "Pago simulado registrado. No se realizo ningun cobro."Petición (PDF): POST /api/v1/estudiantes/202600001/constancia/pdfRespuesta (200 OK): "PDF simulado generado para Juan Perez."Petición (Correo): POST /api/v1/estudiantes/202600001/constancia/correoRespuesta (200 OK): "Correo simulado enviado a juan@universidad.edu."5. Registrar un libro en bibliotecaEndpoint: POST /api/v1/librosHeaders: Content-Type: application/jsonCuerpo de la petición (JSON):{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": true
}
Respuesta (200 OK):{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": true
}
12. Prestar y Devolver libroPrestar Libro: POST /api/v1/libros/L001/prestarRespuesta (200 OK):{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": false
}
Devolver Libro: POST /api/v1/libros/L001/devolverRespuesta (200 OK):{
  "codigo": "L001",
  "titulo": "Introduccion a SOA",
  "autor": "Juan Lopez",
  "disponible": true
}
