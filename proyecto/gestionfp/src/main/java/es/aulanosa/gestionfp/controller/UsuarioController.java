package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.excepciones.RestResponseEntityExceptionHandler;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controlador de usuarios
 */
@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "API de usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    /**
     * crea un usuario
     * @param usuarioDTO
     * @return codigo 201 si se ha creado correctamente y el usuario con el id insertado, codigo 400 si no se ha podido crear y la lista de errores
     */
    @PostMapping("")
    @Operation(summary = "Crear un usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioConsultado = service.consultarPorNombre(usuarioDTO.getNombre());

        if (!usuarioConsultado.isPresent()) {
            try {
                Usuario usuarioGuardado = usuarioDTO.toModel();
                service.crear(usuarioGuardado);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
            } catch (NoSeHaEncontradoException e) {
                List<ErrorDTO> errores = new ArrayList<>();
                errores.add(new ErrorDTO("E001", e.getMessage()));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E001", "Ya existe un usuario con ese nombre o id"));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
    }

    /**
     * Lista el usuario cuyo id coincida con el introducido
     * @param id del usuario que se busca consultar
     * @return codigo 200 y usuario con el id introducido, codigo 404 si no se ha encontrado y lista de errores
     */
    @GetMapping("/{id}")
    @Operation(summary = "Lista el usuario cuyo id coincida con el introducido")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioConsultado = service.listarPorId(id);

        if (usuarioConsultado.isPresent()) {
            return ResponseEntity.ok(usuarioConsultado);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E002", "No existe ningún usuario con ese id"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }

    /**
     * Lista los usuarios cuyo nombre coincida con el introducido
     * @param nombre del usuario que se busca consultar
     * @return codigo 200 y usuario con el nombre introducido, codigo 404 si no se ha encontrado y lista de errores
     */
    @GetMapping("/nombreEs/{nombre}")
    @Operation(summary = "Lista los usuarios cuyo nombre coincida con el introducido")
    public ResponseEntity<?> listarUsuarioPorNombre(@PathVariable String nombre) {
        Optional<Usuario> usuarioConsultado = service.consultarPorNombre(nombre);

        if (usuarioConsultado.isPresent()) {
            return ResponseEntity.ok(usuarioConsultado);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E003", "No existe ningún usuario con ese nombre"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }

    /**
     * Lista los usuarios cuyo rol coincida con el introducido
     * @param rol de los usuarios que se busca consultar
     * @return codigo 200 y lista de usuarios con el rol introducido, codigo 404 si no se ha encontrado y lista de errores
     */
    @GetMapping("/rolEs/{rol}")
    @Operation(summary = "Lista los usuarios cuyo rol coincida con el introducido")
    public ResponseEntity<?> listarUsuariosPorRol(@PathVariable String rol) {
        List<Usuario> usuariosConsultados = service.consultarPorRol(rol);

        if (!usuariosConsultados.isEmpty()) {
            return ResponseEntity.ok(usuariosConsultados);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E004", "No existe ningún usuario con ese rol"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }

    /**
     * Lista los usuarios cuyo nombre contenga el nombre introducido
     * @param nombre de los usuarios que se busca consultar
     * @return codigo 200 y lista de usuarios cuyo nombre contenga el introducido, codigo 404 si no se ha encontrado y lista de errores
     */
    @GetMapping("/nombreContiene/{nombre}")
    @Operation(summary = "Lista los usuarios cuyo nombre contenga el nombre introducido")
    public ResponseEntity<?> compararUsuariosPorNombre(@PathVariable String nombre) {
        List<Usuario> usuariosConsultados = service.listarPorNombre(nombre);

        if (!usuariosConsultados.isEmpty()) {
            return ResponseEntity.ok(usuariosConsultados);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E005", "No existe ningún usuario que contenga ese nombre"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }

    /**
     * Lista los usuarios cuyo email contenga el email introducido
     * @param email de los usuarios que se busca consultar
     * @return codigo 200 y usuarios cuyo email contenga el introducido, codigo 404 si no se ha encontrado y lista de errores
     */
    @GetMapping("/emailContiene/{email}")
    @Operation(summary = "Lista los usuarios cuyo email contenga el email introducido")
    public ResponseEntity<?> compararUsuariosPorEmail(@PathVariable String email) {
        List<Usuario> usuariosConsultados = service.listarPorEmail(email);

        if (!usuariosConsultados.isEmpty()) {
            return ResponseEntity.ok(usuariosConsultados);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios");
        }
    }

    /**
     * Modifica un usuario existente
     * @param usuarioDTO con los datos del usuario que se desea modificar
     * @return codigo 200 y usuario modificado, codigo 404 si no se ha encontrado y lista de errores, codigo 400 si suceden errores de validación
     * @throws NoSeHaEncontradoException
     */
    @PutMapping("")
    @Operation(summary = "Modifica un usuario existente")
    public ResponseEntity<?> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws NoSeHaEncontradoException {
        Optional<Usuario> usuarioConsultado = service.listarPorId(usuarioDTO.getId());

        // Comprobamos si existe un usuario con el ID introducido
        if (usuarioConsultado.isPresent()) {
            Optional<Usuario> usuarioMismoNombreConsultado = service.consultarPorNombre(usuarioDTO.getNombre());
            // Comprobamos si existe un usuario con el nombre introducido
            if (usuarioMismoNombreConsultado.isPresent()) {
                Usuario usuarioMismoNombre = usuarioMismoNombreConsultado.get();
                // Permitimos la actualización si el nombre ya existe pero pertenece al usuario que deseamos modificar
                if (usuarioMismoNombre.getId() == usuarioDTO.getId()) {
                    Usuario usuarioActualizado = usuarioDTO.toModel();
                    service.actualizar(usuarioActualizado);
                    return ResponseEntity.ok(usuarioActualizado);
                } else {
                    List<ErrorDTO> errores = new ArrayList<>();
                    errores.add(new ErrorDTO("E006", "El nombre al que desea actualizar ya existe para otro usuario"));
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
                }
            } else {
                // Permitimos la actualización si el nombre introducido no existe
                Usuario usuarioActualizado = usuarioDTO.toModel();
                service.actualizar(usuarioActualizado);
                return ResponseEntity.ok(usuarioActualizado);
            }
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E002", "No existe ningún usuario con ese id"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }

    /**
     * Elimina un usuario existente
     * @param id del usuario que se desea eliminar
     * @return codigo 204 si se ha eliminado correctamente, codigo 404 si no se ha encontrado y lista de errores
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario existente")
    public ResponseEntity<?> borrarUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioConsultado = service.listarPorId(id);

        if (usuarioConsultado.isPresent()) {
            service.borrarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El usuario ha sido eliminado correctamente");
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E002", "No existe ningún usuario con ese id"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }

    /**
     * Lista todos los usuarios
     * @return codigo 200 y lista de usuarios, codigo 404 si no se ha encontrado y lista de errores
     */
    @GetMapping("")
    @Operation(summary = "Lista todos los usuarios")
    public ResponseEntity<?> listarTodosUsuarios() {
        List<Usuario> usuarios = service.listarTodo();

        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E007", "No existen usuarios"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
}
