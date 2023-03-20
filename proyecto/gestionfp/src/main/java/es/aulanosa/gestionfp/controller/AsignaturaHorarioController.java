package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AsignaturaHorarioDTO;
import es.aulanosa.gestionfp.dto.CursoEstudioNivelDTO;
import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.AsignaturaHorario;
import es.aulanosa.gestionfp.service.AsignaturaHorarioService;
import es.aulanosa.gestionfp.service.AsignaturaHorarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturaHorario")
public class AsignaturaHorarioController {

    @Autowired
    private AsignaturaHorarioServiceImp service;

    @PostMapping("")
    //falta @operation
    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
    public ResponseEntity<?> altaAsignaturaHorario(@RequestBody AsignaturaHorarioDTO asignaturaHorarioDTO){
        try{
            AsignaturaHorario asignaturaHorarioGuardado = asignaturaHorarioDTO.convertirModel();
            //lo pongo a 0 para que me lo auto incremente y no me edite los demas campos
            asignaturaHorarioGuardado.setId(0);
            AsignaturaHorario asignaturaHorarioGuardadoInsertado = service.insertarAsignaturaHorario(asignaturaHorarioGuardado);
            asignaturaHorarioDTO.convertirDTO(asignaturaHorarioGuardadoInsertado);
            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);
        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0005", "Los datos facilitados no satisfacen los requisitos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping("/{id}")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que lo haga devuelve el objeto recuperado de la BD
    public ResponseEntity<?> consultaAsignaturaHorario(@PathVariable Integer id){
        try{
            Optional<AsignaturaHorario> asignaturahorario = service.buscarPorIdAsignaturaHorario(id);
            AsignaturaHorarioDTO asignaturaHorarioDTO = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(asignaturahorario.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);
        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

        }
    }

    @PutMapping("")
    //se le pasa un objeto completo por PUT, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes
    public ResponseEntity<?> editarAsignaturaHorario(@RequestBody AsignaturaHorarioDTO asignaturaHorarioDTO){
        try{
            //compruebo que exista, si no, salta el error del service
            Optional<AsignaturaHorario> asignaturaHorario = service.buscarPorIdAsignaturaHorario(asignaturaHorarioDTO.getId());

            AsignaturaHorario asignaturaHorarioEditada = asignaturaHorarioDTO.convertirModel();
            service.modificarAsignaturaHorario(asignaturaHorarioEditada);
            AsignaturaHorarioDTO asignaturaHorarioDTORecuperado = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(asignaturaHorarioEditada);

            return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaHorarioDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "AsignaturaHorario no encontrado");
            System.out.println("s");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO("E0004", "No se ha introducido una asignaturaHorario válida");
            System.out.println("s");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

        }
    }
    @DeleteMapping("/{id}")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma
    public ResponseEntity<?> eliminarAsignaturaHorario(@PathVariable int id){
        try{
            Optional<AsignaturaHorario> asignaturaHorario = service.buscarPorIdAsignaturaHorario(id);
            service.eliminarAsignaturaHorario(asignaturaHorario.get().getId());

            AsignaturaHorarioDTO asignaturaHorarioDTO = new AsignaturaHorarioDTO();
            asignaturaHorarioDTO.convertirDTO(asignaturaHorario.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(asignaturaHorarioDTO);

        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0002", "AsignaturaHorario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("")
    //lista todos los campos de la BD, en caso de que esta este vacia, devuelve un error personalizado
    public ResponseEntity<?> listarTodoAsignaturaHorario(){
        if(!service.listarTodoAsignaturaHorario().isEmpty()){
            List<AsignaturaHorario> asignaturaHorarioLista = service.listarTodoAsignaturaHorario();

            List<AsignaturaHorarioDTO> asignaturaHorarioDTOLista = new ArrayList<AsignaturaHorarioDTO>();


            for (AsignaturaHorario asig:
                 asignaturaHorarioLista) {
                int cont = 0;
                AsignaturaHorarioDTO asignaturaHorarioDTO = new AsignaturaHorarioDTO();
                asignaturaHorarioDTO.convertirDTO(asig);
                asignaturaHorarioDTOLista.add(asignaturaHorarioDTO);

            }

            return ResponseEntity.status(HttpStatus.OK).body(asignaturaHorarioDTOLista);
        }else{
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/curso/{curso}/estudio/{estudio}/nivel/{nivel}")
    //se le pasan las IDs del curso y estudio, además del nivel, desde AsignaturaHorario relaciona con Asignatura, que es la tabla que tiene--
    //los campos necesarios para hacer la respuesta, compruebo que lo que me devuelve no esté vacío y luego lo devuelvo en caso afirmativo
    public ResponseEntity<?> listarCursoEstudiosYNivel(@PathVariable(value = "curso") int idCurso, @PathVariable(value = "estudio") int idEstudio, @PathVariable(value = "nivel")int nivel) throws NoSeHaEncontradoException {
        try{
            CursoEstudioNivelDTO cursoEstudioNivelDTO = new CursoEstudioNivelDTO(idCurso, idEstudio, nivel);
                List<AsignaturaHorario> asignaturaHorariosRelacionados = service.listarHorariosSegunCursoEstudioNivel(cursoEstudioNivelDTO);
                return ResponseEntity.status(HttpStatus.OK).body(asignaturaHorariosRelacionados);
        }catch (NoSeHaEncontradoException e){
            ErrorDTO errorDTO = new ErrorDTO("E0005", "Los registros de la base de datos no coinciden con los insertados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }
}
