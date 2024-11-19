package ExamenParcial.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ExamenParcial.modelo.Alumno;
import ExamenParcial.servicio.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    @GetMapping
    public List<Alumno> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alumno add(@RequestBody Alumno alumno) {
        return service.add(alumno);
    }

    @PutMapping
    public ResponseEntity<Alumno> update(@RequestBody Alumno alumno) {
        try {
            return ResponseEntity.ok(service.update(alumno));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
