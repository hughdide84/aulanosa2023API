package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnoExternoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import es.aulanosa.gestionfp.service.AlumnoExternoServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Clase que implementa la interfaz AlumnoExternoService.
 */
@RestController
@RequestMapping("api/alumnoExterno")

public class AlumnoExternoController {

    @Autowired
    private AlumnoExternoServiceImp service;
    /**
     * Inserta un alumnoExterno a la tabla
     * @param alumnoExternoDTO
     * @return el alumno externo insertado
     */
    @PostMapping("/")
    @Operation(summary = "Inserta un alumnoExterno a la tabla")
    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
    public ResponseEntity<?> alta(@RequestBody AlumnoExternoDTO alumnoExternoDTO){
        try{
            AlumnoExterno alumnosExternos = alumnoExternoDTO.convertirModel();
            AlumnoExterno alumnosExternosGuardado = service.guardar(alumnosExternos);
            alumnoExternoDTO.crearDTO(alumnosExternosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    /**
     * Devuelve un id de un alumno externo especifico
     * @param id
     * @return un alumno externo
     */
    @GetMapping("/{id}")
    @Operation(summary = "Devuelve un id de un alumno externo especifico")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que lo haga devuelve el objeto recuperado de la BD
    public ResponseEntity<?> consulta(@PathVariable Integer id){
        try{
            Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);
            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);
        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

        }
    }
    /**
     * Modifica una fila de la tabla alumnoExterno a partir del parametro que se le pasa
     * @param alumnoExternoDTO
     * @return un alumno modificado
     */
    @PutMapping("/")
    @Operation(summary = "Modifica una fila de la tabla alumnoExterno a partir del parametro que se le pasa")
    //se le pasa un objeto completo por POST, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes
    public ResponseEntity<?> editar(@RequestBody AlumnoExternoDTO alumnoExternoDTO){
        try{
            AlumnoExterno alumnosExternos = alumnoExternoDTO.convertirModel();
            //no hay control de errore porque al listar por id si no existe ese id, salta un error directamente avisandolo
            Optional<AlumnoExterno> alumnoExternoRercuperado = service.listarPorId(alumnoExternoDTO.getId());
            service.guardar(alumnosExternos);
            AlumnoExternoDTO alumnoExternoDTORecuperado = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnosExternos);

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido un Alumno válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

        }
    }
    /**
     * Elimina un id de un alumno externo especifico
     * @param id
     * @return un alumno externo
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Borra todos los alumnos externos")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma
    public ResponseEntity<?> eliminar(@PathVariable int id){
        try{
            Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);
            service.eliminar(alumnosExternos.get().getId());

            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnosExternos.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoExternoDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }
    /**
     * Devuelve todos los campos de la tabla alumnoExterno
     * @return Todos los  alumnos externos
     */
    @GetMapping("/")
    @Operation(summary = "Lista todos los alumnos externos")
    //lista todos los campos de la BD, en caso de que esta este vacia, devuelve un error personalizado
    public ResponseEntity<?> listarTodo(){
        if(!service.listarTodo().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTodo());
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }
    /**
     * Devuelve todos los alumnosExternos asociados a un curso especificado como parametro
     * @param idCurso el id del curso que queremos filtrar
     * @return Todos los  alumnos externos que pertenecen a ese curso
     */
    @GetMapping("/cursoEs/{idCurso}")
    @Operation(summary = "Lista todos los alumnos externos filtrados por idCurso")
    public ResponseEntity<?> listarAlumnosExternosCurso(@PathVariable int idCurso) throws NoSeHaEncontradoException {
        List<AlumnoExterno> a = service.buscarPorIdCurso(idCurso);

        if(!a.isEmpty()){
            List<AlumnoExternoDTO> aeDTO = new ArrayList<>();
            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            for (AlumnoExterno ae:
                 a) {
                aeDTO.add(alumnoExternoDTO.crearDTO(ae));
            }

            return ResponseEntity.status(HttpStatus.OK).body(aeDTO);
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0006", "No hay resultados en la base de datos para los criterios de busqueda");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }

    }

}
