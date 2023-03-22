package es.aulanosa.gestionfp.repository;


import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoExternoRepository extends JpaRepository<AlumnoExterno, Integer> {

    @Query("select a from AlumnoExterno a join fetch a.curso c where c.estado = 'A'")
    List<AlumnoExterno> findAllAlumnoExternosActivos();

    AlumnoExterno findByNombre(String nombre);
    List<AlumnoExterno> findByNombreContaining(String nombre);
    List<AlumnoExterno> findByEmailContaining(String email);
    AlumnoExterno findByEmail(String email);
    AlumnoExterno findByTelefono(String telefono);
    AlumnoExterno findByUniversidad(String universidad);
    List<AlumnoExterno> findByUniversidadContaining(String universidad);
    AlumnoExterno findByTitulacion(String titulacion);
    List<AlumnoExterno> findByTitulacionContaining(String titulacion);
    AlumnoExterno findByEspecialidad(String especialidad);
    List<AlumnoExterno> findByEspecialidadContaining(String especialidad);



}
