package br.com.igrejaidef.Idef.controller.impl;

import br.com.igrejaidef.Idef.Service.CadastroDeVisitante;
import br.com.igrejaidef.Idef.controller.VisitanteRestController;
import br.com.igrejaidef.Idef.dto.mapper.VisitanteMapper;
import br.com.igrejaidef.Idef.dto.modelo.Visitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitanteRestControllerImpl implements VisitanteRestController {

    @Autowired
    CadastroDeVisitante cadastro;
    @Autowired
    VisitanteMapper mapper;


    @Override
    public ResponseEntity<List<Visitante>> listarVisitantes() throws Exception {
        return ResponseEntity.ok(
                mapper.toListaDeModelos(cadastro.listar())
        );
    }

    @Override
    public ResponseEntity<Visitante> adicionarVisitante(@RequestBody Visitante visitante) throws Exception {
        return ResponseEntity.ok(
                mapper.toModelo(
                        cadastro.adicionar(
                                mapper.toEntidade(visitante)))
        );
    }

    @Override
    public ResponseEntity<Visitante> atualizarVisitante(@RequestBody Visitante visitante) throws Exception {
        return ResponseEntity.ok(
                mapper.toModelo(cadastro.atualizar(mapper.toEntidade(visitante)))
        );
    }

    @Override
    public ResponseEntity<Visitante> deletarVisitante(long id) throws Exception {
        return ResponseEntity.ok(
                mapper.toModelo(cadastro.remover(id))
        );
    }

    @Override
    public void enviarMsg() {
        cadastro.enviarMsgDeAgradecimento();
    }
}
