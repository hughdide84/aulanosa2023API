package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ProyectoDTO;
import es.aulanosa.gestionfp.model.Proyectos;
import es.aulanosa.gestionfp.service.ProyectosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Alta")
    //Guarda un nuevo proyecto
    public ResponseEntity<?> altaProyecto(@RequestBody ProyectoDTO proyectosDTO) {
        Proyectos proyectosGuardado = service.guardar(proyectosDTO.toModel());

        if (proyectosGuardado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectosGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto ya existe");
        }
    }

    /**
     * Método parta listar todos los registros de la table proyectos
     * @return Devuelve un body con una lista de proyectos representando los registros, o un error
     */
    @GetMapping("")
    @Operation(summary = "Listar")
    //Lista todos los proyectos
    public ResponseEntity<?> listarTodoProyecto(){
        List<Proyectos> proyectos = service.buscarTodo();

        if (!proyectos.isEmpty()) {
            return ResponseEntity.ok(proyectos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay proyectos");
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "Consultar")
    //Consulta un proyecto por su ID
    public ResponseEntity<?> consultarProyectoId(@PathVariable Integer id){
        Proyectos proyectosConsultado = service.buscarPorId(id);

        if (proyectosConsultado != null) {
            return ResponseEntity.ok(proyectosConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }
    @PutMapping("")
    @Operation(summary = "Editar")
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
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar")
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
}
