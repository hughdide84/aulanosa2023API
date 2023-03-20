package es.aulanosa.gestionfp.controller;


import es.aulanosa.gestionfp.dto.AlumnoDTO;
import es.aulanosa.gestionfp.dto.EmpresaDTO;
import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.AlumnoEmpresaService;
import es.aulanosa.gestionfp.service.AlumnoService;
import es.aulanosa.gestionfp.service.EmpresaService;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    @Autowired
    AlumnoService service;
    @Autowired
    AlumnoEmpresaService serviceAlumnoEmpresa;

    // Crea un nuevo alumno
    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumnoConsultado = service.buscarPorId(alumnoDTO.getId());

        if(alumnoConsultado.isEmpty()){
            Alumno alumnoguardado = alumnoDTO.toModel();
            service.guardarAlumno(alumnoguardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoguardado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno ya existe");
        }

    }

    // Devuelve el usuario cuyo id coincide con el introducido
    @GetMapping("/{id}")
    public ResponseEntity<?> alumnoPorId(@PathVariable Integer id) {
        Optional<Alumno> alumnoConsultado = service.buscarPorId(id);

        if (!alumnoConsultado.isEmpty()) {
            return ResponseEntity.ok(alumnoConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún alumno con ese ID");
        }
    }

    // Actualiza un usuario ya existente
    @PutMapping("")
    public ResponseEntity<?> editar(@RequestBody AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumnoConusltado = service.buscarPorId(alumnoDTO.getId());

        if (!alumnoConusltado.isEmpty()) {
            Alumno alumnoActualizado = alumnoDTO.toModel();
            service.guardarAlumno(alumnoActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario que desea modificar no existe");
        }
    }

    // Borra el usuario cuyo id coincide con el introducido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPorid(@PathVariable Integer id) throws NoSeHaEncontradoException {
        Optional<Alumno> alumnoConusltado = service.buscarPorId(id);


        if (alumnoConusltado.isPresent()) {
            service.eliminarAlumno(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno que desea eliminar no existe");
        }

    }

    // Devuelve un listado con todos los usuarios
    @GetMapping("")
    public ResponseEntity<?> buscarTodo() {
        List<Alumno> alumnos = service.buscarTodo();

        if (!alumnos.isEmpty()) {
            return ResponseEntity.ok(alumnos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alumnos");
        }
    }
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron empresas asignadas al alumno consultado");

        }
    }

    //devuelve alumno por usuario
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<?> alumnoPorUsuario(@PathVariable String usuario) {
        Alumno alumnoConsultado = service.buscarPorUsuario(usuario);

        if (alumnoConsultado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún alumno con ese usuario");
        }
    }



}
