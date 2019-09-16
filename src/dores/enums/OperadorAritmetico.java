package dores.enums;

import java.util.Arrays;

public enum OperadorAritmetico {
    SOMA('+'),SUB('-'),DIV('/'),MULT('*');

    private Character simbolo;

    private OperadorAritmetico(Character c){
        simbolo = c;
    }

    public static Boolean charToSimbol(Character c){
        return Arrays.stream(values()).anyMatch(s -> s.simbolo.equals(c));
    }
}
