package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AsignaturaHorarioDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;
import es.aulanosa.gestionfp.service.AsignaturaHorarioService;
import es.aulanosa.gestionfp.service.AsignaturaHorarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturaHorario")
public class AsignaturaHorarioController {

    @Autowired
    private AsignaturaHorarioServiceImp service;

    @PostMapping("/")
    //falta @operation
    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
    public ResponseEntity<?> alta(@RequestBody AsignaturaHorarioDTO asignaturaHorarioDTO){
        try{
            AsignaturaHorario asignaturahorario = asignaturaHorarioDTO.convertirModel();
            AsignaturaHorario asignaturahorarioGuardado = service.insertar(asignaturahorario);
            asignaturaHorarioDTO.convertirDTO(asignaturahorarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que lo haga devuelve el objeto recuperado de la BD
    public ResponseEntity<?> consulta(@PathVariable Integer id){
        try{
            Optional<AsignaturaHorario> asignaturahorario = service.buscarPorId(id);
            AsignaturaHorarioDTO asignaturaHorarioDTO = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(asignaturahorario.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);
        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

        }
    }

    @PutMapping("/")
    //se le pasa un objeto completo por POST, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes
    public ResponseEntity<?> editar(@RequestBody AsignaturaHorarioDTO asignaturaHorarioDTO){
        try{
            Optional<AsignaturaHorario> asignaturaHorario = service.buscarPorId(asignaturaHorarioDTO.getId());
            service.insertar(asignaturaHorario.get());
            AsignaturaHorarioDTO asignaturaHorarioDTORecuperado = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(asignaturaHorario.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "AsignatutraHorario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido una asignaturaHorario válida");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

        }
    }
    @DeleteMapping("/{id}")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma
    public ResponseEntity<?> eliminar(@PathVariable int id){
        try{
            Optional<AsignaturaHorario> asignaturaHorario = service.buscarPorId(id);
            service.eliminar(asignaturaHorario.get().getId());

            AsignaturaHorarioDTO asignaturaHorarioDTO = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(asignaturaHorario.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(asignaturaHorarioDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "AsignaturaHorario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/")
    //lista todos los campos de la BD, en caso de que esta este vacia, devuelve un error personalizado
    public ResponseEntity<?> listarTodo(){
        if(!service.listarTodo().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTodo());
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }




}
