package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.AlumnoEmpresaDTO;
import es.aulanosa.gestionfp.dto.EmpresaDTO;
import es.aulanosa.gestionfp.excepciones.NoSeHaEncontradoException;
import es.aulanosa.gestionfp.model.Alumno;
import es.aulanosa.gestionfp.model.AlumnoEmpresa;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.service.AlumnoEmpresaService;
import es.aulanosa.gestionfp.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase controladora de endpoints relacionados con la entidad Empresa.
 */
@RestController
@RequestMapping("/api/empresa")
@Tag(name = "Empresas",description = "Operaciones con empresas ")
public class EmpresaController {

    @Autowired
    private EmpresaService empService;

    @Autowired
    private AlumnoEmpresaService alumnoEmpresaService;



    /**
     * Método para insertar datos a la tabla empresa.
     * @param empresaDTO Objeto que contiene los datos de la empresa a insertar.
     * @return ResponseEntity que indica si se ha insertado la empresa correctamente o no.
     */
    @PostMapping("")
    @Operation(summary = "Inserta una empresa")
    public ResponseEntity<?> altaEmpresa(@RequestBody EmpresaDTO empresaDTO) {

        Empresa consultarEmpresa = empService.findById(empresaDTO.getId());

        if (consultarEmpresa == null && comprobarTamanoCampos(empresaDTO).equals("")) {
            Empresa empresaGuardada = empresaDTO.convertirModel();
            empService.save(empresaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
        } else if (!comprobarTamanoCampos(empresaDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud de caracteres excedida de los siguientes campos: " + comprobarTamanoCampos(empresaDTO));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empresa ya existente");
        }
    }

    /**
     * Este método busca un campo de la tabla empresa mediante su id.
     * @param id Identificador de la empresa a buscar.
     * @return ResponseEntity con la empresa encontrada o un mensaje de error si no se ha encontrado.
     */
    @GetMapping("{id}")
    @Operation(summary = "Consulta una empresa")
    public ResponseEntity<?> consultarEmpresa(@PathVariable("id") Integer id) {
        Empresa empresa = empService.findById(id);

        if (empresa != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(empresa);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }
    }

    /**
     * Este método busca un campo por id para modificarlo a partir del body que se le pasa.
     * @param empresaDTO Objeto que contiene los datos de la empresa a modificar.
     * @return ResponseEntity que indica si se ha modificado la empresa correctamente o no.
     */
    @PutMapping("")
    @Operation(summary = "Edita una empresa")
    public ResponseEntity<?> editarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresaParaActualizar = empService.findById(empresaDTO.getId());

        if (empresaParaActualizar != null && comprobarTamanoCampos(empresaDTO).equals("")) {
            Empresa empresaGuardada = empresaDTO.convertirModel();
            empService.save(empresaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaGuardada);
        } else if (!comprobarTamanoCampos(empresaDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud de caracteres excedida de los siguientes campos: " + comprobarTamanoCampos(empresaDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la empresa a modificar");
        }
    }

    /**
     * Este método busca un campo de la tabla empresa para eliminarlo.
     * @param id Identificador de la empresa a eliminar.
     * @return ResponseEntity que indica si se ha eliminado la empresa correctamente o no.
     */
    @DeleteMapping("{id}")
    @Operation(summary = "Elimina una empresa por id")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable("id") Integer id) {
        Empresa empresa = empService.findById(id);

        if (empresa != null) {
            int idEmp = empresa.getId();
            empService.deleteById(id);
            Empresa empresaComp = empService.findById(idEmp);
            if (empresaComp == null) {
                return ResponseEntity.ok("Campo seleccionado borrado con exito");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, no se ha podido eliminar el campo seleccionado");
            }
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado el campo seleccionado");
        }
    }
    /**
     * Este método lista todas las empresas almacenadas en la base de datos.
     * @return ResponseEntity con la lista de empresas encontradas o un mensaje de error si no se han encontrado.
     */
    @GetMapping("")
    @Operation(summary = "Lista todas las empresas")
    public ResponseEntity<?> listarTodasEmpresas() {
        List<Empresa> empresa = empService.findAll();

        if (empresa != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(empresa);
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, no se ha podido ejecutar esta funcion");
        }
    }
    /**
     * Este método lista todos los alumnos asociados a una empresa determinada.
     * @param id Identificador de la empresa a buscar.
     * @return ResponseEntity con la lista de alumnos encontrados o un mensaje de error si no se han encontrado.
     */
    @GetMapping("{id}/alumno")
    @Operation(summary = "Lista alumnos asociados a una empresa")
    public ResponseEntity<?> listarAlumnosEnEmpresa(@PathVariable("id") Integer id) {
        List<Alumno> alumnoEmp = alumnoEmpresaService.buscarTodosAlumnosPorIdEmpresa(id);

        if (alumnoEmp != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(alumnoEmp);
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, no se pude ejecutar esta opcion");
        }
    }
    
    //Metodo para controlar los tamaños de los campos con longitud determinada
    public String comprobarTamanoCampos(EmpresaDTO empresaDTO) {
        List<String> invalidFields = new ArrayList<>();
        String msg = "";

        if (empresaDTO.getNombre().length() > 100) {
            invalidFields.add("nombre");
        } else if (empresaDTO.getDireccionSocial().length() > 300) {
            invalidFields.add("direccionSocial");
        } else if (empresaDTO.getDireccionTrabajo().length() > 300) {
            invalidFields.add("direccionTrabajo");
        } else if (empresaDTO.getCif().length() > 10) {
            invalidFields.add("cif");
        } else if (empresaDTO.getRepresentante().length() > 100) {
            invalidFields.add("representante");
        } else if (empresaDTO.getContacto().length() > 100) {
            invalidFields.add("contacto");
        } else if (empresaDTO.getTutor1().length() > 100) {
            invalidFields.add("tutor1");
        } else if (empresaDTO.getTutor2().length() > 100) {
            invalidFields.add("tutor2");
        } else if (empresaDTO.getTutor3().length() > 100) {
            invalidFields.add("tutor3");
        } else {

        }

        if (invalidFields.size() != 0) {
            for (int i = 0; i < invalidFields.size(); i++) {
                if (i != invalidFields.size() - 1) {
                    msg += invalidFields.get(i) + ", ";
                } else {
                    msg += invalidFields.get(i);
                }
            }
            return msg;
        } else {
            return msg;
        }
    }
    /**
     Método que busca las empresas de los alumnos asociados a un curso y estudio específico
     y devuelve una lista de AlumnoEmpresaDTO que contiene la información de la relación entre el alumno y la empresa.
     @param idCurso identificador del curso a buscar
     @param idEstudio identificador del estudio a buscar
     @return ResponseEntity con la lista de AlumnoEmpresaDTO encontrados
     */
    @GetMapping("/alumno/{idCurso}/{idEstudio}")
    @Operation(summary = "Lista empresas en las que esta un alumno")
    public ResponseEntity<?> buscarEmpresaAlumno(@PathVariable int idCurso, @PathVariable int idEstudio) {
        List<AlumnoEmpresa> listaAlumnosEmpresas = alumnoEmpresaService.buscarEmpresasAlumnos(idCurso,idEstudio);

        List<AlumnoEmpresaDTO> listaAlumnoEmpresaDTO = new ArrayList<>();
        for(AlumnoEmpresa alumnoEmpresa : listaAlumnosEmpresas) {
            AlumnoEmpresaDTO alumnoEmpresaDTO = new AlumnoEmpresaDTO();
            alumnoEmpresaDTO.crearDTO(alumnoEmpresa);
            listaAlumnoEmpresaDTO.add(alumnoEmpresaDTO);
        }

        return ResponseEntity.ok(listaAlumnoEmpresaDTO);
    }

    /**
     *
     * @param idCurso
     * @param idEstudio
     * @return
     * @throws NoSeHaEncontradoException
     */
    @GetMapping("/curso/{idCurso}/estudio/{idEstudio}")
    public ResponseEntity<?> buscarEmpresaPorCursoYEstudios(@PathVariable int idCurso, @PathVariable int idEstudio) throws NoSeHaEncontradoException {
        List<Empresa> empresas = empService.buscarEmpresasPorCursoYEstudios(idCurso, idEstudio);

        List<EmpresaDTO empresasDTO = new ArrayList<>();
        for (Empresa empresa :
                empresas) {
            EmpresaDTO empresaDTO = new EmpresaDTO();

            empresasDTO.add(empresaDTO.crearDTO(empresa));
        }

        return ResponseEntity.status(HttpStatus.OK).body(empresasDTO);
    }
}
