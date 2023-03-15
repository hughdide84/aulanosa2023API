package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    // Crea un nuevo usuario
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioConsultado = service.listarPorId(usuarioDTO.getId());

        if (usuarioConsultado == null && checkFieldSize(usuarioDTO).equals("")) {
            Usuario usuarioGuardado = usuarioDTO.toModel();
            service.crear(usuarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        } else if (!checkFieldSize(usuarioDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Longitud excedida en el/los siguiente/-s campo/-s: " + checkFieldSize(usuarioDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario ya fue añadido previamente");
        }

    }

    // Devuelve el usuario cuyo id coincide con el introducido
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        Usuario usuarioConsultado = service.listarPorId(id);

        if (usuarioConsultado != null) {
            return ResponseEntity.ok(usuarioConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún usuario con ese ID");
        }
    }

    // Actualiza un usuario ya existente
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioConsultado = service.listarPorId(usuarioDTO.getId());

        if (usuarioConsultado != null && checkFieldSize(usuarioDTO).equals("")) {
            Usuario usuarioActualizado = usuarioDTO.toModel();
            service.crear(usuarioActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioActualizado);
        } else if (!checkFieldSize(usuarioDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Longitud excedida en el/los siguiente/-s campo/-s: " + checkFieldSize(usuarioDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario que desea modificar no existe");
        }
    }


    // Borra el usuario cuyo id coincide con el introducido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        Usuario usuarioConsultado = service.listarPorId(id);

        if (usuarioConsultado != null) {
            service.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }

    // Devuelve un listado con todos los usuarios
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Usuario> usuarios = service.listarTodo();

        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios");
        }
    }


    // Función para gestionar el tamaño de los campos introducidos
    public String checkFieldSize(UsuarioDTO usuarioDTO) {
        List<String> invalidFields = new ArrayList<>();
        String msg = "";

        if (usuarioDTO.getNombre().length() > 50) {
            invalidFields.add("nombre");
        } else if (usuarioDTO.getPassword().length() > 100) {
            invalidFields.add("password");
        } else if (usuarioDTO.getRol().length() > 10) {
            invalidFields.add("rol");
        } else if (usuarioDTO.getEmail().length() > 50) {
            invalidFields.add("email");
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
