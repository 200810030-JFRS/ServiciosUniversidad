
package com.api.serviciosuniversidad.controllers;

import com.api.serviciosuniversidad.models.Libro;
import com.api.serviciosuniversidad.services.LibroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private final LibroService servicio;

    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Libro> listar() {
        return servicio.listar();
    }

    @GetMapping("/{codigo}")
    public Libro consultarLibro(@PathVariable String codigo) {
        return servicio.consultarLibro(codigo);
    }

    @GetMapping("/{codigo}/disponibilidad")
    public Libro consultarDisponibilidad(@PathVariable String codigo) {
        return servicio.consultarDisponibilidad(codigo);
    }


    @PostMapping
    public Libro registrarLibro(@RequestBody Libro libro) {
        return servicio.registrarLibro(libro);
    }

    @PostMapping("/{codigo}/prestar")
    public Libro prestarLibro(@PathVariable String codigo) {
        return servicio.marcarPrestado(codigo);
    }

    @PostMapping("/{codigo}/devolver")
    public Libro devolverLibro(@PathVariable String codigo) {
        return servicio.marcarDisponible(codigo);
    }
}
