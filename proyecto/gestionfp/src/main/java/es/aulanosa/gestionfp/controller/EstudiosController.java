package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorEstudiosDto;
import es.aulanosa.gestionfp.dto.EstudiosDTO;
import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.service.EstudiosService;
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
 * Clase que controla las peticiones de los estudios
 */
@RestController
@RequestMapping("/api/estudios")
@Tag(name = "Estudios", description = "Estudios")
public class EstudiosController {

    @Autowired
    private EstudiosService servicio;

    /**
     * Método que inserta un estudio en la base de datos
     *
     * @param estudiosDTO
     * @return un nuevo estudio
     */
    @PostMapping
    @Operation(summary = "Inserta un estudio")
    public ResponseEntity<?> crearEstudio(@RequestBody EstudiosDTO estudiosDTO) {

        Estudios estudiosGuardado = estudiosDTO.convertirModel();
        try {
            estudiosGuardado = servicio.insertar(estudiosGuardado);
            estudiosDTO.crearDTO(estudiosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estudiosDTO);
        } catch (Exception e) {
            List<ErrorEstudiosDto> errorEstudiosDto = new ArrayList<>();
            errorEstudiosDto.add(new ErrorEstudiosDto("E007", "Error al insertar un estudio"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Método que consulta un estudio por su id
     *
     * @param id
     * @return un estudio por su id
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulta un estudio por su id")
    public ResponseEntity<?> consultarId(@PathVariable Integer id) {
        Optional<Estudios> estudiosConsultado = servicio.consultarPorId(id);
        if (estudiosConsultado.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(estudiosConsultado);
        } else {
            ErrorEstudiosDto errorEstudiosDto = new ErrorEstudiosDto();
            List<ErrorEstudiosDto> errorEstudiosDtoList = new ArrayList<>();
            errorEstudiosDto.setMensaje("No se ha encontrado el estudio");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorEstudiosDto);
        }
    }

    /**
     * Método que consulta todos los estudios
     *
     * @return todos los estudios
     */
    @GetMapping("/all")
    @Operation(summary = "Consultar todos los estudios")
    public ResponseEntity<?> consultarTodos() {
        List<Estudios> estudiosConsultado = servicio.consultarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(estudiosConsultado);

    }

    /**
     * Método que modifica un estudio
     *
     * @param estudiosDTO
     * @return un estudio modificado
     * @throws NoSuchFieldException
     */
    @PutMapping("/update")
    @Operation(summary = "Modificar un estudio")
    public ResponseEntity<?> modificar(@RequestBody EstudiosDTO estudiosDTO) throws NoSuchFieldException {
        Optional<Estudios> estudiosConsultado = servicio.consultarPorId(estudiosDTO.getId());
        if (estudiosConsultado.isPresent()) {
            Estudios estudiosModificado = servicio.modificar(estudiosDTO.convertirModel());
            return ResponseEntity.status(HttpStatus.OK).body(estudiosModificado);
        } else {
            ErrorEstudiosDto errorEstudiosDto = new ErrorEstudiosDto();
            errorEstudiosDto.setMensaje("No se ha encontrado el estudio");
            errorEstudiosDto.setError("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorEstudiosDto);
        }
    }

    /**
     * Método que borra un estudio
     *
     * @param id
     * @return un estudio borrado
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borrar un estudio")
    public ResponseEntity<?> borrar(@PathVariable int id) {
        var estudiosConsultado = servicio.consultarPorId(id);
        if (!estudiosConsultado.isEmpty()) {
            servicio.eliminar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


