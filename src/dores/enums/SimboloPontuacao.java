package dores.enums;

import java.util.Arrays;

public enum SimboloPontuacao {
    PONTOEVIRGULA(';'),ASPAS('"'),ABREPARENTESES('('),FECHAPARENTESES(')'),PONTO('.');

    private Character simbolo;

    private SimboloPontuacao(Character c){
        simbolo = c;
    }

    public static Boolean charToSimbol(Character c){
        return Arrays.stream(values()).anyMatch(s -> s.simbolo.equals(c));
    }
}
