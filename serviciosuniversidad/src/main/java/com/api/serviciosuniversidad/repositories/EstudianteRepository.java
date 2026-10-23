package com.api.serviciosuniversidad.repositories;

import com.api.serviciosuniversidad.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
}
