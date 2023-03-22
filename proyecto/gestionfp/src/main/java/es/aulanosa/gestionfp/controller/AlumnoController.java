package es.aulanosa.gestionfp.controller;


import es.aulanosa.gestionfp.dto.*;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.AlumnoEmpresaService;
import es.aulanosa.gestionfp.service.AlumnoService;
import es.aulanosa.gestionfp.service.EmpresaService;
import es.aulanosa.gestionfp.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Controlador de alumnos
 */
@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {
    @Autowired
    AlumnoService service;
    @Autowired
    AlumnoEmpresaService serviceAlumnoEmpresa;

    // Crea un nuevo alumno
    /**
     * Crea un nuevo alumno
     * @param alumnoDTO El alumno a crear
     * @return ResponseEntity<?> codigo 201 con el alumno creado o codigo 404 con lista de errores
     */
    @PostMapping("")
    @Operation(summary = "Crear un nuevo alumno")
    public ResponseEntity<?> crear(@RequestBody AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumnoConsultado = service.buscarPorId(alumnoDTO.getId());

        if(alumnoConsultado.isEmpty()){
            Alumno alumnoguardado = alumnoDTO.toModel();
            service.guardarAlumno(alumnoguardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoguardado);
        }else{
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0001", "El alumno ya existe"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Devuelve un alumno por su id
     * @param id id del alumno a buscar
     * @return ResponseEntity<?> codigo 200 con el alumno encontrado o codigo 404 con mensaje de error
     */
    // Devuelve el usuario cuyo id coincide con el introducido
    @GetMapping("/{id}")
    @Operation(summary = "Devuelve un alumno por su id")
    public ResponseEntity<?> alumnoPorId(@PathVariable Integer id) {
        Optional<Alumno> alumnoConsultado = service.buscarPorId(id);

        if (!alumnoConsultado.isEmpty()) {
            return ResponseEntity.ok(alumnoConsultado);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0002", "No existe ningún alumno con ese ID"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Edita un alumno ya existente
     * @param alumnoDTO
     * @return ResponseEntity<?> codigo 201 con el alumno editado o codigo 404 con mensaje de error
     */
    // Actualiza un usuario ya existente
    @PutMapping("")
    @Operation(summary = "Edita un alumno ya existente")
    public ResponseEntity<?> editar(@RequestBody AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumnoConusltado = service.buscarPorId(alumnoDTO.getId());

        if (!alumnoConusltado.isEmpty()) {
            Alumno alumnoActualizado = alumnoDTO.toModel();
            service.guardarAlumno(alumnoActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoActualizado);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0002", "No existe ningún alumno con ese ID"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Borra un alumno por su id
     * @param id
     * @return ResponseEntity<?> codigo 204 con el alumno borrado o codigo 404 con mensaje de error
     * @throws NoSeHaEncontradoException si no se encuentra el alumno
     */
    // Borra el usuario cuyo id coincide con el introducido
    @DeleteMapping("/{id}")
    @Operation(summary = "Borra un alumno por su id")
    public ResponseEntity<?> borrarPorid(@PathVariable Integer id) throws NoSeHaEncontradoException {
        Optional<Alumno> alumnoConusltado = service.buscarPorId(id);
        if (alumnoConusltado.isPresent()) {
            service.eliminarAlumno(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0002", "No existe ningún alumno con ese ID"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Devuelve un listado de todos los alumnos
     * @return ResponseEntity<?> codigo 200 con el listado de alumnos o codigo 404 con mensaje de error
     */
    // Devuelve un listado con todos los usuarios
    @GetMapping("")
    @Operation(summary = "Devuelve un listado de todos los alumnos")
    public ResponseEntity<?> buscarTodo() {
        List<Alumno> alumnos = service.buscarTodo();

        if (!alumnos.isEmpty()) {
            return ResponseEntity.ok(alumnos);













        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0003", "No existen alumnos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Devuelve un listado de las empresas asignadas a un alumno
     * @param id
     * @return ResponseEntity<?> codigo 200 con el listado de empresas o codigo 404 con mensaje de error
     */
    // Devuelve un listado de las empresas
    @GetMapping("/{id}/empresa")
    public ResponseEntity<?> buscarPorEmpresa(@PathVariable Integer id) {
        //listado de empresas que devuelve
        List<Empresa> listaEmpresas = serviceAlumnoEmpresa.buscarTodasEmpresasPorIdAlumno(id);

        //lista de empresasDTO para poder recuperar datos
        List<EmpresaDTO> listaEmpresasDTO = new ArrayList<>();

        //pasar cada empresa recuperada a dto
        for (Empresa empresa : listaEmpresas) {
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.crearDTO(empresa);
            listaEmpresasDTO.add(empresaDTO);
        }
        if(!listaEmpresas.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(listaEmpresasDTO);
        }else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0004", "No se encontraron empresas asignadas al alumno consultado"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    //devuelve alumno por usuario
    /**
     * Devuelve un alumno por su usuario
     * @param usuario
     * @return ResponseEntity<?> codigo 200 con el alumno encontrado o codigo 404 con mensaje de error
     */
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<?> alumnoPorUsuario(@PathVariable String usuario) {
        Alumno alumnoConsultado = service.buscarPorUsuario(usuario);

        if (alumnoConsultado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoConsultado);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0005", "No existe ningún alumno con ese usuario"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Devuelve un listado de alumnos que tengan el estado activo
     * @return ResponseEntity<?> codigo 200 con el listado de alumnos o codigo 404 con mensaje de error
     * @throws NoSeHaEncontradoException si no se encuentra el alumno
     */
    @GetMapping("/estado")
    //Devuelve un listado de alumnos que tengan el estado activo
    public ResponseEntity<?> buscarPorEstado() throws NoSeHaEncontradoException {
        List<Alumno> alumnos = service.buscarPorEstado();

        if (!alumnos.isEmpty()) {
            return ResponseEntity.ok(alumnos);
        } else {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0006", "No existen alumnos con ese estado"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
        }
    }
    /**
     * Devuelve un listado de la empresa asignada a un alumno con el id del curso y el id del estudio
     * @param idCurso
     * @param idEstudio
     * @return ResponseEntity<?> codigo 200 con el listado de alumnos o codigo 404 con mensaje de error
     */
    @GetMapping("/empresa/{idCurso}/{idEstudio}")
    //Devuelve un listado de alumnos que tengan el estado activo
    public ResponseEntity<?> buscarAlumnoEmpresa(@PathVariable int idCurso, @PathVariable int idEstudio) {
        List<Alumno> listaAlumnosEmpresas = service.buscarPorCursoYEstudios(idCurso,idEstudio);

        List<AlumnoEmpresaDTO> listaAlumnoEmpresaDTO = new ArrayList<>();
        for(Alumno alumno : listaAlumnosEmpresas) {
            AlumnoEmpresaDTO alumnoEmpresaDTO = new AlumnoEmpresaDTO();
            alumnoEmpresaDTO.setIdAlumno(alumno.getId());
            alumnoEmpresaDTO.setNombreAlumno(alumno.getNombre());
            if (alumno.getEmpresa() != null) {
                alumnoEmpresaDTO.setIdEmpresa(alumno.getIdEmpresa());
                alumnoEmpresaDTO.setNombreEmpresa(alumno.getEmpresa().getNombre());
            } else {
                alumnoEmpresaDTO.setIdEmpresa(0);
                alumnoEmpresaDTO.setNombreEmpresa("");
            }
            alumnoEmpresaDTO.setEstado(' ');
            listaAlumnoEmpresaDTO.add(alumnoEmpresaDTO);
        }

        return ResponseEntity.ok(listaAlumnoEmpresaDTO);
    }
}
