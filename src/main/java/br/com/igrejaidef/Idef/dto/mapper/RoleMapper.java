package br.com.igrejaidef.Idef.dto.mapper;

import br.com.igrejaidef.Idef.dto.modelo.RoleModelo;
import br.com.igrejaidef.Idef.model.Role;
import br.com.igrejaidef.Idef.utils.UtilitarioDeConversao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

public class RoleMapper implements Mapper<Role, RoleModelo>{

    @Autowired
    UtilitarioDeConversao conversor;

    @Override
    public Role toEntidade(RoleModelo modelo) throws Exception {
        return null;
    }

    @Override
    public RoleModelo toModelo(Role entidade) throws Exception {
        return null;
    }

    @Override
    public List<Role> toListaDeEntidades(Iterable<RoleModelo> modelos) throws Exception {
        return null;
    }

    @Override
    public List<RoleModelo> toListaDeModelos(Iterable<Role> entidades) throws Exception {
        return null;
    }

    private Role fromModelo(RoleModelo modelo) {
        return new Role(
                modelo.getId(),
                modelo.getRoleName(),
                modelo.getUsuarioModel()
        );
    }
}
