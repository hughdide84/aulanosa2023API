package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorDTO;
import es.aulanosa.gestionfp.dto.MatriculaDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Matricula;
import es.aulanosa.gestionfp.service.MatriculasService;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


    @RestController
    @RequestMapping("/api/matricula")
    public class MatriculaController {

        @Autowired
        MatriculasService service;
        @Autowired
        UsuarioService serviceUsu;


        // Crea una nueva matricula
        @PostMapping("")
        public ResponseEntity<?> crearMatricula(@RequestBody MatriculaDTO matriculaDTO) {


            try{
                if(serviceUsu.listarPorId(matriculaDTO.getIdUsuario()) != null){
                    Matricula matriculaGuardado = matriculaDTO.toModel();
                    service.insertarMatricula(matriculaGuardado);
                    return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDTO.toDTO(matriculaGuardado));
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El idUsuario no existe");
                }


            }catch (NoSuchElementException e){
                ErrorDTO errorDTO = new ErrorDTO("E001","Error con un campo usado como clave foranea");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }


        }

        // Devuelve la matricula del mensaje cuyo id coincide con el introducido
        @GetMapping("/{id}")
        public ResponseEntity<?> obtenerMatriculaPorId(@PathVariable Integer id) {
            Optional<Matricula> matriculaConsultado = service.consultarPorIdMatricula(id);

            if (matriculaConsultado.isPresent()) {
                return ResponseEntity.ok(matriculaConsultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningúna matricula con ese ID");
            }
        }
        //edita la matricula ya existente
        @PutMapping("")
        public ResponseEntity<?> editarMatricula(@RequestBody MatriculaDTO matriculaDTO) {
            Optional<Matricula> matriculaConsultar = service.consultarPorIdMatricula(matriculaDTO.getId());

            if (matriculaConsultar.isPresent()) {
                Matricula matriculaActualizar = matriculaDTO.toModel();
                service.insertarMatricula(matriculaActualizar);
                return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDTO.toDTO(matriculaActualizar));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la matricula a modificar.");
            }
        }

        //elimina la matricula existente que tenga el id que se le pasa
        @DeleteMapping("/{id}")
        public ResponseEntity<?> borrarMatriculaPorId(@PathVariable Integer id) {
            Optional<Matricula> matriculaConsultado = service.consultarPorIdMatricula(id);

            if (matriculaConsultado.isPresent()) {
                service.eliminarMatricula(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La matricula que desea eliminar no existe");
            }
        }
        //Lista todos los datos de la tabla matriculas
       @GetMapping("")
        public ResponseEntity<?> listarTodo() {
           if (!service.consultarTodasMatriculas().isEmpty()) {
               return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodasMatriculas());
           } else {
               ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
           }
       }
        //Lista los datos que contengan el id del curso filtrado
       @GetMapping("cursoEs/{idCurso}")
        public ResponseEntity<?> listarPorCurso(@PathVariable Integer idCurso){
            List<Matricula> listadoCurso = service.buscarTodosCursosPorId(idCurso);
            if (!listadoCurso.isEmpty()) {
                return  ResponseEntity.status(HttpStatus.OK).body(listadoCurso);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricula no encontrado");
            }
       }
        //Lista los datos que contengan el mes filtrado dentro del campo fecha
       @GetMapping("mesEs/{mes}")
        public ResponseEntity<?> listarPorMes(@PathVariable Integer mes){
            List<Matricula> listadoMes = service.buscarPorMesDeMatricula(mes);
            if (!listadoMes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(listadoMes);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricula no encontrada");
            }
       }
        //Lista los datos de las filas que contengan el nombre filtrado
       @GetMapping("nombreContiene/{nombre}")
        public ResponseEntity<?> listarPorNombre(@PathVariable String nombre) {
            List<Matricula> listarNombre = service.buscarPorNombreDeMatricula(nombre);

            if(!listarNombre.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(listarNombre);
            }
            else {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricula no encontrada");
            }


       }

       //devuelve los saldos menores a 0 listados por un curso en especifico
        @GetMapping("pagosAtrasados/{idCurso}")
        public ResponseEntity<?> buscaridCursoSaldoNegativo(@PathVariable Integer idCurso) throws NoSeHaEncontradoException {
            List<Matricula> listaNegativa = service.buscaridCursoSaldoNegativo(idCurso);

            if(!listaNegativa.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(listaNegativa);
            }else{
                ErrorDTO errorDTO = new ErrorDTO("E0001", "No se han encontrado saldos negativos para el IdCurso especificado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }
}
