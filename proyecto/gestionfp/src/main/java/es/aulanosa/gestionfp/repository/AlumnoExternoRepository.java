package es.aulanosa.gestionfp.repository;


import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoExternoRepository extends JpaRepository<AlumnoExterno, Integer> {

    @Query("select a from AlumnoExterno a join fetch a.curso c where c.estado = 'A'")
    List<AlumnoExterno> findAllAlumnoExternosActivos();

    AlumnoExterno findFirstByNombre(String nombre);
    List<AlumnoExterno> findByNombreContaining(String nombre);
    List<AlumnoExterno> findByEmailContaining(String email);
    AlumnoExterno findFirstByEmail(String email);
    @Query("select a from AlumnoExterno a where a.telefono = ?1")
    AlumnoExterno findFirstByTelefono(String telefono);
    List<AlumnoExterno> findByUniversidad(String universidad);
    List<AlumnoExterno> findByUniversidadContaining(String universidad);
    List<AlumnoExterno> findByTitulacion(String titulacion);
    List<AlumnoExterno> findByTitulacionContaining(String titulacion);
    List<AlumnoExterno> findByEspecialidad(String especialidad);
    List<AlumnoExterno> findByEspecialidadContaining(String especialidad);



}
