package br.com.igrejaidef.Idef.model;

public class Token {
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return '{' +
                "\"token\":\"" + token +'"'+
                '}';
    }
}
