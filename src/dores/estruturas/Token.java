package dores.estruturas;

import dores.enums.Tipo;

public class Token {

    private Tipo tipo;
    private String lexema;
    private int linha;
    private int coluna;

    public Token() {

    }

    public Token(Tipo tipo, String lexema, int linha, int coluna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }


    @Override
    public String toString() {
        return "Token{" +
                "tipo=" + tipo +
                ", lexema='" + lexema + '\'' +
                ", linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }
}
