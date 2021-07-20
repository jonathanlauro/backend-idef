package br.com.igrejaidef.Idef.Service.impl;

import br.com.igrejaidef.Idef.Service.CadastroDeUsuario;
import br.com.igrejaidef.Idef.model.UsuarioModel;
import br.com.igrejaidef.Idef.repository.UsuarioRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroDeUsuarioImpl implements CadastroDeUsuario {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        validaUsuario(usuarioModel);
        usuarioModel.setPassword(encoder.encode(usuarioModel.getPassword()));
        return repository.save(usuarioModel);
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
        }if(atualizar.getLogin().equals("admin")){
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
        return repository.findAll();
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
        if(busca.getLogin().equals("admin")){
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
