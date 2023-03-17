package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnoExternoDTO;
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

public class AlumnoExternoController {

    @Autowired
    private AlumnoExternoServiceImp service;

    @PostMapping("/")
    //falta @operation

    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
    public ResponseEntity<?> alta(@RequestBody AlumnoExternoDTO alumnoExternoDTO){
        try{
            AlumnoExterno alumnosExternos = alumnoExternoDTO.convertirModel();
            AlumnoExterno alumnosExternosGuardado = service.guardar(alumnosExternos);
            alumnoExternoDTO.crearDTO(alumnosExternosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que lo haga devuelve el objeto recuperado de la BD
    public ResponseEntity<?> consulta(@PathVariable Integer id){
        try{
            Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);
            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);
        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

        }
    }

    @PutMapping("/")
    //se le pasa un objeto completo por POST, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes
    public ResponseEntity<?> editar(@RequestBody AlumnoExternoDTO alumnoExternoDTO){
        try{
            Optional<AlumnoExterno> alumnosExternos = service.listarPorId(alumnoExternoDTO.getId());
            service.guardar(alumnosExternos.get());
            AlumnoExternoDTO alumnoExternoDTORecuperado = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnosExternos.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido un Alumno válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

        }
    }

    @DeleteMapping("/{id}")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma
    public ResponseEntity<?> eliminar(@PathVariable int id){
        try{
            Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);
            service.eliminar(alumnosExternos.get().getId());

            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnosExternos.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alumnoExternoDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "Alumno no encontrado");
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
