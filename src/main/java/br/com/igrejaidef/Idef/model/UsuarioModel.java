package br.com.igrejaidef.Idef.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String email;
    private String nomeCompleto;
    private String telefone;
    private String status;
    private String tipo;
    private String sexo;
    private String role;

    public static Builder novoUsuarioModel() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String login;
        private String password;
        private String email;
        private String nomeCompleto;
        private String telefone;
        private String status;
        private String tipo;
        private String sexo;
        private String role;
        
        private Builder(){
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        public Builder withLogin(String login){
            this.login = login;
            return this;
        }
        public Builder withPassword(String password){
            this.password = password;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }
        public Builder withNomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }
        public Builder withTelefone(String telefone){
            this.telefone = telefone;
            return this;
        }
        public Builder withStatus(String status){
            this.status = status;
            return this;
        }
        public Builder withTipo(String tipo){
            this.tipo = tipo;
            return this;
        }
        public Builder withSexo(String sexo){
            this.sexo = sexo;
            return this;
        }
        public Builder withRole(String role ){
            this.role = role;
            return this;
        }
        public UsuarioModel build(){
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(id);
            usuarioModel.setLogin(login);
            usuarioModel.setPassword(password);
            usuarioModel.setEmail(email);
            usuarioModel.setNomeCompleto(nomeCompleto);
            usuarioModel.setTelefone(telefone);
            usuarioModel.setStatus(status);
            usuarioModel.setTipo(tipo);
            usuarioModel.setSexo(sexo);
            usuarioModel.setRole(role);
            return usuarioModel;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioModel)) return false;
        UsuarioModel that = (UsuarioModel) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", telefone='" + telefone + '\'' +
                ", status='" + status + '\'' +
                ", tipo='" + tipo + '\'' +
                ", sexo='" + sexo + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
