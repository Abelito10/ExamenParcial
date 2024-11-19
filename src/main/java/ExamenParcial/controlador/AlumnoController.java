package ExamenParcial.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ExamenParcial.modelo.Alumno;
import ExamenParcial.servicio.AlumnoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    private AlumnoService service;

    @GetMapping
    public List<Alumno> getAll() {
        logger.info("Obteniendo todos los alumnos");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable int id) {
        logger.info("Obteniendo el alumno con ID: {}", id);
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("No se encontro el alumno con ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public Alumno add(@RequestBody Alumno alumno) {
        logger.info("Añadiendo un nuevo alumno: {}", alumno.getNombre());
        return service.add(alumno);
    }

    @PutMapping
    public ResponseEntity<Alumno> update(@RequestBody Alumno alumno) {
        try {
            logger.info("Actualizando los datos del alumno: {}", alumno.getNombre());
            return ResponseEntity.ok(service.update(alumno));
        } catch (RuntimeException e) {
            logger.error("Error al actualizar el alumno: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            logger.info("Eliminando el alumno con ID: {}", id);
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Error al eliminar el alumno con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
