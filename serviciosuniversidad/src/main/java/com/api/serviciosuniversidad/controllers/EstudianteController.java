package com.api.serviciosuniversidad.controllers;

import com.api.serviciosuniversidad.models.Estudiante;
import com.api.serviciosuniversidad.services.EstudianteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    private final EstudianteService servicio;

    public EstudianteController(EstudianteService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return servicio.listar();
    }

    @GetMapping("/{matricula}")
    public Estudiante consultarEstudiante(@PathVariable String matricula) {
        return servicio.consultarEstudiante(matricula);
    }

    @PostMapping
    public Estudiante registrarEstudiante(@RequestBody Estudiante estudiante) {
        return servicio.registrarEstudiante(estudiante);
    }

    @GetMapping("/{matricula}/inscripcion")
    public boolean verificarInscripcion(@PathVariable String matricula) {
        return servicio.verificarInscripcion(matricula);
    }

    @PostMapping("/{matricula}/constancia/solicitar")
    public String solicitarConstancia(@PathVariable String matricula) {
        return servicio.solicitarConstancia(matricula);
    }

    @PostMapping("/{matricula}/constancia/pago")
    public String registrarPagoConstancia(@PathVariable String matricula) {
        return servicio.registrarPagoConstancia(matricula);
    }

    @PostMapping("/{matricula}/constancia/pdf")
    public String generarPdfConstancia(@PathVariable String matricula) {
        return servicio.generarPdfConstancia(matricula);
    }

    @PostMapping("/{matricula}/constancia/correo")
    public String enviarCorreoConstancia(@PathVariable String matricula) {
        return servicio.enviarCorreoConstancia(matricula);
    }
}
