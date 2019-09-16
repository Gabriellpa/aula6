package dores.enums;

import java.util.Arrays;

public enum OperadorRelacional {
    MAIOR('>'),MENOR('<'),IGUAL('=');

    private Character simbolo;

    private OperadorRelacional(Character c){
        simbolo = c;
    }

    public static Boolean charToSimbol(Character c){
        return Arrays.stream(values()).anyMatch(s -> s.simbolo.equals(c));
    }
}
