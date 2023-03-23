package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.ProyectoDTO;
import es.aulanosa.gestionfp.model.Proyectos;
import es.aulanosa.gestionfp.repository.ProyectosRepository;
import es.aulanosa.gestionfp.service.ProyectosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.EventObject;
>>>>>>> Añadir-endpoint-Proyectos
import java.util.List;

/**
 * Clase para los EndPoint de Proyectos
 */
@RestController
@RequestMapping("/api/proyectos")
@Tag(name = "Proyectos", description = "Clase para los apis de proyectos")
public class ProyectoController {

    @Autowired
    private ProyectosService service;


    /**
     * Método para insertar nuevos campos en la BD
     * @param proyectosDTO Objeto completo con los campos necesarios para la inserción en la BD,
     * @return Devuelve un body con los datos insertados o un error en caso de que falle algo
     */
    @PostMapping("/alta")
    @Operation(summary = "Método para insertar nuevos campos en la BD")
    //Guarda un nuevo proyecto
    public ResponseEntity<?> altaProyecto(@RequestBody ProyectoDTO proyectosDTO) {
        Proyectos proyectosConsultado = service.buscarPorId(proyectosDTO.getId());

        if (proyectosConsultado == null) {
            Proyectos proyectosGuardado = proyectosDTO.toModel();
            service.guardar(proyectosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectosGuardado);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0004", "Ya existe un proyecto con ese ID"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
    }

    /**
     * Método parta listar todos los registros de la table proyectos
     * @return Devuelve un body con una lista de proyectos representando los registros, o un error
     */
    @GetMapping("")
    @Operation(summary = "Método parta listar todos los registros de la table proyectos")
    //Lista todos los proyectos
    public ResponseEntity<?> listarTodoProyecto(){
        List<Proyectos> proyectos = service.buscarTodo();

        if (!proyectos.isEmpty()) {
            return ResponseEntity.ok(proyectos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay proyectos");
        }
    }

    /**
     * Método para buscar un registro en concreto en la BD con lo parámetros que se le pasan
     * @param id Parámetro para representar el campo ID de la BD
     * @return Devuelve un body con el registro en concreto o un error
     */
    @GetMapping("/{id}")
    @Operation(summary = "Método para buscar un registro en concreto en la BD con lo parámetros que se le pasan")
    //Consulta un proyecto por su ID
    public ResponseEntity<?> consultarProyectoId(@PathVariable Integer id){
        Proyectos proyectosConsultado = service.buscarPorId(id);

        if (proyectosConsultado != null) {
            return ResponseEntity.ok(proyectosConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }

    /**
     * Método para editar campos de la BD, se le pasa un objeto entero, comprueba el id y cambia el registro según los datos del objeto
     * @param proyectosDTO Objeto completo con el ID del registro que se quiere cambiar y las ediciones del mismo
     * @return Devuelve el objeto editado, de forma que el usuario puede ver lo que ha editado en la BD
     */
    @PutMapping("")
    @Operation(summary = "Método para editar campos de la BD, se le pasa un objeto entero, comprueba el id y cambia el registro según los datos del objeto")
    //Edita un proyecto
    public ResponseEntity<?> editarProyecto(@RequestBody ProyectoDTO proyectosDTO){
        Proyectos proyectosConsultado = service.buscarPorId(proyectosDTO.getId());

        if (proyectosConsultado != null) {
            Proyectos proyectosActualizado = proyectosDTO.toModel();
            service.guardar(proyectosActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectosActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }

    /**
     * Método para eliminar registros de la BD, se comprueba que el id exista y se elimina el id especificado en el parámetro
     * @param id Parámetro que representa el campo de la BD que se quiere elminiar
     * @return Devuelve un body con los datos que acaban de ser eliminados o un error
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Método para eliminar registros de la BD, se comprueba que el id exista y se elimina el id especificado en el parámetro")
    //Elimina un proyecto
    public ResponseEntity<?> eliminarProyecto(@PathVariable Integer id){
        Proyectos proyectosConsultado = service.buscarPorId(id);

        if(proyectosConsultado != null){
            service.borrar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }

<<<<<<< HEAD
    @GetMapping("/curso/{idCurso}/estudios/{idEstudios}")
    @Operation(summary = "Listar por curso y estudios")
    public ResponseEntity<?> listarPorCursoYEstudios(@PathVariable Integer idCurso, @PathVariable Integer idEstudios){
        List<Proyectos> proyectos = service.buscarPorCursoYEstudios(idCurso, idEstudios);

        try {
            return ResponseEntity.ok(proyectos);
        } catch (Exception e){
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E004", "No hay proyectos "));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
=======
    @GetMapping("/cursoEs/{idCurso}/estudiosEs/{idEstudios}")
    @Operation(summary = "Método para buscar todos los proyectos de un curso y unos estudios")
    //Consulta un proyecto por su ID
    public ResponseEntity<?> poryectosDeCursoyEstudios(@PathVariable Integer idCurso, @PathVariable Integer idEstudios){


        if(service.buscarProyectosCursoyEstudios(idCurso,idEstudios).isEmpty()){
            ErrorDTO errorDTO = new ErrorDTO("E0003","No hay resultados para los criterios de busqueda");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }else{
            List<Proyectos> proyectos = service.buscarProyectosCursoyEstudios(idCurso,idEstudios);
            List<ProyectoDTO> proyectosDTO = new ArrayList<>();

            for (Proyectos p1: proyectos)
            {
                ProyectoDTO proyectoDTO = new ProyectoDTO();
                proyectosDTO.add(proyectoDTO.toDTO(p1));
            }

            return ResponseEntity.status(HttpStatus.OK).body(proyectosDTO);
        }



>>>>>>> Añadir-endpoint-Proyectos
    }
}
