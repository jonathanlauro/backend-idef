package br.com.igrejaidef.Idef.controller;

import br.com.igrejaidef.Idef.Service.CadastroDeUsuario;
import br.com.igrejaidef.Idef.dto.mapper.UsuarioMapper;
import br.com.igrejaidef.Idef.dto.modelo.Usuario;
import br.com.igrejaidef.Idef.model.UsuarioModel;
import br.com.igrejaidef.Idef.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    private CadastroDeUsuario repository;

    @Autowired
    private UsuarioMapper mapper;

    @GetMapping
    public ResponseEntity<List<Usuario>> ListarTodosUsuarios() throws Exception {
        return ResponseEntity.ok(mapper.toListaDeModelos(repository.listar()));
    }
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(mapper.toModelo(repository.salvar(mapper.fromDto(usuario))));
    }
    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(mapper.toModelo(repository.salvar(mapper.fromDto(usuario))));
    }
    @DeleteMapping
    public ResponseEntity<Usuario> deletarUsuario(@RequestParam long id) throws Exception{
        return ResponseEntity.ok(mapper.toModelo(repository.remover(id)));
    }

//    @GetMapping("/valida")
//    public ResponseEntity<Boolean> validaSenha(@RequestParam String login,
//                                               @RequestParam String password){
//        boolean valid = repository.validaSenha(login,password);
//        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//        return ResponseEntity.status(status).body(valid);
//    }
}
