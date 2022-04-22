package br.com.igrejaidef.Idef.dto.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class Usuario {

    private Long id;
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String nomeCompleto;
    private String telefone;
    @NonNull
    private StatusUsuario status;
    @NonNull
    private TipoUsuario tipo;
    @NonNull
    private Sexo sexo;
    @NonNull
    private RoleUsuario role;

    public static Builder novoUsuario(){
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String login;
        private String password;
        private String email;
        private String nomeCompleto;
        private String telefone;
        private StatusUsuario status;
        private TipoUsuario tipo;
        private Sexo sexo;
        private RoleUsuario role;

        private Builder(){
        }

        public Builder withId(Long id){
            this.id = id;
            return this;
        }
        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder withNomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }
        public Builder withTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }
        public Builder withStatus(StatusUsuario status) {
            this.status = status;
            return this;
        }
        public Builder withTipo(TipoUsuario tipo){
            this.tipo = tipo;
            return this;
        }
        public Builder withSexo(Sexo sexo){
            this.sexo = sexo;
            return this;
        }
        public Builder withRole(RoleUsuario role){
            this.role = role;
            return this;
        }

        public Usuario build(){
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setLogin(login);
            usuario.setPassword(password);
            usuario.setEmail(email);
            usuario.setNomeCompleto(nomeCompleto);
            usuario.setTelefone(telefone);
            usuario.setStatus(status);
            usuario.setTipo(tipo);
            usuario.setSexo(sexo);
            usuario.setRole(role);
            return usuario;
        }
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public RoleUsuario getRole() {
        return role;
    }

    public void setRole(RoleUsuario role) {
        this.role = role;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", telefone='" + telefone + '\'' +
                ", status=" + status +
                ", tipo=" + tipo +
                ", sexo=" + sexo +
                ", role='" + role + '\'' +
                '}';
    }
}
