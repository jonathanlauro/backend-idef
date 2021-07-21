package br.com.igrejaidef.Idef.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_visitantes")
public class VisitanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Date DataVisita;

    public VisitanteModel(){
        this(null,null,null,null,null);
    }
    public VisitanteModel(Long id, String nome, String telefone, String email, Date dataVisita) {
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

    public Date getDataVisita() {
        return DataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        DataVisita = dataVisita;
    }

    @Override
    public String toString() {
        return "VisitanteModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", DataVisita='" + DataVisita + '\'' +
                '}';
    }
}
