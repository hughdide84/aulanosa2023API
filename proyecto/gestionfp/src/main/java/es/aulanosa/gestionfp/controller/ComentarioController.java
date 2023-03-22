package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ComentarioDTO;
import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentario")
@Tag(name = "Comentarios", description = "Comentarios")
/**
 * Controller para el API de comentario
 */
public class ComentarioController {
    @Autowired
    private ComentarioService service;

    /**
     * Funcion para insertar datos a la BD, con una comprobación de longitud
     * @param comentarioDTO Objeto con los datos necesarios para crear una nueva insercion de datos en la BD
     * @return Devuelve el body con los datos necesarios o un error
     */
    // Crea un nuevo comentario
    @PostMapping("")
    @Operation(summary = "Inserta un nuevo usuario en la BD")
    public ResponseEntity<?> crear(@RequestBody ComentarioDTO comentarioDTO) {
        if (comentarioDTO.getTexto().length() <= 500) {
            Comentario comentarioGuardado = comentarioDTO.toModel();
            service.crear(comentarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentarioGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud excedida en el campo texto");
        }
    }

    /**
     * Devuelve el campo relacionado con el ID proporcionado, si existe en la BD
     * @param id Int representativo del campo en la BD
     * @return Devuelve el body con los datos necesarios o un error
     */
    // Devuelve el comentario cuyo id coincide con el introducido
    @GetMapping("/{id}")
    @Operation(summary = "Devuelve el campo relacionado con el ID proporcionado, si existe en la BD")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
        Optional<Comentario> comentarioConsultado = service.listarPorId(id);

        if (comentarioConsultado.isPresent()) {
            return ResponseEntity.ok(comentarioConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún comentario con ese ID");
        }
    }

    /**
     * Actualiza el campo proporcionado en la BD, si existe
     * @param comentarioDTO Objeto con los datos necesarios para crear una nueva insercion de datos en la BD
     * @return Devuelve el body con los datos necesarios o un error
     */
    // Actualiza un comentario ya existente
    @PutMapping("")
    @Operation(summary = "Actualiza el campo proporcionado en la BD, si existe")
    public ResponseEntity<?> actualizar(@RequestBody ComentarioDTO comentarioDTO) {
        Optional<Comentario> comentarioConsultado = service.listarPorId(comentarioDTO.getId());

        if (comentarioConsultado.isPresent() && comentarioDTO.getTexto().length() <= 500) {
            Comentario comentarioActualizado = comentarioDTO.toModel();
            service.crear(comentarioActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentarioActualizado);
        } else if (comentarioDTO.getTexto().length() > 500) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud excedida en el campo texto");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El comentario que desea modificar no existe");
        }
    }

    /**
     * Borra el campo relacionado con el ID proporcionado de la BD, si existe en la misma
     * @param id Int representativo del campo en la BD
     * @return Devuelve el body con los datos necesarios o un error
     */
    // Borra el comentario cuyo id coincide con el introducido
    @DeleteMapping("/{id}")
    @Operation(summary = "Borra el campo relacionado con el ID proporcionado de la BD, si existe en la misma")
    public ResponseEntity<?> borrarPorId(@PathVariable Integer id) {
        Optional<Comentario> comentarioConsultado = service.listarPorId(id);

        if (comentarioConsultado.isPresent()) {
            service.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El comentario que desea eliminar no existe");
        }
    }

    /**
     * Devuelve una lista con TODOS los campos de la BD
     * @return Devuelve el body con los datos necesarios o un error
     */
    // Devuelve un listado con todos los comentarios
    @GetMapping("")
    @Operation(summary = "Devuelve una lista con TODOS los campos de la BD")
    public ResponseEntity<?> listarTodo() {
        List<Comentario> comentarios = service.listarTodo();

        if (!comentarios.isEmpty()) {
            return ResponseEntity.ok(comentarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios");
        }
    }

    /**
     * Devuelve una lista con los campos relacionados con el Sistema y Referencia proporcionados
     * @param sistema Parámetro sistema que debe coincidir en la BD
     * @param referencia Parámetro referencia que debe coincidir en la BD
     * @return Devuelve el body con los datos necesarios o un error
     */
    // Devuelve un listado con todos los comentarios cuyo sistema y referencia coincidan con los introducidos
    @GetMapping("/sistema/{sistema}/referencia/{referencia}")
    @Operation(summary = "Devuelve una lista con los campos relacionados con el Sistema y Referencia proporcionados")
    public ResponseEntity<?> listarPorSistemaYReferencia(@PathVariable char sistema, @PathVariable int referencia) {
        List<Comentario> comentarios = service.listarPorSistemaYReferencia(sistema, referencia);

        if (!comentarios.isEmpty()) {
            return ResponseEntity.ok(comentarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios");
        }
    }
}
