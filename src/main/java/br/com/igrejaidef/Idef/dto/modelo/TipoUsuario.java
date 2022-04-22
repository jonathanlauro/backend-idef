package br.com.igrejaidef.Idef.dto.modelo;

public enum TipoUsuario {

    MENTOR("M"),
    MENTOREADO("A")
    ;

    private String valor;

    TipoUsuario(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
