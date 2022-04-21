package br.com.igrejaidef.Idef.dto.modelo;

import br.com.igrejaidef.Idef.model.UsuarioModel;

import javax.persistence.*;

public class RoleModelo {

    private Integer id;
    private String roleName;
    private UsuarioModel usuarioModel;

    public RoleModelo() {
        this(null,null,null);
    }

    public RoleModelo(Integer id, String roleName, UsuarioModel usuarioModel) {
        this.id = id;
        this.roleName = roleName;
        this.usuarioModel = usuarioModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    @Override
    public String toString() {
        return "RoleModelo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", usuarioModel=" + usuarioModel +
                '}';
    }
}
