package br.com.igrejaidef.Idef.Service;

import br.com.igrejaidef.Idef.model.UsuarioModel;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public interface CadastroDeUsuario {

    UsuarioModel salvar(UsuarioModel usuarioModel) throws MessagingException;

    UsuarioModel atualizar(UsuarioModel usuarioModel);

    List<UsuarioModel> listar();

    UsuarioModel buscarPorLogin(String login);

    UsuarioModel remover(long id);

    Boolean validaSenha(String login, String password);

    UsuarioModel buscaRole(String login);
}
