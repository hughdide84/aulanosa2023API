package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AsignaturaDTO;
import es.aulanosa.gestionfp.model.Asignatura;
import es.aulanosa.gestionfp.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @PostMapping
    public ResponseEntity<?> insertarAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {

        Asignatura asignaturaConsultada = asignaturaService.findById(asignaturaDTO.getId());

        if (asignaturaConsultada == null) {
            asignaturaService.save(asignaturaDTO.toAsignatura());
            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una asignatura con ese id");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAsignaturaPorId(@PathVariable int id) {
        Asignatura asignatura = asignaturaService.findById(id);
        if (asignatura != null) {
            return ResponseEntity.status(HttpStatus.OK).body(asignatura);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la asignatura");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> buscarTodasAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.findAll();
        if (asignaturas != null && !asignaturas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(asignaturas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se han encontrado asignaturas");
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> modificarAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        Asignatura asignaturaConsultada = asignaturaService.findById(asignaturaDTO.getId());

        if (asignaturaConsultada != null) {
            asignaturaService.update(asignaturaDTO.toAsignatura());
            return ResponseEntity.status(HttpStatus.OK).body(asignaturaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la asignatura");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> borrarAsignatura(@PathVariable int id) {
        Asignatura asignaturaConsultada = asignaturaService.findById(id);
        if (asignaturaConsultada != null) {
            asignaturaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la asignatura");
        }
    }
}
