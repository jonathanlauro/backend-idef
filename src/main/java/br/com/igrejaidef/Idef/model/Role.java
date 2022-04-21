package br.com.igrejaidef.Idef.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority,Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;

    @Column(name = "no_role")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario",  insertable = false, updatable = false)
    private UsuarioModel usuarioModel;

    public Role() {
        this(null,null,null);
    }

    public Role(Integer id, String roleName, UsuarioModel usuarioModel) {
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

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
