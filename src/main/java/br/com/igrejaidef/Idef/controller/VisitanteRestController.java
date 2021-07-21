package br.com.igrejaidef.Idef.controller;

import br.com.igrejaidef.Idef.dto.modelo.Visitante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitante")
public interface VisitanteRestController {

    @GetMapping
    ResponseEntity<List<Visitante>> listarVisitantes() throws Exception;

    @PostMapping
    ResponseEntity<Visitante> adicionarVisitante(@RequestBody Visitante visitante) throws Exception;

    @PutMapping
    ResponseEntity<Visitante> atualizarVisitante(@RequestBody Visitante visitante) throws Exception;

    @DeleteMapping
    ResponseEntity<Visitante> deletarVisitante(long id) throws Exception;

    @GetMapping("/enviar")
    void enviarMsg();
}
