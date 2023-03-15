package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.EmpresaDTO;
import es.aulanosa.gestionfp.model.Empresa;
import es.aulanosa.gestionfp.service.EmpresaService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empService;



    //Metodo para insertar datos a la tabla empresa
    @PostMapping
    public ResponseEntity<?> altaEmpresa(@RequestBody EmpresaDTO empresaDTO) {

        Empresa consultarEmpresa = empService.findById(empresaDTO.getId());

        if (consultarEmpresa == null && checkFieldSize(empresaDTO).equals("")) {
            Empresa empresaGuardada = empresaDTO.convertirModel();
            empService.save(empresaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
        } else if (!checkFieldSize(empresaDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud de caracteres excedida de los siguientes campos: " + checkFieldSize(empresaDTO));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empresa ya existente");
        }
    }

    //Este metodo busca un campo de la tabla empresa mediante su id
    @GetMapping("{id}")
    public ResponseEntity<?> consultarEmpresa(@RequestBody Integer id) {
        Empresa empresa = empService.findById(id);

        if (empresa != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(empresa);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }
    }

    //Este metodo busca un campo por id para modificarlo a partir del body que se le pasa.
    @PutMapping
    public ResponseEntity<?> editarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresaParaActualizar = empService.findById(empresaDTO.getId());

        if (empresaParaActualizar != null && checkFieldSize(empresaDTO).equals("")) {
            Empresa empresaGuardada = empresaDTO.convertirModel();
            empService.save(empresaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaGuardada);
        } else if (!checkFieldSize(empresaDTO).equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Longitud de caracteres excedida de los siguientes campos: " + checkFieldSize(empresaDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la empresa a modificar");
        }
    }

    //Este metodo busca un campo de la tabla empresa para eliminarlo.
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarEmpresa(@RequestBody Integer id) {
        Empresa empresa = empService.findById(id);

        if (empresa != null) {
            empService.deleteById(id);
            if (empresa == null) {
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

    @GetMapping
    public ResponseEntity<?> listarTodasEmpresas() {
        List<Empresa> empresa = empService.findAll();

        if (empresa != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(empresa);
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, no se ha podido ejecutar esta funcion");
        }
    }
    

    public String checkFieldSize(EmpresaDTO empresaDTO) {
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
}
