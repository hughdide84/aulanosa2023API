package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnosExternosDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnoExterno;
import es.aulanosa.gestionfp.service.AlumnoExternoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/alumnoExterno")
//falta @tag

public class AlumnosExternosController {

    @Autowired
    private AlumnoExternoServiceImp service;

    @PostMapping("/")
    //falta @operation
    public ResponseEntity<?> alta(@RequestBody AlumnosExternosDTO alumnosExternosDTO){
        try{
            AlumnoExterno alumnosExternos = alumnosExternosDTO.convertirModel();
            AlumnoExterno alumnosExternosGuardado = service.guardar(alumnosExternos);
            alumnosExternosDTO.crearDTO(alumnosExternosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnosExternosDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consulta(@PathVariable Integer id){
        Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);

        if (alumnosExternos.isPresent()){
            AlumnosExternosDTO alumnosExternosDTO = new AlumnosExternosDTO();
            alumnosExternosDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnosExternosDTO);
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> editar(@RequestBody AlumnosExternosDTO alumnosExternosDTO){
        Optional<AlumnoExterno> alumnosExternos = service.listarPorId(alumnosExternosDTO.getId());

        if (alumnosExternos.isPresent()){
            service.guardar(alumnosExternos.get());
            AlumnosExternosDTO alumnosExternosDTORecuperado = new AlumnosExternosDTO();
            alumnosExternosDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnosExternosDTO);
        }else if(!alumnosExternos.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido un Alumno válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);

        if(alumnosExternos.isPresent()){
            service.eliminar(alumnosExternos.get().getId());

            AlumnosExternosDTO alumnosExternosDTO = new AlumnosExternosDTO();
            alumnosExternosDTO.crearDTO(alumnosExternos.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnosExternosDTO);
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarTodo(){
        if(!service.listarTodo().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTodo());
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }
}
