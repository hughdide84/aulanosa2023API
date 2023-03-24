package es.aulanosa.gestionfp.repository;

import es.aulanosa.gestionfp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**

 La interface EmpresaRepository proporciona métodos para realizar operaciones CRUD sobre la entidad Empresa. Extiende la interface JpaRepository
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    /**
     Devuelve una lista de empresas con el nombre especificado.
     @param nombre El nombre de la empresa/empresas a buscar.
     @return Una lista de empresas que coinciden con el nombre especificado.
     */
    List<Empresa> findAllByNombre(String nombre);


    /**
     * Lista todas las empresas que tengan el idCUrso e idEstudios correspondientes
     * @param idCurso Id del curso representado en la BD
     * @param idEstudios Id del Estudio representado en la BD
     * @return Devuelve una lista de empresas
     */
    List<Empresa> findAllByIdCursoAndIdEstudios(Integer idCurso, Integer idEstudios);
}
