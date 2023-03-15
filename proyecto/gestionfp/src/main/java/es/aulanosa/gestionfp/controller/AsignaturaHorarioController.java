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
            Optional<AsignaturaHorario> alumnosExternos = service.buscarPorId(asignaturaHorarioDTO.getId());
            service.insertar(alumnosExternos.get());
            AsignaturaHorarioDTO asignaturaHorarioDTORecuperado = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido un Alumno válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

        }
    }



}
