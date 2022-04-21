package br.com.igrejaidef.Idef.dto.modelo;

import br.com.igrejaidef.Idef.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.List;

public class Usuario {

    private Long id;
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private List<String> roles;

    public Usuario() {
        this(null,null,null,null,null);
    }

    public Usuario(Long id, String login, String password, String email, List<String> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
