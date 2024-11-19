package ExamenParcial.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Alumno")
@Data
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double nota;
}

