package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.model.AlumnosExternos;
import es.aulanosa.gestionfp.service.AlumnosExternosServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/alumnoExterno")
//falta @tag

public class AlumnosExternosController {

    @Autowired
    private AlumnosExternosServiceImp service;

    @PostMapping("/")
    //falta @operation
    public ResponseEntity<?> alta(AlumnosExternos alumnosExternos){
        AlumnosExternos alumnosExternos1
    }

}
