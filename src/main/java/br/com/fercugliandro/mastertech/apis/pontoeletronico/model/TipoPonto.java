package br.com.fercugliandro.mastertech.apis.pontoeletronico.model;

public enum TipoPonto {
    ENTRADA("E"),
    SAIDA("S");

    private String tipo;

    TipoPonto(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}