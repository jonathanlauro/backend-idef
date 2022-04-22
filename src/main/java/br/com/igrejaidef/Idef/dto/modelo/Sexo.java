package br.com.igrejaidef.Idef.dto.modelo;

public enum Sexo {

    MASCULINO("M"),
    FEMININO("F"),
    OUTRO("O")
    ;

    private String valor;

    Sexo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
