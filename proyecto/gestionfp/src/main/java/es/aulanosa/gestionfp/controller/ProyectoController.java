package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ProyectoDTO;
import es.aulanosa.gestionfp.model.Proyectos;
import es.aulanosa.gestionfp.service.ProyectosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectosService service;

    @PostMapping("")
    @Operation(summary = "Inserta un nuevo proyecto")
    //Guarda un nuevo proyecto
    public ResponseEntity<?> altaProyecto(@RequestBody ProyectoDTO proyectosDTO) {
        Proyectos proyectosGuardado = service.guardar(proyectosDTO.toModel());

        if (proyectosGuardado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectosGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto ya existe");
        }
    }
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
