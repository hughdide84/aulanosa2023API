package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnoExternoDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.PagoDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.excepciones.RestResponseEntityExceptionHandler;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pago")
@Tag(name = "Pagos", description = "Pagos")
public class PagoController {

    @Autowired
    PagoServiceImp service;

    @PostMapping("")
    //falta @operation

    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
    public ResponseEntity<?> altaPago(@RequestBody PagoDTO pagoDTO){

            Pago pago = pagoDTO.convertirModel();
            //le pongo id 0 para que siempre cree uno nuevo
            pago.setId(0);
            Pago PagoGuardado = service.guardarPago(pago);
            pagoDTO.convertirDTO(PagoGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoDTO);

    }

    @GetMapping("/{id}")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que lo haga devuelve el objeto recuperado de la BD
    //hay que hacerlo con try/catch

    public ResponseEntity<?> consultaPago(@PathVariable Integer id){
            Optional<Pago> alumnosExternos = service.buscarPorIdPago(id);

            if(alumnosExternos.isPresent()){
                PagoDTO pagoDTO = new PagoDTO();
                pagoDTO.convertirDTO(alumnosExternos.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(pagoDTO);
            }else{
                ErrorDTO errorDTO = new ErrorDTO("E0009", "ID no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
    }

    @PutMapping("")
    //se le pasa un objeto completo por POST, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes

    public ResponseEntity<?> editarPago(@RequestBody PagoDTO pagoDTO){
        try{
            Optional<Pago> pago = service.buscarPorIdPago(pagoDTO.getId());
            service.modificarPago(pagoDTO.convertirModel());
            PagoDTO pagoDTORecuperado = new PagoDTO();
            pagoDTORecuperado.convertirDTO(service.buscarPorIdPago(pagoDTO.getId()).get());

            return ResponseEntity.status(HttpStatus.CREATED).body(pagoDTORecuperado);

        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0005", "No se ha introducido un Pago válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }

    @DeleteMapping("/{id}")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma

    //hay que hacerlo con try/catch
    public ResponseEntity<?> eliminarPago(@PathVariable int id){
            Optional<Pago> pago = service.buscarPorIdPago(id);
            if(pago.isPresent()){
                service.borrarPago(pago.get().getId());

                PagoDTO pagoDTO = new PagoDTO();
                pagoDTO.convertirDTO(pago.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pagoDTO);
            }else{
                ErrorDTO errorDTO = new ErrorDTO("E0006", "Pago no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }


    @GetMapping("")
    //lista todos los campos de la BD, en caso de que esta este vacia, devuelve un error personalizado
    public ResponseEntity<?> listarTodoPago(){
        int cont = 0;

        if(!service.buscarTodosPagos().isEmpty()){
            List<Pago> pagos = service.buscarTodosPagos();

            List<PagoDTO> pagosDTO = new ArrayList<>();
            for (Pago pago:
                    pagos) {
                pagosDTO.add(new PagoDTO().convertirDTO(pago));
                cont++;
            }
            return ResponseEntity.status(HttpStatus.OK).body(pagosDTO);
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0007", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/matricula/{matricula}")
    //recupera un idMatricula y la consulta con los datos de labase de datos, ésta devuelve una lista y la convierto a DTO para mostrarlo
    public ResponseEntity<?> listarPorMatricula(@PathVariable int matricula){
        int cont = 0;
        try{
            List<Pago> pagos = service.listarPorMatricula(matricula);

            List<PagoDTO> pagosDTO = new ArrayList<>();
            for (Pago pago:
                 pagos) {
                pagosDTO.add(new PagoDTO().convertirDTO(pago));
                cont++;
            }
            return ResponseEntity.status(HttpStatus.OK).body(pagosDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0008", "No se ha encontrado la matrícula especificada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

        }
    }
}
