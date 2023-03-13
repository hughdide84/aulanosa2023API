package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioConsultado = service.findById(usuarioDTO.getId());

        if (usuarioConsultado == null) {
            Usuario usuarioGuardado = usuarioDTO.toModel();
            service.save(usuarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario ya fue añadido previamente");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        Usuario usuarioConsultado = service.findById(id);

        if (usuarioConsultado != null) {
            return ResponseEntity.ok(usuarioConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún usuario con ese ID");
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioConsultado = service.findById(usuarioDTO.getId());

        if (usuarioConsultado != null) {
            Usuario usuarioActualizado = usuarioDTO.toModel();
            service.save(usuarioActualizado);
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario que desea modificar no existe");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        Usuario usuarioConsultado = service.findById(id);

        if (usuarioConsultado != null) {
            service.deleteById(id);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Usuario> usuarios = service.findAll();

        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios");
        }
    }
}
