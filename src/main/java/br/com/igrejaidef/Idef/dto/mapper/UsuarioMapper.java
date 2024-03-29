package br.com.igrejaidef.Idef.dto.mapper;

import br.com.igrejaidef.Idef.dto.modelo.Usuario;
import br.com.igrejaidef.Idef.model.UsuarioModel;
import br.com.igrejaidef.Idef.utils.UtilitarioDeConversao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper implements Mapper<UsuarioModel, Usuario>{

    @Autowired
    private UtilitarioDeConversao conversor;

    @Override
    public UsuarioModel toEntidade(Usuario modelo) throws Exception {
        return conversor.converter(modelo,this::fromDto);
    }

    @Override
    public Usuario toModelo(UsuarioModel entidade) throws Exception {
        return conversor.converter(entidade,this::fromModel);
    }

    @Override
    public List<UsuarioModel> toListaDeEntidades(Iterable<Usuario> modelos) throws Exception {
        return conversor.converterColecao(modelos,this::fromDto);
    }

    @Override
    public List<Usuario> toListaDeModelos(Iterable<UsuarioModel> entidades) throws Exception {
        return conversor.converterColecao(entidades,this::fromModel);
    }

    public Usuario fromModel(UsuarioModel model){
        return new Usuario(
                model.getId(),
                model.getLogin(),
                model.getPassword(),
                model.getEmail(),
                model.getRole()
        );
    }
    public UsuarioModel fromDto(Usuario dto){
        return new UsuarioModel(
                dto.getId(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getRole()
        );
    }
}
