package ExamenParcial.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ExamenParcial.modelo.Alumno;
import ExamenParcial.repository.AlumnoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    

    @Autowired
    private AlumnoRepository repository;

    public List<Alumno> getAll() {
        return repository.findAll();
    }

    public Optional<Alumno> getById(int id) {
        return repository.findById(id);
    }

    public Alumno add(Alumno alumno) {
        return repository.save(alumno);
    }

    public Alumno update(Alumno alumno) {
        if (repository.existsById(alumno.getId())) {
            return repository.save(alumno);
        }
        throw new RuntimeException("Alumno no encontrado");
    }

    public void delete(int id) {
        // Verificamos si el alumno existe
        if (!repository.existsById(id)) {
            throw new RuntimeException("Alumno con id " + id + " no encontrado");
        }
        // Si existe, se elimina
        repository.deleteById(id);
    }
    
}
