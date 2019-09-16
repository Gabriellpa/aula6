package dores;

import dores.enums.OperadorRelacional;
import dores.enums.OperadorAritmetico;
import dores.enums.SimboloPontuacao;
import dores.enums.Tipo;
import dores.estruturas.Token;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;

public class Analisador {

    private String path;

    private List<Token> tokens = new ArrayList<>();

    private PushbackReader r;

    private int coluna = 0;

    private int linha = 1;

    private int estado = 0;

    public Analisador(String path) {
        this.path = path;
    }

    public List<Token> Analisar() {
        try {
            r = new PushbackReader(new BufferedReader(new InputStreamReader(
                    new FileInputStream(path), "US-ASCII")));

            int intData;
            while ((intData = r.read()) != -1) {
                Character data = (char) intData;


                if (Character.isWhitespace((data))) {
                    proximaColuna(data);
                    continue;
                } else {
                    coluna++;
                }

                Token token = pegarToken(data);

                if (Tipo.SERRO.equals(token.getTipo())) {
                    tokens.add(token);
                    return tokens;
                } else {
                    tokens.add(token);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tokens;
    }

    // TODO: LINHA E COLUNA
    private Token pegarToken(Character c) {
        Token tk = new Token();
        String lexema = "";
        desler(c); // GAMBIS :9
        while (true) {
            c = Ler();
            switch (estado) {
                case 0:
                    if (Character.isDigit(c)) {
                        lexema += c;
                        estado = 12;
                    } else if (Character.isAlphabetic(c)) {
                        lexema += c;
                        estado = 14;
                    } else if ( c.equals(':')) {
                        lexema += c;
                        estado = 1;
                    } else if (OperadorRelacional.charToSimbol(c) || OperadorAritmetico.charToSimbol(c) || SimboloPontuacao.charToSimbol(c)) {
                        variosIfsSemFim(c);
                        lexema += c;
                    } else {
                        tk.setTipo(Tipo.SERRO);
                        return tk;
                    }
                    break;
                case 1:
                    if (c.equals('=')) {
                        lexema += c;
                        estado = 2;
                    } else {
                        desler(c);
                        estado = 3;
                    }
                    break;
                case 2:
                    desler(c);
                    tk.setTipo(Tipo.SATRIBUICAO);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 3:
                    desler(c);
                    tk.setTipo(Tipo.STIPO);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 4:
                    desler(c);
                    tk.setTipo(Tipo.SMAIS);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 5:
                    desler(c);
                    tk.setTipo(Tipo.SMENOS);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 6:
                    desler(c);
                    tk.setTipo(Tipo.SMULTIPLICACAO);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 7:
                    desler(c);
                    tk.setTipo(Tipo.SDIVIDIR);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 8:
                    desler(c);
                    tk.setTipo(Tipo.SPONTO_E_VIRGULA);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 9:
                    desler(c);
                    tk.setTipo(Tipo.SPONTO);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 10:
                    desler(c);
                    tk.setTipo(Tipo.SABRE_PARENTESIS);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 11:
                    desler(c);
                    tk.setTipo(Tipo.SFECHA_PARENTESIS);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 12:
                    if (Character.isDigit(c)) {
                        lexema += c;
                        estado = 12;
                    } else if (!Character.isDigit(c)) {
                        desler(c);
                        estado = 13;
                    }
                    break;
                case 13:
                    desler(c);
                    tk.setTipo(Tipo.SNUMERO);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                case 14:
                    if (Character.isDigit(c) || Character.isAlphabetic(c) || c.equals('_')) {
                        lexema += c;
                        estado = 14;
                    } else if (!Character.isDigit(c) || !Character.isAlphabetic(c) || !c.equals(')')) {
                        desler(c);
                        estado = 15;
                    }
                    break;
                case 15:
                    desler(c);
                    tk.setTipo(Tipo.SIDENTIFICADOR);
                    tk.setLexema(lexema);
                    estado = 0;
                    return tk;
                default:
                    desler(c);
                    tk.setTipo(Tipo.SERRO);
                    tk.setLexema(lexema);
                    estado = 0;
            }
        }

    }

    private Character Ler() {
        try {
            int c = r.read();
            return c != -1 ? (char) c : (char) -1; // TODO: RETIRAR
        } catch (Exception e) {
            System.out.println(e);
        }
        return (char) -1; // TODO: RETIRAR
    }

    private void proximaLinha(Character c) {
        if (c.equals('\n')) {
            linha++;
            coluna = 0;
        }
    }

    private void proximaColuna(Character c) {
        coluna++;
        proximaLinha(c);
    }

    // gostei do nome, mt criativo
    private void desler(Character ch) {
        try {
            r.unread((int) ch);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void variosIfsSemFim(Character c) {
        if (c.equals('+')) {
            estado = 4;
        } else if (c.equals('-')) {
            estado = 5;
        } else if (c.equals('*')) {
            estado = 6;
        } else if (c.equals('/')) {
            estado = 7;
        } else if (c.equals(';')) {
            estado = 8;
        } else if (c.equals('.')) {
            estado = 9;
        } else if (c.equals('(')) {
            estado = 10;
        } else if (c.equals(')')) {
            estado = 11;
        }
    }
}
