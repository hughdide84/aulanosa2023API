package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorEstudiosDto;
import es.aulanosa.gestionfp.dto.EstudiosDTO;
import es.aulanosa.gestionfp.model.Estudios;
import es.aulanosa.gestionfp.service.EstudiosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Estudios estudiosGuardado = servicio.insertar(estudiosDTO.convertirModel());
        estudiosDTO.crearDTO(estudiosGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiosDTO);
    }

    public ResponseEntity<?> crear (@RequestBody EstudiosDTO estudiosDTO) {
        Optional<Estudios> estudiosConsultado = servicio.consultarPorId(estudiosDTO.getId());
        if (!estudiosConsultado.isPresent()) {
            Estudios estudiosGuardado = servicio.insertar(estudiosDTO.convertirModel());
            return ResponseEntity.status(HttpStatus.CREATED).body(estudiosDTO.crearDTO(estudiosGuardado));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEstudiosDto());
    }
}
