package br.com.igrejaidef.Idef.dto.modelo;

public enum StatusUsuario {
    ATIVO("A"),
    INATIVO("I"),
    PENDENTE("P");

    private String value;

    StatusUsuario(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
