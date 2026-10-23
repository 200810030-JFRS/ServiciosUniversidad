package com.api.serviciosuniversidad.services;

import com.api.serviciosuniversidad.models.Estudiante;
import com.api.serviciosuniversidad.repositories.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository repositorio;

    public EstudianteService(EstudianteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Estudiante> listar() {
        return repositorio.findAll();
    }

    public Estudiante consultarEstudiante(String matricula) {
        String canonica = matricula.trim();
        return repositorio.findById(canonica)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ESTUDIANTE_NO_ENCONTRADO"));
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        return repositorio.save(estudiante);
    }

    public boolean verificarInscripcion(String matricula) {
        return consultarEstudiante(matricula).isActivo();
    }

    private final java.util.Map<String, Integer> tramites = new java.util.concurrent.ConcurrentHashMap<>();

    public String solicitarConstancia(String matricula) {
        Estudiante estudiante = consultarEstudiante(matricula);
        if (!estudiante.isActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ESTUDIANTE_NO_INSCRITO");
        }
        tramites.put(estudiante.getMatricula(), 1);
        return "Solicitud registrada para " + estudiante.getNombre() + ". Pendiente de pago simulado.";
    }

    public String registrarPagoConstancia(String matricula) {
        exigirEtapa(matricula, 1);
        tramites.put(matricula, 2);
        return "Pago simulado registrado. No se realizo ningun cobro.";
    }

    public String generarPdfConstancia(String matricula) {
        exigirEtapa(matricula, 2);
        tramites.put(matricula, 3);
        return "PDF simulado generado para " + consultarEstudiante(matricula).getNombre() + ".";
    }

    public String enviarCorreoConstancia(String matricula) {
        exigirEtapa(matricula, 3);
        tramites.put(matricula, 4);
        return "Correo simulado enviado a " + consultarEstudiante(matricula).getCorreo() + ".";
    }

    private void exigirEtapa(String matricula, int etapa) {
        consultarEstudiante(matricula);
        if (tramites.getOrDefault(matricula, 0) != etapa) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PASO_ANTERIOR_PENDIENTE");
        }
    }
}
