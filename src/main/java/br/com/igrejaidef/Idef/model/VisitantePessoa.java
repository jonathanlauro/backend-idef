package br.com.igrejaidef.Idef.model;


import org.springframework.beans.factory.annotation.Value;

public class VisitantePessoa{

    @Value("@{target.nome}")
    private String nome;
    @Value("@{target.telefone}")
    private String telefone;

    public VisitantePessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
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
}
