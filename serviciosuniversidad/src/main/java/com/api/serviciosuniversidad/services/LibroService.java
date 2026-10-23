
package com.api.serviciosuniversidad.services;

import com.api.serviciosuniversidad.models.Libro;
import com.api.serviciosuniversidad.repositories.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository repositorio;

    public LibroService(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Libro> listar() {
        return repositorio.findAll();
    }

    public Libro consultarLibro(String codigo) {
        return repositorio.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "LIBRO_NO_ENCONTRADO"
                ));
    }

    public Libro consultarDisponibilidad(String codigo) {
        return consultarLibro(codigo);
    }

    public Libro registrarLibro(Libro libro) {
        return repositorio.save(libro);
    }

    @Transactional
    public Libro marcarPrestado(String codigo) {
        Libro libro = consultarLibro(codigo);

        if (!Boolean.TRUE.equals(libro.getDisponible())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "LIBRO_NO_DISPONIBLE"
            );
        }

        libro.setDisponible(false);
        return repositorio.save(libro);
    }

    @Transactional
    public Libro marcarDisponible(String codigo) {
        Libro libro = consultarLibro(codigo);

        if (Boolean.TRUE.equals(libro.getDisponible())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "LIBRO_NO_PRESTADO"
            );
        }

        libro.setDisponible(true);
        return repositorio.save(libro);
    }
}
