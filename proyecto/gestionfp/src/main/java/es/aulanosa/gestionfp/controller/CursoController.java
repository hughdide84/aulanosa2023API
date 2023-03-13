package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.CursoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.util.Errores;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/curso")
@Tag(name = "Curso", description = "Estado de los cursos escolares")
public class CursoController {

    //Operacion para insertar los datos correspondientes a la tabla cursos
    @PostMapping
    @Operation(summary = "Altas")
    public ResponseEntity<?> altaCurso(@RequestBody CursoDTO cursoDTO) {

        Curso cursoGuardado = servicio.guardar(cursoDTO.convertirModel());
        cursoDTO.crearDTO(cursoGuardado);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
    }

    //Operacion correspondiente para consultar un curso determinado por id
    @GetMapping("/api/curso/{id}")
    public ResponseEntity<?> consultaCurso(@PathVariable("id") Integer id) {
        Optional<Curso> consultaDelCurso = servicio.consultarPorId(id);

        if (consultaDelCurso.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(consultaDelCurso);
        }

        else {
            ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_FALLOGENERAL, Errores.MEN_ERROR_FALLOGENERAL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }

    //Operacion correspondiente para cambiar los datos de un curso
    @PutMapping
    public ResponseEntity<?> editarCurso(@RequestBody CursoDTO curso) {
        
    }

    //Operación correspondiente para borrar un curso en concreto por id
    @DeleteMapping("/api/curso/{id}")
    public ResponseEntity<?> borrarCurso(@PathVariable Integer id) {
        Optional<Curso> borradoCurso = servicio.consultarPorId(id);

        if (borradoCurso.isPresent()) {
            servicio.borrarPorId(borradoCurso.get().getId());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Borrado con exito");
        }

        else {
            ErrorDTO errorDTO = new ErrorDTO(Errores.COD_ERROR_FALLOGENERAL, Errores.MEN_ERROR_FALLOGENERAL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }

    //Operacion correspondiente para listar todos los datos de la tabla Cursos
    @GetMapping
    public ResponseEntity<?> listarAllCursos(@RequestBody CursoDTO cursoDTO) {
        Optional<Curso> listadoAllCurso = servicio.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listadoAllCurso);
    }

}
