package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AsignaturaDTO;
import es.aulanosa.gestionfp.model.Asignatura;
import es.aulanosa.gestionfp.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controladora para asignatura define las operaciones CRUD básicas para la entidad Asignatura.
 */
@RestController
@RequestMapping("api/asignaturas")
@Tag(name = "Asignaturas",description = "Operaciones con asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    /**
     * Crea una nueva asignatura.
     * @param asignaturaDTO objeto AsignaturaDTO que contiene los datos de la asignatura a crear.
     * @return ResponseEntity con el objeto Asignatura creado en el cuerpo de la respuesta si se ha creado correctamente,
     *         o un mensaje de error con el código de estado correspondiente si ha habido algún error.
     */
    @PostMapping("")
    @Operation(summary = "Inserta una asignatura")
    public ResponseEntity<?> insertarAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {

        Asignatura asignaturaConsultada = asignaturaService.buscarPorIdAsignatura(asignaturaDTO.getId());

        if (asignaturaConsultada == null) {
            try {
                Asignatura asignatura = asignaturaService.guardarAsignatura(asignaturaDTO.toAsignatura());
                return ResponseEntity.status(HttpStatus.CREATED).body(asignatura);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la asignatura");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una asignatura con ese id");
        }
    }

    /**
     * Busca una asignatura por su id.
     * @param id el id de la asignatura a buscar.
     * @return ResponseEntity con el objeto Asignatura encontrado en el cuerpo de la respuesta si se ha encontrado,
     *         o un mensaje de error con el código de estado correspondiente si no se ha encontrado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Busca una asignatura por id")
    public ResponseEntity<?> buscarAsignaturaPorId(@PathVariable int id) {
        Asignatura asignatura = asignaturaService.buscarPorIdAsignatura(id);
        if (asignatura != null) {
            return ResponseEntity.status(HttpStatus.OK).body(asignatura);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la asignatura");
        }
    }
    /**
     * Busca todas las asignaturas.
     * @return ResponseEntity con una lista de objetos Asignatura en el cuerpo de la respuesta si se han encontrado,
     *         o un mensaje de error con el código de estado correspondiente si no se han encontrado.
     */
    @GetMapping("/all")
    @Operation(summary = "Busca todas las asignaturas")
    public ResponseEntity<?> buscarTodasAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.buscarTodoAsignatura();
        if (asignaturas != null && !asignaturas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(asignaturas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se han encontrado asignaturas");
        }
    }
    /**
     * Modifica una asignatura existente.
     * @param asignaturaDTO objeto AsignaturaDTO que contiene los datos de la asignatura a modificar.
     * @return ResponseEntity con el objeto Asignatura modificado en el cuerpo de la respuesta si se ha modificado correctamente,
     *         o un mensaje de error con el código de estado correspondiente si ha habido algún error.
     */
    @PutMapping("/update")
    @Operation(summary = "Modifica una asignatura")
    public ResponseEntity<?> modificarAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        Asignatura asignaturaConsultada = asignaturaService.buscarPorIdAsignatura(asignaturaDTO.getId());

        if (asignaturaConsultada != null) {
            try {
                asignaturaService.modificarAsignatura(asignaturaDTO.toAsignatura());
                return ResponseEntity.status(HttpStatus.OK).body(asignaturaDTO);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar la asignatura");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la asignatura");
        }
    }
    /**
     * Borra una asignatura por su id.
     * @param id el id de la asignatura a borrar.
     * @return ResponseEntity con un código de estado 204 (NO_CONTENT) si se ha borrado correctamente,
     *         o un mensaje de error con el código de estado correspondiente si ha habido algún error.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina una asignatura")
    public ResponseEntity<?> borrarAsignatura(@PathVariable int id) {
        Asignatura asignaturaConsultada = asignaturaService.buscarPorIdAsignatura(id);
        if (asignaturaConsultada != null) {
            try {
                asignaturaService.borrarPorIdAsignatura(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al borrar la asignatura");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la asignatura");
        }
    }
}
