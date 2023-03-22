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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/alumnoExterno")
//falta @tag

public class AlumnoExternoController {

    @Autowired
    private AlumnoExternoServiceImp service;

    @PostMapping("")
    //falta @operation
    //API para dar de alta, se le pasa un objeto DTO por POST, lo convierte al model y lo inserta
    public ResponseEntity<?> altaAlumnosExternos(@RequestBody AlumnoExternoDTO alumnoExternoDTO) throws NoSeHaEncontradoException {

        try {
            AlumnoExterno alumnosExternos = alumnoExternoDTO.convertirModel();
            AlumnoExterno alumnosExternosGuardado = service.guardar(alumnosExternos);
            alumnoExternoDTO.crearDTO(alumnosExternosGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoExternoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    //consulta por id, se le pasa como variable el mismo, consulta si existe y en caso de que l ohaga devuelve el objeto recuperado de la BD
    public ResponseEntity<?> consultaAlumnosExternos(@PathVariable Integer id) {
        if (id == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else {
            try {
                Optional<AlumnoExterno> alumnosExternos = service.listarPorId(id);
                if (alumnosExternos.isPresent()) {
                    AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
                    alumnoExternoDTO.crearDTO(alumnosExternos.get());

                    return ResponseEntity.status(HttpStatus.OK).body(alumnoExternoDTO);
                } else {
                    ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
                }
            } catch (NoSeHaEncontradoException e) {
                ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        }
    }

    @PutMapping("")
    //se le pasa un objeto completo por POST, el programa comprueba que su ID exista en la BD y en caso de que lo haga cambia los valores que estén diferentes
    public ResponseEntity<?> editarAlumnosExternos(@RequestBody AlumnoExternoDTO alumnoExternoDTO) {
        try {
            AlumnoExterno alumnosExternos = alumnoExternoDTO.convertirModel();
            AlumnoExterno alumnosExternosGuardado = service.modificar(alumnosExternos);
            alumnoExternoDTO.crearDTO(alumnosExternosGuardado);
            return ResponseEntity.status(HttpStatus.OK).body(alumnoExternoDTO);
        } catch (Exception e) {
            List<ErrorDTO> errores = new ArrayList<>();
            errores.add(new ErrorDTO("E0001", "ID no encontrado"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
    }

    @DeleteMapping("/{id}")
    //se le pasa un ID por API, el programa comprueba que exista en la BD y en caso afirmativo se borra de la misma
    public ResponseEntity<?> eliminarAlumnosExternos(@PathVariable int id) throws NoSeHaEncontradoException {
        Optional<AlumnoExterno> alumnosExterno = service.listarPorId(id);

        if (alumnosExterno.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            ErrorDTO errorDTO = new ErrorDTO("E0001", "ID no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("")
    //lista todos los campos de la BD, en caso de que esta este vacia, devuelve un error personalizado
    public ResponseEntity<?> listarTodosAlumnosExternos() {
        if (!service.listarTodo().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTodo());
        } else {
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> buscarPorEstadoAlumnosExternos() throws NoSeHaEncontradoException {
        if (!service.buscarPorEstado().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorEstado());
        } else {
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/nombreEs/{nombre}")
    public ResponseEntity<?> buscarPorNombreEsAlumnosExternos(@PathVariable String nombre) throws NoSeHaEncontradoException {
        AlumnoExterno alumnoExternoConsultado = service.buscarPorNombreEs(nombre);

        if (alumnoExternoConsultado != null) {
            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnoExternoConsultado);
            return ResponseEntity.status(HttpStatus.OK).body(alumnoExternoDTO);
        } else {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/nombreContiene/{nombre}")
    public ResponseEntity<?> buscarPorNombreConteniendoAlumnosExternos(@PathVariable String nombre) throws NoSeHaEncontradoException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNombreConteniendo(nombre));
        } catch (Exception e) {
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/emailEs/{email}")
    public ResponseEntity<?> buscarPorEmailEsAlumnosExternos(@PathVariable String email) throws NoSeHaEncontradoException {
        AlumnoExterno alumnoExternoConsultado = service.buscarPorEmailEs(email);

        if (alumnoExternoConsultado != null) {
            try {
                AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
                alumnoExternoDTO.crearDTO(alumnoExternoConsultado);
                return ResponseEntity.status(HttpStatus.OK).body(alumnoExternoDTO);
            } catch (Exception e) {
                List<ErrorDTO> errorDTO = new ArrayList<>();
                errorDTO.add(new ErrorDTO("E0003", "Error al consultar"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
            }
        } else {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/emailContiene/{email}")
    public ResponseEntity<?> buscarPorEmailConteniendoAlumnosExternos(@PathVariable String email) throws NoSeHaEncontradoException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorEmailConteniendo(email));
        } catch (Exception e) {
            ErrorDTO errorDTO = new ErrorDTO("E0003", "No hay registros en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<?> buscarPorTelefono(@PathVariable String telefono) {

        AlumnoExterno alumnoExternoConsultado = service.buscarPorTelefono(telefono);

        if (alumnoExternoConsultado != null) {
            AlumnoExternoDTO alumnoExternoDTO = new AlumnoExternoDTO();
            alumnoExternoDTO.crearDTO(alumnoExternoConsultado);
            return ResponseEntity.status(HttpStatus.OK).body(alumnoExternoDTO);
        } else {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/universidadEs/{universidad}")
    public ResponseEntity<?> buscarPorUniversidadEsAlumnosExternos(@PathVariable String universidad) throws NoSeHaEncontradoException {

        try  {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorUniversidadEs(universidad));
        } catch (Exception e) {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/universidadContiene/{universidad}")
    public ResponseEntity<?> buscarPorUniversidadConteniendoAlumnosExternos(@PathVariable String universidad) throws NoSeHaEncontradoException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorUniversidadConteniendo(universidad));
        } catch (Exception e) {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/titulacionEs/{titulacion}")
    public ResponseEntity<?> buscarPorTitulacionEsAlumnosExternos(@PathVariable String titulacion) throws NoSeHaEncontradoException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorTitulacionEs(titulacion));
        } catch (Exception e) {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/titulacionContiene/{titulacion}")
    public ResponseEntity<?> buscarPorTitulacionConteniendoAlumnosExternos(@PathVariable String titulacion) throws NoSeHaEncontradoException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorTitulacionConteniendo(titulacion));
        } catch (Exception e) {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/especialidadEs/{especialidad}")
    public ResponseEntity<?> buscarEspecialidadEsAlumnosExternos(@PathVariable String especialidad) throws NoSeHaEncontradoException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorEspecialidadEs(especialidad));
        } catch (Exception e) {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }

    @GetMapping("/especialidadContiene/{especialidad}")
    public ResponseEntity<?> buscarPorEspecialidadConteniendoAlumnosExternos(@PathVariable String especialidad) throws NoSeHaEncontradoException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorEspecialidadConteniendo(especialidad));
        } catch (Exception e) {
            List<ErrorDTO> errorDTO = new ArrayList<>();
            errorDTO.add(new ErrorDTO("E0003", "No hay registros en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
        }
    }


}
