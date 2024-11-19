package ExamenParcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ExamenParcial.modelo.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
}
