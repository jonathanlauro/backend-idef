package br.com.igrejaidef.Idef.dto.mapper;

import br.com.igrejaidef.Idef.dto.modelo.*;
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
        return Usuario.novoUsuario()
                .withId(model.getId())
                .withLogin(model.getLogin())
                .withPassword(model.getPassword())
                .withEmail(model.getEmail())
                .withNomeCompleto(model.getNomeCompleto())
                .withTelefone(model.getTelefone())
                .withStatus(stringToStatus(model.getStatus()))
                .withTipo(StringToTipo(model.getTipo()))
                .withSexo(StringToSexo(model.getSexo()))
                .withRole(StringToRoleUsuario(model.getRole()))
                .build();
    }
    public UsuarioModel fromDto(Usuario dto){
        return UsuarioModel.novoUsuarioModel()
                .withId(dto.getId())
                .withLogin(dto.getLogin())
                .withPassword(dto.getPassword())
                .withEmail(dto.getEmail())
                .withNomeCompleto(dto.getNomeCompleto())
                .withTelefone(dto.getTelefone())
                .withStatus(dto.getStatus().getValue())
                .withTipo(dto.getTipo().getValor())
                .withSexo(dto.getSexo().getValor())
                .withRole(dto.getRole().toString())
                .build();
    }

    private StatusUsuario stringToStatus(String status){
        if(status.equals("A")){
            return StatusUsuario.ATIVO;
        }else if(status.equals("I")) {
            return StatusUsuario.INATIVO;
        }else{
            return StatusUsuario.PENDENTE;
        }
    }

    private TipoUsuario StringToTipo(String tipo){
        return tipo.equals("M") ? TipoUsuario.MENTOR : TipoUsuario.MENTOREADO;
    }

    private Sexo StringToSexo(String sexo) {
        if(sexo.equals("M")){
            return Sexo.MASCULINO;
        }else if(sexo.equals("F")){
            return Sexo.FEMININO;
        }else {
            return Sexo.OUTRO;
        }
    }

    private RoleUsuario StringToRoleUsuario(String role) {
        return role.equals("ROLE_ADMIN") ? RoleUsuario.ROLE_ADMIN : RoleUsuario.ROLE_USER;
    }
}
