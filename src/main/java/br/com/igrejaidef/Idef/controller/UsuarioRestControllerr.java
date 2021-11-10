package br.com.igrejaidef.Idef.controller;

import br.com.igrejaidef.Idef.dto.modelo.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public interface UsuarioRestControllerr {

    @GetMapping
    ResponseEntity<List<Usuario>> ListarTodosUsuarios() throws Exception;

    @PostMapping
    ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) throws Exception;

    @PutMapping
    ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) throws Exception;

    @DeleteMapping
    ResponseEntity<Usuario> deletarUsuario(@RequestParam long id) throws Exception;

    @GetMapping("/buscarole/{login}")
    ResponseEntity<String> buscaRole(@RequestParam(name = "login") String login) throws Exception;

}
