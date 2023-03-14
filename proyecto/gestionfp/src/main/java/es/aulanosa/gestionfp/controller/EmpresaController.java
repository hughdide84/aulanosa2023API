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

    //Se insertan datos a la tabla empresa
    @PostMapping
    public ResponseEntity<?> altaEmpresa(@RequestBody EmpresaDTO empresaDTO) {

        Empresa consultarEmpresa = empService.findById(empresaDTO.getId());

        if (consultarEmpresa == null) {
            Empresa empresaGuardada = empresaDTO.convertirModel();
            empService.save(empresaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaDTO);
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empresa ya existente");
        }
    }

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

    @PutMapping
    public ResponseEntity<?> editarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresaParaActualizar = empService.findById(empresaDTO.getId());

        if (empresaParaActualizar != null) {
            Empresa empresaGuardada = empresaDTO.convertirModel();
            empService.save(empresaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaGuardada);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la empresa a modificar");
        }
    }

    

    public String checkFieldSize(UsuarioDTO usuarioDTO) {
        List<String> invalidFields = new ArrayList<>();
        String msg = "";

        if (usuarioDTO.getNombre().length() > 50) {
            invalidFields.add("nombre");
        } else if (usuarioDTO.getPassword().length() > 100) {
            invalidFields.add("password");
        } else if (usuarioDTO.getRol().length() > 10) {
            invalidFields.add("rol");
        } else if (usuarioDTO.getEmail().length() > 50) {
            invalidFields.add("email");
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
