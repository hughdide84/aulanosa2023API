package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.CursoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Curso;
import es.aulanosa.gestionfp.service.CursoService;
import es.aulanosa.gestionfp.util.Errores;
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
public class CursoController {

    @Autowired
     private CursoService serviceCur;

    //Operacion para insertar los datos correspondientes a la tabla cursos
    @PostMapping
    public ResponseEntity<?> altaCurso(@RequestBody CursoDTO cursoDTO) {
        Curso cursoComprobar = serviceCur.buscarPorId(cursoDTO.getId());
        if (cursoComprobar == null && checkFieldSize(cursoDTO).equals("")) {
            Curso cursoGuardado = serviceCur.insertarCurso(cursoDTO.convertirModel());
            cursoDTO.crearDTO(cursoGuardado);

            return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
        } else if (!checkFieldSize(cursoDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo nombre supera el numero de caracteres");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo ya existente");
        }
    }

    //Operacion correspondiente para consultar un curso determinado por id
    @GetMapping("{id}")
    public ResponseEntity<?> consultarCurso(@PathVariable("id") Integer id) {
        Curso curso = serviceCur.buscarPorId(id);

        if (curso != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo no encontrado");
        }
    }

    //Operacion correspondiente para cambiar los datos de un curso
    @PutMapping
    public ResponseEntity<?> editarCurso(@RequestBody CursoDTO curso) {
        Curso cursoConsultar = serviceCur.buscarPorId(curso.getId());

        if (cursoConsultar != null && checkFieldSize(curso).equals("")) {
            Curso cursoActualizar = curso.convertirModel();
            serviceCur.insertarCurso(cursoActualizar);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoActualizar);
        } else if (!checkFieldSize(curso).equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo nombre excede el numero de caracteres permitidos");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el campo a modificar.");
        }
    }

    //Operación correspondiente para borrar un curso en concreto por id
    @DeleteMapping("{id}")
    public ResponseEntity<?> borrarCurso (@PathVariable Integer id) throws NoSeHaEncontradoException {
        Curso curso = serviceCur.buscarPorId(id);

        if (curso != null) {
            serviceCur.eliminarCurso(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Borrado con exito");
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo no encontrado");
        }
    }

    //Operacion correspondiente para listar todos los datos de la tabla Cursos
    @GetMapping
    public ResponseEntity<?> listarTodosCursos(@RequestBody CursoDTO cursoDTO) {
        List<Curso> curso = serviceCur.buscarTodo();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(curso);
    }

    public String checkFieldSize(CursoDTO cursoDTO) {
        List<String> invalidFields = new ArrayList<>();
        String msg = "";

        if (cursoDTO.getNombre().length() > 100) {
            invalidFields.add("nombre");
        } else {

        }

        if (invalidFields.size() != 0) {
            for (int i = 0; i < invalidFields.size(); i++) {
                if (i != invalidFields.size() - 1) {
                    msg += invalidFields.get(i) + ", ";
                } else {
                    msg += invalidFields.get(i);
                }
            }
            return msg;
        } else {
            return msg;
        }
    }

}
