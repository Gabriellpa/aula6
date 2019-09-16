package dores.enums;

import java.util.Arrays;

public enum Tipo {
    SPROGRAMA("programa"),
    SVAR("var"),
    SDOISPONTOS("doispontos"),
    SINICIO("inicio"),
    SFIM("fim"),
    SATRIBUICAO(":="),
    STIPO(":"),
    SESCREVA("escreva"),
    SINTEIRO("inteiro"),
    SPONTO_E_VIRGULA(""),
    SPONTO("."),
    SMAIS("+"),
    SMENOS("-"),
    SMULTIPLICACAO("*"),
    SDIVIDIR("/"),
    SNUMERO("0"),
    SIDENTIFICADOR(""),
    SABRE_PARENTESIS("("),
    SFECHA_PARENTESIS(")"),
    SERRO("erro");

    private String descricao;

    private Tipo(String descricao){
        this.descricao = descricao;
    }

    public static Tipo PalavraReservada(String palavra){
        return  Arrays.stream(values()).filter(s -> s.descricao.equals(palavra)).findFirst().orElse(Tipo.SIDENTIFICADOR);
    }
}
