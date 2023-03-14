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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudios")
@Tag(name = "Estudios", description = "Estudios")
public class EstudiosController {

    @Autowired
    private EstudiosService servicio;

    @PostMapping
    @Operation(summary = "Inserta un estudio")
    public ResponseEntity<?> crearEstudio(@RequestBody EstudiosDTO estudiosDTO) {

        Estudios estudiosGuardado = estudiosDTO.convertirModel();
        try {
            estudiosGuardado = servicio.insertar(estudiosGuardado);
            estudiosDTO.crearDTO(estudiosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estudiosDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "Consulta un estudio por su id")
    public ResponseEntity<?> consultarId(@PathVariable Integer id){
        Optional<Estudios> estudiosConsultado = servicio.consultarPorId(id);
        if (estudiosConsultado.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(estudiosConsultado);
        }else {
            ErrorEstudiosDto errorEstudiosDto = new ErrorEstudiosDto();
            errorEstudiosDto.setMensaje("No se ha encontrado el estudio");
            errorEstudiosDto.setError("404");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorEstudiosDto);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Consultar todos los estudios")
    public ResponseEntity<?> consultarTodos(){
        List<Estudios> estudiosConsultado = servicio.consultarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(estudiosConsultado);


    }

    @PutMapping("/update")
    @Operation(summary = "Modificar un estudio")
    public ResponseEntity<?> modificar(@RequestBody EstudiosDTO estudiosDTO) throws NoSuchFieldException {
        Optional<Estudios> estudiosConsultado = servicio.consultarPorId(estudiosDTO.getId());
        if (estudiosConsultado.isPresent()) {
            Estudios estudiosModificado = servicio.modificar(estudiosDTO.convertirModel());
            return ResponseEntity.status(HttpStatus.OK).body(estudiosModificado);
        }else {
            ErrorEstudiosDto errorEstudiosDto = new ErrorEstudiosDto();
            errorEstudiosDto.setMensaje("No se ha encontrado el estudio");
            errorEstudiosDto.setError("404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorEstudiosDto);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borrar un estudio")
    public ResponseEntity<?> borrar(@PathVariable int id) {
        var estudiosConsultado = servicio.consultarPorId(id);
        if (estudiosConsultado != null) {
            servicio.eliminar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


