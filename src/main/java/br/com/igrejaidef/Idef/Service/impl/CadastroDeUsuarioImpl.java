package br.com.igrejaidef.Idef.Service.impl;

import br.com.igrejaidef.Idef.Service.CadastroDeUsuario;
import br.com.igrejaidef.Idef.model.Role;
import br.com.igrejaidef.Idef.model.UsuarioModel;
import br.com.igrejaidef.Idef.repository.RoleRepository;
import br.com.igrejaidef.Idef.repository.UsuarioRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroDeUsuarioImpl implements CadastroDeUsuario {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    SendEmailService serviceEmail;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UsuarioModel salvar(UsuarioModel usuarioModel) throws MessagingException {
        validaUsuario(usuarioModel);
        usuarioModel.setPassword(encoder.encode(usuarioModel.getPassword()));
//        serviceEmail.sendEmail(usuarioModel.getEmail(), "<head>\n" +
//                        "    <meta charset=\"UTF-8\">\n" +
//                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                        "    <title>Bem-vindo</title>\n" +
//                        "    <style>\n" +
//                        "        *{\n" +
//                        "            margin: 0;\n" +
//                        "            padding: 0;\n" +
//                        "        }\n" +
//                        "        .banner{\n" +
//                        "            width: 700px;\n" +
//                        "            height: auto;\n" +
//                        "        }\n" +
//                        "        main {\n" +
//                        "            display: block;\n" +
//                        "            width: 700px;\n" +
//                        "            height: auto;\n" +
//                        "            margin: auto;\n" +
//                        "        }\n" +
//                        "        article {\n" +
//                        "            background-color: rgb(31, 31, 31);\n" +
//                        "            color: white;\n" +
//                        "            height: 350px;\n" +
//                        "            padding: 2rem;\n" +
//                        "        }\n" +
//                        "        .title {\n" +
//                        "            font-size: 60px;\n" +
//                        "            margin-bottom: 1rem;\n" +
//                        "        }\n" +
//                        "        .content {\n" +
//                        "            font-size: 25px;\n" +
//                        "            line-height: 2.5rem;\n" +
//                        "        }\n" +
//                        "    </style>\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//                        "    <main>\n" +
//                        "        <img class=\"banner\" src=\"https://ohlaladani.com.br/wp-content/uploads/wallpaper-OHLALADANI_DESKTOP_WALLPAPERS_AVENTURA-2.jpg\" \n" +
//                        "        alt=\"Homem pulando de um penhasco para o outro, segurnado uma bandeira \">\n" +
//                        "        <article>\n" +
//                        "            <h1 class=\"title\">Seja bem vindo a nossa igreja.</h1>\n" +
//                        "            <p class=\"content\">\n" +
//                        "                Somos uma igreja que tem como principio o seguinte Texto. \"Uma família de famílias\". Ficamos muito gratos em ter você como nosso membro.\n" +
//                        "                nos vemos no próximo culto. até logo.\n" +
//                        "\n" +
//                        "                Atenciosamento: Jonathan Lauro.\n" +
//                        "            </p>\n" +
//                        "        </article>\n" +
//                        "    </main>\n" +
//                        "</body>"
//                ,"Bem-vindo " + usuarioModel.getUsername());

        UsuarioModel retorno = repository.save(usuarioModel);
        usuarioModel.getRoles().forEach(item -> {
            roleRepository.save(new Role(null,item.getAuthority(),retorno));
        });

        return retorno;
    }

    @Override
    public UsuarioModel atualizar(UsuarioModel usuarioModel) {
        if(usuarioModel.getId()==null){
            throw new NullPointerException("Id não pode ser nulo.");
        }
        validaUsuario(usuarioModel);
        UsuarioModel atualizar = repository.findById((long)usuarioModel.getId());
        if(atualizar == null){
            throw new NullPointerException("Usuário não encontrado");
        }
        if(atualizar.getLogin().equals("admin")){
            throw new ServiceException("Admin não pode ser atualizado");
        }
        atualizar.setId(usuarioModel.getId());
        atualizar.setLogin(usuarioModel.getLogin());
        atualizar.setPassword(encoder.encode(usuarioModel.getPassword()));
        atualizar.setEmail(usuarioModel.getEmail());
        return repository.save(atualizar);
    }

    @Override
    public List<UsuarioModel> listar() {
        return repository.listarTodosDescId();
    }

    @Override
    public UsuarioModel buscarPorLogin(String login) {
        if(login == null || login.isEmpty()){
            throw new ServiceException("Campo não pode ficar vazio");
        }
        return repository.findByLogin(login);
    }

    @Override
    public UsuarioModel remover(long id) {
        UsuarioModel busca = repository.findById(id);
        if(busca == null){
            throw new NullPointerException("Usuario não encontrado");
        }
        List<UsuarioModel> buscarTudo = repository.findAll();
        if(buscarTudo.size() < 2){
            throw new ServiceException("Nao pode zerar o banco de usuários");
        }
        if(busca.getLogin().equals("admin") || busca.getLogin().equals("jonathan")){
            throw new ServiceException("Admin não pode ser deletado");
        }
        repository.deleteById(id);
        return busca;
    }

    @Override
    public Boolean validaSenha(String login, String password) {
        UsuarioModel usuario = repository.findByLogin(login);
        if(usuario == null){
            return false;
        }
        boolean valid;
        valid = encoder.matches(password, usuario.getPassword());
        return valid;
    }

    @Override
    public UsuarioModel buscaRole(String login) {
        UsuarioModel usuario = repository.findByLogin(login);
        return usuario;
    }

    private void validaUsuario(UsuarioModel usuario) throws ServiceException{
        List<String> erros = new ArrayList<>();
        if(usuario.getLogin()==null || usuario.getLogin().isEmpty()){
            erros.add("Erro de usuário");
        }
        if(usuario.getEmail()==null || usuario.getEmail().isEmpty()){
            erros.add("erro de email");
        }
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            erros.add("erro de senha");
        }
        UsuarioModel buscar = repository.findByLogin(usuario.getLogin());
        if(buscar != null && buscar.getId() != usuario.getId()){
            erros.add("usuario ja cadastrado");
        }
        if(erros.size() > 0){
            throw new ServiceException("Verifique todos os campos!");
        }
    }
}
