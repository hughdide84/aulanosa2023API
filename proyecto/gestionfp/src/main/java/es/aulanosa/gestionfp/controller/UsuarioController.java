package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    // Crea un nuevo usuario
    @PostMapping("")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws NoSeHaEncontradoException {
        if (comprobarLongitudCampos(usuarioDTO).equals("")) {
            Usuario usuarioGuardado = usuarioDTO.toModel();
            service.crear(usuarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        } else if (!comprobarLongitudCampos(usuarioDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Longitud excedida en el/los siguiente/-s campo/-s: " + comprobarLongitudCampos(usuarioDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario ya fue añadido previamente");
        }
    }

    // Lista el usuario cuyo id coincida con el introducido
    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioConsultado = service.listarPorId(id);

        if (usuarioConsultado != null) {
            return ResponseEntity.ok(usuarioConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún usuario con ese ID");
        }
    }

    // Lista el usuario cuyo nombre coincida con el introducido
    @GetMapping("/nombreEs/{nombre}")
    public ResponseEntity<?> listarUsuarioPorNombre(@PathVariable String nombre) {
        Optional<Usuario> usuarioConsultado = service.consultarPorNombre(nombre);

        if (usuarioConsultado.isPresent()) {
            return ResponseEntity.ok(usuarioConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún usuario con ese nombre");
        }
    }

    // Lista los usuarios cuyo rol coincida con el introducido
    @GetMapping("/rolEs/{rol}")
    public ResponseEntity<?> listarUsuariosPorRol(@PathVariable String rol) {
        List<Usuario> usuariosConsultados = service.consultarPorRol(rol);

        if (!usuariosConsultados.isEmpty()) {
            return ResponseEntity.ok(usuariosConsultados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún usuario con ese rol");
        }
    }

    // Lista los usuarios que contengan en su nombre la cadena introducida
    @GetMapping("/nombreContiene/{nombre}")
    public ResponseEntity<?> compararUsuariosPorNombre(@PathVariable String nombre) {
        List<Usuario> usuariosConsultados = service.listarPorNombre(nombre);

        if (!usuariosConsultados.isEmpty()) {
            return ResponseEntity.ok(usuariosConsultados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios");
        }
    }

    // Lista los usuarios que contengan en su email la cadena introducida
    @GetMapping("/emailContiene/{email}")
    public ResponseEntity<?> compararUsuariosPorEmail(@PathVariable String email) {
        List<Usuario> usuariosConsultados = service.listarPorEmail(email);

        if (!usuariosConsultados.isEmpty()) {
            return ResponseEntity.ok(usuariosConsultados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios");
        }
    }

    // Actualiza un usuario ya existente
    @PutMapping("")
    public ResponseEntity<?> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws NoSeHaEncontradoException {
        Optional<Usuario> usuarioConsultado = service.listarPorId(usuarioDTO.getId());

        if (usuarioConsultado.isPresent() && comprobarLongitudCampos(usuarioDTO).equals("")) {
            Usuario usuarioActualizado = usuarioDTO.toModel();
            service.actualizar(usuarioActualizado);
            return ResponseEntity.ok(usuarioActualizado);
        } else if (!comprobarLongitudCampos(usuarioDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Longitud excedida en el/los siguiente/-s campo/-s: " + comprobarLongitudCampos(usuarioDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario que desea modificar no existe");
        }
    }

    // Borra el usuario cuyo id coincide con el introducido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioConsultado = service.listarPorId(id);

        if (usuarioConsultado != null) {
            service.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El usuario ha sido eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario que desea eliminar no existe");
        }
    }

    // Lista todos los usuarios
    @GetMapping("")
    public ResponseEntity<?> listarTodosUsuarios() {
        List<Usuario> usuarios = service.listarTodo();

        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios");
        }
    }

    // Función para gestionar el tamaño de los campos introducidos
    public String comprobarLongitudCampos(UsuarioDTO usuarioDTO) {
        List<String> camposInvalidos = new ArrayList<>();
        String msg = "";

        if (usuarioDTO.getNombre().length() > 50) {
            camposInvalidos.add("nombre");
        } else if (usuarioDTO.getPassword().length() > 100) {
            camposInvalidos.add("password");
        } else if (usuarioDTO.getRol().length() > 10) {
            camposInvalidos.add("rol");
        } else if (usuarioDTO.getEmail().length() > 50) {
            camposInvalidos.add("email");
        } else {

        }

        if (camposInvalidos.size() != 0) {
            for (int i = 0; i < camposInvalidos.size(); i++) {
                if (i != camposInvalidos.size() - 1) {
                    msg += camposInvalidos.get(i) + ", ";
                } else {
                    msg += camposInvalidos.get(i);
                }
            }
            return msg;
        } else {
            return msg;
        }
    }
}
