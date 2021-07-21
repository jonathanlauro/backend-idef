package br.com.igrejaidef.Idef.Service;

import br.com.igrejaidef.Idef.model.VisitanteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CadastroDeVisitante {

    VisitanteModel adicionar(VisitanteModel visitante);
    VisitanteModel atualizar(VisitanteModel visitante);
    VisitanteModel remover(long id);
    List<VisitanteModel> listar();
    void enviarMsgDeAgradecimento();
}
