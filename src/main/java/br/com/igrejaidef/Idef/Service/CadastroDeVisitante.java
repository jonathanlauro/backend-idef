package br.com.igrejaidef.Idef.Service;

import br.com.igrejaidef.Idef.model.VisitanteModel;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;

@Service
public interface CadastroDeVisitante {

    VisitanteModel adicionar(VisitanteModel visitante) throws MessagingException;

    VisitanteModel atualizar(VisitanteModel visitante);

    VisitanteModel remover(long id);

    List<VisitanteModel> listar();

    void enviarMsgDeAgradecimento(String data) throws ParseException;

    void enviarMsgDeAusencia(String data) throws ParseException;

    void enviarMsgDeLembrete(String msg) throws ParseException;
}
