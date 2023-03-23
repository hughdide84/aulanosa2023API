package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.MatriculaDTO;
import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.service.MatriculasService;
import es.aulanosa.gestionfp.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Clase que gestiona las peticiones relacionadas con las matriculas
 */
    @RestController
    @RequestMapping("/api/matricula")
    public class MatriculaController {

        @Autowired
        MatriculasService service;
        @Autowired
        UsuarioService serviceUsu;

    /**
     * Crea una nueva matricula
     * @param matriculaDTO
     * @return una nueva matricula
     */
        // Crea una nueva matricula
        @PostMapping("")
        @Operation(summary = "Crea una nueva matricula")
        public ResponseEntity<?> crearMatricula(@RequestBody MatriculaDTO matriculaDTO) {

            try{
                if(serviceUsu.listarPorId(matriculaDTO.getIdUsuario()) != null){
                    Matricula matriculaGuardado = matriculaDTO.toModel();
                    service.insertarMatricula(matriculaGuardado);
                    return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDTO.toDTO(matriculaGuardado));
                }else{
                    List<ErrorDTO> errorDTO = new ArrayList<>();
                    errorDTO.add(new ErrorDTO("E001","El idUsuario no existe"));
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
                }

            }catch (NoSuchElementException e){
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","Error con un campo usado como clave foranea"));;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }

        // Devuelve la matricula del mensaje cuyo id coincide con el introducido

    /**
     * Devuelve la matricula del mensaje cuyo id coincide con el introducido
     * @param id
     * @return la matricula del mensaje cuyo id coincide con el introducido
     */
        @GetMapping("/{id}")
        @Operation(summary = "Devuelve la matricula del mensaje cuyo id coincide con el introducido")
        public ResponseEntity<?> obtenerMatriculaPorId(@PathVariable Integer id) {
            Optional<Matricula> matriculaConsultado = service.consultarPorIdMatricula(id);

            if (matriculaConsultado.isPresent()) {
                return ResponseEntity.ok(matriculaConsultado);
            } else {
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","No existe ninguna matricula con ese ID"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }
        //edita la matricula ya existente

    /**
     * edita la matricula ya existente
     * @param matriculaDTO
     * @return la matricula ya existente editada
     */
        @PutMapping("")
        @Operation(summary = "Edita la matricula ya existente")
        public ResponseEntity<?> editarMatricula(@RequestBody MatriculaDTO matriculaDTO) {
            Optional<Matricula> matriculaConsultar = service.consultarPorIdMatricula(matriculaDTO.getId());

            if (matriculaConsultar.isPresent()) {
                Matricula matriculaActualizar = matriculaDTO.toModel();
                service.insertarMatricula(matriculaActualizar);
                return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDTO.toDTO(matriculaActualizar));
            } else {
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","No se ha encontrado la matricula a modificar"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }

        //elimina la matricula existente que tenga el id que se le pasa

    /**
     * elimina la matricula existente que tenga el id que se le pasa
     * @param id
     * @return la matricula existente que tenga el id que se le pasa
     */
        @DeleteMapping("/{id}")
        @Operation(summary = "Elimina la matricula existente que tenga el id que se le pasa")
        public ResponseEntity<?> borrarMatriculaPorId(@PathVariable Integer id) {
            Optional<Matricula> matriculaConsultado = service.consultarPorIdMatricula(id);

            if (matriculaConsultado.isPresent()) {
                service.eliminarMatricula(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                List <ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","No se ha encontrado la matricula a eliminar"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }
        //Lista todos los datos de la tabla matriculas

    /**
     * Lista todos los datos de la tabla matriculas
     * @return todos los datos de la tabla matriculas
     */
       @GetMapping("")
         @Operation(summary = "Lista todos los datos de la tabla matriculas")
        public ResponseEntity<?> listarTodo() {
           if (!service.consultarTodasMatriculas().isEmpty()) {
               return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodasMatriculas());
           } else {
               List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E003","No hay registros en la base de datos"));
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
           }
       }
        //Lista los datos que contengan el id del curso filtrado

    /**
     * Lista los datos que contengan el id del curso filtrado
     * @param idCurso
     * @return los datos que contengan el id del curso filtrado
     */
       @GetMapping("cursoEs/{idCurso}")
       @Operation(summary = "Lista los datos que contengan el id del curso filtrado")
        public ResponseEntity<?> listarPorCurso(@PathVariable Integer idCurso){
            List<Matricula> listadoCurso = service.buscarTodosCursosPorId(idCurso);
            if (!listadoCurso.isEmpty()) {
                return  ResponseEntity.status(HttpStatus.OK).body(listadoCurso);
            }
            else {
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","No se ha encontrado ninguna matricula con ese idCurso"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
       }
        //Lista los datos que contengan el mes filtrado dentro del campo fecha

    /**
     * Lista los datos que contengan el mes filtrado dentro del campo fecha
     * @param mes
     * @return los datos que contengan el mes filtrado dentro del campo fecha
     */
       @GetMapping("mesEs/{mes}")
         @Operation(summary = "Lista los datos que contengan el mes filtrado dentro del campo fecha")
        public ResponseEntity<?> listarPorMes(@PathVariable Integer mes){
            List<Matricula> listadoMes = service.buscarPorMesDeMatricula(mes);
            if (!listadoMes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(listadoMes);
            }
            else {
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","No se ha encontrado ninguna matricula con ese mes"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
       }
        //Lista los datos de las filas que contengan el nombre filtrado

    /**
     * Lista los datos de las filas que contengan el nombre filtrado
     * @param nombre
     * @return los datos de las filas que contengan el nombre filtrado
     */
       @GetMapping("nombreContiene/{nombre}")
         @Operation(summary = "Lista los datos de las filas que contengan el nombre")
        public ResponseEntity<?> listarPorNombre(@PathVariable String nombre) {
            List<Matricula> listarNombre = service.buscarPorNombreDeMatricula(nombre);

            if(!listarNombre.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(listarNombre);
            }
            else {
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E001","No se ha encontrado ninguna matricula con ese nombre"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }


       }
}
