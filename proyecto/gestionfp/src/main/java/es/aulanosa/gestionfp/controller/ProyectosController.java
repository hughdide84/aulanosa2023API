package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ProyectosDTO;
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
public class ProyectosController {

    @Autowired
    private ProyectosService service;

    @PostMapping("/alta")
    @Operation(summary = "Alta")
    //Guarda un nuevo proyecto
    public ResponseEntity<?> altaProyecto(@RequestBody ProyectosDTO proyectosDTO) {
        Proyectos proyectosGuardado = service.save(proyectosDTO.toModel());

        if (proyectosGuardado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectosGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto ya existe");
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "Consulta")
    //Consulta un proyecto por su ID
    public ResponseEntity<?> getUsuarioPorId(@PathVariable Integer id){
        Proyectos proyectosConsultado = service.findById(id);

        if (proyectosConsultado != null) {
            return ResponseEntity.ok(proyectosConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }
    @PutMapping("")
    @Operation(summary = "Editar")
    //Edita un proyecto
    public ResponseEntity<?> editar(@RequestBody ProyectosDTO proyectosDTO){
        Proyectos proyectosConsultado = service.findById(proyectosDTO.getId());

        if (proyectosConsultado != null) {
            Proyectos proyectosActualizado = proyectosDTO.toModel();
            service.save(proyectosActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectosActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar")
    //Elimina un proyecto
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        Proyectos proyectosConsultado = service.findById(id);

        if(proyectosConsultado != null){
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún proyecto con ese ID");
        }
    }
    @GetMapping("")
    @Operation(summary = "Listar todo")
    //Lista todos los proyectos
    public ResponseEntity<?> listarTodo(){
        List<Proyectos> proyectos = service.findAll();

        if (!proyectos.isEmpty()) {
            return ResponseEntity.ok(proyectos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay proyectos");
        }
    }
}
