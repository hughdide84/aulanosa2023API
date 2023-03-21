package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnoExternoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.PagoDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import es.aulanosa.gestionfp.model.Pago;
import es.aulanosa.gestionfp.repository.PagoRepository;
import es.aulanosa.gestionfp.service.PagoServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Estudios", description = "Estudios")
public class PagoController {

    @Autowired
    PagoServiceImp service;

    @PostMapping("")
    //falta @operation

    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
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

    @GetMapping("/{id}")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que lo haga devuelve el objeto recuperado de la BD

    //hay que hacerlo con try/catch

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

    @PutMapping("")
    //se le pasa un objeto completo por POST, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes

    //hay que hacerlo con try/catch

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

    @DeleteMapping("/{id}")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma

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


    @GetMapping("")
    //lista todos los campos de la BD, en caso de que esta este vacia, devuelve un error personalizado
    public ResponseEntity<?> listarTodo(){
        if(!service.buscarTodosPagos().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodosPagos());
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }



}
