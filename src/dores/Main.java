package dores;

import dores.estruturas.Token;

import java.text.MessageFormat;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        final String path = "C:\\Users\\Gabriel\\Desktop\\estados\\src\\resources\\program-teste";
        // RITTER
        //final String path = "C:\\Users\\alu201518294\\Documents\\trabalhos\\estados-compilador\\src\\resources\\program-teste";
        Analisador analisador = new Analisador(path);
        List<Token> tokens = analisador.Analisar();

        System.out.printf(MessageFormat.format("Quantidade de Tokens: '{'{0}'} \n", tokens.size()));
        tokens.forEach(System.out::println);
    }
}
