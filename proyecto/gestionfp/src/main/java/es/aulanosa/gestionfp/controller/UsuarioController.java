package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    //@Autowired
    //UsuarioService service;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {

    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {

    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {

    }
}
