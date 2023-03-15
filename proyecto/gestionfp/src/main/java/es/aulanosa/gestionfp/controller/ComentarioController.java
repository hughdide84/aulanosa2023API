package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ComentarioDTO;
import es.aulanosa.gestionfp.model.Comentario;
import es.aulanosa.gestionfp.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioService service;

    // Crea un nuevo comentario
    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody ComentarioDTO comentarioDTO) {
        if (comentarioDTO.getTexto().length() <= 500) {
            Comentario comentarioGuardado = comentarioDTO.toModel();
            service.crear(comentarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentarioGuardado);
        } else if (comentarioDTO.getTexto().length() > 500) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud excedida en el campo texto");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El comentario ya fue añadido previamente");
        }
    }

    // Devuelve el comentario cuyo id coincide con el introducido
    @GetMapping("/{id}")
    public ResponseEntity<?> listarComentarioPorId(@PathVariable Integer id) {
        Optional<Comentario> comentarioConsultado = service.listarPorId(id);

        if (comentarioConsultado.isPresent()) {
            return ResponseEntity.ok(comentarioConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún comentario con ese ID");
        }
    }

    // Actualiza un comentario ya existente
    @PutMapping("")
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

    // Borra el comentario cuyo id coincide con el introducido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable Integer id) {
        Optional<Comentario> comentarioConsultado = service.listarPorId(id);

        if (comentarioConsultado.isPresent()) {
            service.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Devuelve un listado con todos los comentarios
    @GetMapping("")
    public ResponseEntity<?> listarTodo() {
        List<Comentario> comentarios = service.listarTodo();

        if (!comentarios.isEmpty()) {
            return ResponseEntity.ok(comentarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios");
        }
    }

    // Devuelve un listado con todos los comentarios cuyo sistema y referencia coincidan con los introducidos
    @GetMapping("/sistema/{sistema}/referencia/{referencia}")
    public ResponseEntity<?> listarPorSistemaYReferencia(@PathVariable char sistema, @PathVariable int referencia) {
        List<Comentario> comentarios = service.listarPorSistemaYReferencia(sistema, referencia);

        if (!comentarios.isEmpty()) {
            return ResponseEntity.ok(comentarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios");
        }
    }
}
