package dores;

import dores.estruturas.Token;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //final String path = "C:\\Users\\Gabriel\\gs-producing-web-service\\com.pila\\src\\resources\\program-teste";
        // RITTER
        final String path = "C:\\Users\\alu201518294\\Documents\\trabalhos\\estados-compilador\\src\\resources\\program-teste";
        Analisador analisador = new Analisador(path);
        List<Token> tokens = analisador.Analisar();
        tokens.forEach(System.out::println);
        //   tokens.forEach(c -> System.out.println(c.getLexema()));
    }
}
