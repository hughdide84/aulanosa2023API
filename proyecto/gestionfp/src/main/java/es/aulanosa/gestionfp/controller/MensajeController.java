package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.MensajeDTO;
import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.MensajeService;
import es.aulanosa.gestionfp.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Clase controladora de los Endpoints de la API, en la que se especifican los mismos para crear, modificar, listar y eliminar
 */
@RestController
@RequestMapping("/api/mensaje")
@Tag(name = "Mensajes", description = "Mensajes")
public class MensajeController {

    @Autowired
    MensajeService service;
    @Autowired
    UsuarioService serviceUsu;


    /**
     * Método para insertar datos en la BD con el objeto que se le pasa como parámetro
     * @param mensajeDTO Objeto completo que representa los datos para insertar en la BD
     * @return Devuelve un body con los datos que acaban de
     */
    // Crea un nuevo mensaje
    @PostMapping("")
    @Operation(summary = "Método para insertar datos en la BD con el objeto que se le pasa como parámetro")
    public ResponseEntity<?> crear(@RequestBody MensajeDTO mensajeDTO) {


            try{
                    if(serviceUsu.listarPorId(mensajeDTO.getIdUsuario()) != null){
                        Mensaje mensajeGuardado = mensajeDTO.toModel();
                        service.insertarMensaje(mensajeGuardado);
                        return ResponseEntity.status(HttpStatus.CREATED).body(mensajeDTO.toDTO(mensajeGuardado));
                    }else{
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El idUsuario no existe");
                    }


            }catch (NoSuchElementException e){
                ErrorDTO errorDTO = new ErrorDTO("E001","Error con un campo usado como clave foranea");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }


    }

    /**
     * Método para obtener los datos de la BD que correspondan con el ID parámetro
     * @param id Variable Integer que representa el campo ID de la BD
     * @return Devuelve los campos de la BD que coincidan con el ID, o un error
     */
    // Devuelve el mensaje cuyo id coincide con el introducido
    @GetMapping("/{id}")
    @Operation(summary = "Método para obtener los datos de la BD que correspondan con el ID parámetro")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Mensaje> mensajeConsultado = service.consultarPorIdMensaje(id);

        if (mensajeConsultado.isPresent()) {
            return ResponseEntity.ok(mensajeConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún mensaje con ese ID");
        }
    }

    /**
     * Método para listar todos los mensajes
     * @return devuelve una lista con todos los mensajes de la BD
     */
    @GetMapping("")
    @Operation
    public ResponseEntity<?> listarTodoMensaje(){
        List<Mensaje> mensajes = service.consultarTodosMensajes();
        List<MensajeDTO> mensajesDTO = new ArrayList<>();

        for (Mensaje msg:
             mensajes) {
            MensajeDTO msgDTO = new MensajeDTO();
            mensajesDTO.add(msgDTO.toDTO(msg));
        }

        return ResponseEntity.status(HttpStatus.OK).body(mensajesDTO);

    }

    /**
     * Método para editar un registro de la BD a través de un objeto que se le pasa como body
     * @param mensajeDTO Objeto que contiene el ID del registro a editar y los campos ya cambiados
     * @return Devuelve el objeto editado para que el usuario los compruebe, o un error
     */
    //edita el mensaje ya existente
    @PutMapping("")
    @Operation(summary = "Método para editar un registro de la BD")
    public ResponseEntity<?> editarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        Optional<Mensaje> mensajeConsultar = service.consultarPorIdMensaje(mensajeDTO.getId());

        if (mensajeConsultar.isPresent()) {
            Mensaje mensajeActualizar = mensajeDTO.toModel();
            service.insertarMensaje(mensajeActualizar);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensajeDTO.toDTO(mensajeActualizar));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el mensaje a modificar.");
        }
    }

    /**
     * Método para borrar registros de la BD con el ID que se le proporciona como parámetro
     * @param id Parámetro que representa el campo ID de la tabla en la BD
     * @return Devuelve un body con os datos eliminado o un error
     */
    //elimina el mensaje existente que tenga el id que se le pasa
    @DeleteMapping("/{id}")
    @Operation(summary = "Método para borrar registros de la BD con el ID que se le proporciona como parámetro")
    public ResponseEntity<?> borrarMensajePorId(@PathVariable Integer id) {
        Optional<Mensaje> mensajeConsultado = service.consultarPorIdMensaje(id);

        if (mensajeConsultado.isPresent()) {
            service.eliminarMensaje(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mensaje que desea eliminar no existe");
        }
    }

}
