package com.api.serviciosuniversidad.repositories;

import com.api.serviciosuniversidad.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, String> {
}
