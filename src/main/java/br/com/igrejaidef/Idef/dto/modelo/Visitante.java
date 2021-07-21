package br.com.igrejaidef.Idef.dto.modelo;

import java.util.Date;

public class Visitante {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String DataVisita;

    public Visitante(){
        this(null,null,null,null,null);
    }
    public Visitante(Long id, String nome, String telefone, String email, String dataVisita) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        DataVisita = dataVisita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataVisita() {
        return DataVisita;
    }

    public void setDataVisita(String dataVisita) {
        DataVisita = dataVisita;
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", DataVisita=" + DataVisita +
                '}';
    }
}
