package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnosExternosDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AlumnosExternos;
import es.aulanosa.gestionfp.service.AlumnosExternosServiceImp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/alumnoExterno")
//falta @tag

public class AlumnosExternosController {

    @Autowired
    private AlumnosExternosServiceImp service;

    @PostMapping("/")
    //falta @operation
    public ResponseEntity<?> alta(@RequestBody AlumnosExternosDTO alumnosExternosDTO){
        try{
            AlumnosExternos alumnosExternos = alumnosExternosDTO.convertirModel();
            AlumnosExternos alumnosExternosGuardado = service.guardar(alumnosExternos);
            alumnosExternosDTO.crearDTO(alumnosExternosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnosExternosDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consulta(@PathVariable Integer id){
        Optional<AlumnosExternos> alumnosExternos = service.listarPorId(id);

        if (alumnosExternos.isPresent()){
            AlumnosExternosDTO alumnosExternosDTO = new AlumnosExternosDTO();
            alumnosExternosDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnosExternosDTO);
        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NoSeHaEncontradoException("No se ha encontrado ese ID"));
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> editar(@RequestBody AlumnosExternosDTO alumnosExternosDTO){
        Optional<AlumnosExternos> alumnosExternos = service.listarPorId(alumnosExternosDTO.getId());

        if (alumnosExternos.isPresent()){
            service.guardar(alumnosExternos.get());
            AlumnosExternosDTO alumnosExternosDTORecuperado = new AlumnosExternosDTO();
            alumnosExternosDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnosExternosDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NoSeHaEncontradoException("No se ha encontrado ese Alumno Externo"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        Optional<AlumnosExternos> alumnosExternos = service.listarPorId(id);

        if(alumnosExternos.isPresent()){
            service.eliminar(alumnosExternos.get().getId());

            AlumnosExternosDTO alumnosExternosDTO = new AlumnosExternosDTO();
            alumnosExternosDTO.crearDTO(alumnosExternos.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnosExternosDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NoSeHaEncontradoException("No se ha encontrado ese Alumno Externo"));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarTodo(){
        if(!service.listarTodo().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTodo());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NoSeHaEncontradoException("No hay registros de Alumnos Externos"));
        }
    }
}
