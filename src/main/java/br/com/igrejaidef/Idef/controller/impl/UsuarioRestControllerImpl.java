package br.com.igrejaidef.Idef.controller.impl;

import br.com.igrejaidef.Idef.Service.CadastroDeUsuario;
import br.com.igrejaidef.Idef.controller.UsuarioRestControllerr;
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
public class UsuarioRestControllerImpl implements UsuarioRestControllerr {

    @Autowired
    private CadastroDeUsuario repository;

    @Autowired
    private UsuarioMapper mapper;

    @Override
    public ResponseEntity<List<Usuario>> ListarTodosUsuarios() throws Exception {
        return ResponseEntity.ok(mapper.toListaDeModelos(repository.listar()));
    }
    @Override
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(mapper.toModelo(repository.salvar(mapper.fromDto(usuario))));
    }
    @Override
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(mapper.toModelo(repository.salvar(mapper.fromDto(usuario))));
    }
    @Override
    public ResponseEntity<Usuario> deletarUsuario(@RequestParam long id) throws Exception{
        return ResponseEntity.ok(mapper.toModelo(repository.remover(id)));
    }

    @Override
    public ResponseEntity<String> buscaRole(String login) throws Exception {
        return ResponseEntity.ok(repository.buscaRole(login));
    }

//    @GetMapping("/valida")
//    public ResponseEntity<Boolean> validaSenha(@RequestParam String login,
//                                               @RequestParam String password){
//        boolean valid = repository.validaSenha(login,password);
//        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//        return ResponseEntity.status(status).body(valid);
//    }
}
