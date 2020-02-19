package br.com.fercugliandro.mastertech.apis.usuarios;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import br.com.fercugliandro.mastertech.apis.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path="/find")
    public ResponseEntity<?> findAll() {

        try {
            List<Usuario> usuarios = usuarioService.findAll();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/find/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable Long idUsuario) {

        try {
            Usuario usuario = usuarioService.findById(idUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/save", consumes="application/json")
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {

        try {
            Usuario user = usuarioService.save(usuario);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/save", consumes="application/json")
    public ResponseEntity<?> edit(@RequestBody Usuario usuario) {

        try {
            Usuario user = usuarioService.save(usuario);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage;
            errorMessage = e + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
