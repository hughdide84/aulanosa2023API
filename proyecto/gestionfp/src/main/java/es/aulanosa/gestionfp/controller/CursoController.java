package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.CursoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.service.CursoService;
import es.aulanosa.gestionfp.util.Errores;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/curso")
@Tag(name = "Curso", description = "Cursos")
public class CursoController {

    @Autowired
     private CursoService serviceCur;

    /**
     * Recibe un objeto cursoDTO como parametro para insertarlo en la BD
     * @param cursoDTO Se le pasa un objeto de tipo curso
     * @return se devuelve el curso creado con el id asignado en la BD
     */

    @PostMapping("")
    @Operation(summary = "Inserta un curso")
    public ResponseEntity<?> altaCurso(@RequestBody CursoDTO cursoDTO) {
        Curso cursoComprobar = serviceCur.buscarPorId(cursoDTO.getId());

        if (cursoComprobar == null) {
            try {
                Curso curso = cursoDTO.convertirModel();
                serviceCur.insertarCurso(curso);
                return ResponseEntity.status(HttpStatus.CREATED).body(curso);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar el curso");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El curso ya existe");
        }
    }

    //Operacion correspondiente para consultar un curso determinado por id

    
    @GetMapping("/{id}")
    @Operation(summary = "Busca un curso por id")
    public ResponseEntity<?> consultarCurso(@PathVariable Integer id) {
        Curso curso = serviceCur.buscarPorId(id);

        if (curso != null) {
            return ResponseEntity.status(HttpStatus.OK).body(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campo no encontrado");
        }
    }

    //Operacion correspondiente para cambiar los datos de un curso
    @PutMapping("")
    @Operation(summary = "Edita un curso")
    public ResponseEntity<?> editarCurso(@RequestBody CursoDTO curso) {
        Curso cursoConsultar = serviceCur.buscarPorId(curso.getId());

        if (cursoConsultar != null) {
            try {
                Curso cursoActualizar = curso.convertirModel();
                serviceCur.insertarCurso(cursoActualizar);
                return ResponseEntity.status(HttpStatus.OK).body(cursoActualizar);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar el curso");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el campo a modificar.");
        }
    }

    //Operación correspondiente para borrar un curso en concreto por id
    @DeleteMapping("/{id}")
    @Operation(summary = "Borra un curso por id")
    public ResponseEntity<?> borrarCurso (@PathVariable Integer id) throws NoSeHaEncontradoException {
        Curso curso = serviceCur.buscarPorId(id);

        if (curso != null) {
            try {
                serviceCur.eliminarCurso(id);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Borrado con exito");
            } catch (NoSeHaEncontradoException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al borrar el curso");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo no encontrado");
        }
    }

    //Operacion correspondiente para listar todos los datos de la tabla Cursos
    @GetMapping("/all")
    @Operation(summary = "Listar todos los cursos")
    public ResponseEntity<?> listarTodosCursos() {
        List<Curso> curso = serviceCur.buscarTodo();
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

    //Operacion correspondiente para listar todos los datos de la tabla Cursos que esten activos
    @GetMapping("/cursosActivos")
    @Operation(summary = "Listar todos los cursos activos")
    public ResponseEntity<?> listarCursosActivos() {
        List<Curso> cursos = serviceCur.buscarTodoPorEstadoActivo();
        return ResponseEntity.status(HttpStatus.OK).body(cursos);
    }

}
