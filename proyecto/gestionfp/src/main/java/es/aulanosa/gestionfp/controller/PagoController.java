package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnoExternoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.PagoDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import es.aulanosa.gestionfp.model.Pago;
import es.aulanosa.gestionfp.repository.PagoRepository;
import es.aulanosa.gestionfp.service.PagoServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
/**
 Controlador de la API de pagos.
 */
@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pago", description = "Operaciones con pagos")
public class PagoController {

    @Autowired
    PagoServiceImp service;



    /**
     API para dar de alta un pago. Se le pasa un objeto DTO por POST, lo convierte al modelo y lo inserta.
     @param pagoDTO DTO de pago a insertar.
     @return ResponseEntity con el pago insertado en caso de éxito, o un error en caso contrario.
     */
    @PostMapping("")
    @Operation(summary = "Inserta un pago")
    public ResponseEntity<?> alta(@RequestBody PagoDTO pagoDTO){
        try{
            Pago pago = pagoDTO.convertirModel();
            Pago PagoGuardado = service.guardarPago(pago);
            pagoDTO.convertirDTO(PagoGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    /**
     Consulta de un pago por ID.
     @param id ID del pago a consultar.
     @return ResponseEntity con el pago consultado en caso de éxito, o un error en caso contrario.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulta un pago")

    public ResponseEntity<?> consulta(@PathVariable Integer id){
            Optional<Pago> alumnosExternos = service.buscarPorIdPago(id);

            if(alumnosExternos.isPresent()){
                PagoDTO pagoDTO = new PagoDTO();
                pagoDTO.convertirDTO(alumnosExternos.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(pagoDTO);
            }else{
                ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
    }


    //hay que hacerlo con try/catch

    /**
     * Endpoint para editar un pago existente.
     * @param pagoDTO DTO del pago a editar.
     * @return ResponseEntity con el DTO del pago editado o un mensaje de error si hay un problema.
     */
    @PutMapping("")
    @Operation(summary = "Edita un pago")
    public ResponseEntity<?> editar(@RequestBody PagoDTO pagoDTO){
        try{
            Optional<Pago> pago = service.buscarPorIdPago(pagoDTO.getId());
            service.guardarPago(pago.get());
            PagoDTO pagoDTORecuperado = new PagoDTO();
            pagoDTORecuperado.convertirDTO(pago.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(pagoDTORecuperado);

        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido un Alumno válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }


    /**
     * Endpoint para eliminar un pago existente.
     * @param id ID del pago a eliminar.
     * @return ResponseEntity con el DTO del pago eliminado o un mensaje de error si el pago no existe.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un pago")
    //hay que hacerlo con try/catch
    public ResponseEntity<?> eliminar(@PathVariable int id){
            Optional<Pago> pago = service.buscarPorIdPago(id);
            if(pago.isPresent()){
                service.borrarPago(pago.get().getId());

                PagoDTO pagoDTO = new PagoDTO();
                pagoDTO.convertirDTO(pago.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pagoDTO);

            }else{
                ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }



    /**
     * Endpoint para listar todos los pagos almacenados en la base de datos.
     * @return ResponseEntity que contiene una lista de todos los pagos o un mensaje de error si la lista está vacía.
     */
    @GetMapping("")
    @Operation(summary = "Consulta todos los pagos")
    public ResponseEntity<?> listarTodo(){
        if(!service.buscarTodosPagos().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodosPagos());
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }



}
