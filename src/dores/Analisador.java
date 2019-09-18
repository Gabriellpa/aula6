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

            Character data;
            while ((data = Ler()) != -1) {

                if (Character.isWhitespace((data))) {
                    proximaLinha(data);
                    continue;
                }

                Token token = pegarToken(data);

                if (Tipo.SERRO.equals(token.getTipo())) {
                    tokens.add(token);
                    return tokens;
                } else {
                    tokens.add(token);
                }
            }
            System.out.printf("{}  ",linha, coluna);
        } catch (Exception e) {
            System.out.println(e);
        }

        return tokens;
    }

    // TODO: LINHA E COLUNA
    private Token pegarToken(Character c) {
        Token tk = new Token();
        String lexema = "";
        //desler(c); // GAMBIS :9
        int colunaAtual = 0;
        int linhaAtual = 0;

        while (true) {
            //c = Ler();
            switch (estado) {
                case 0:
                    colunaAtual = coluna;
                    linhaAtual = linha;
                    if (Character.isDigit(c)) {
                        lexema += c;
                        estado = 12;
                    } else if (Character.isAlphabetic(c)) {
                        lexema += c;
                        estado = 14;
                    } else if ( c.equals(':')) {
                        lexema += c;
                        estado = 1;
                    } else if (OperadorRelacional.charToSimbol(c) || OperadorAritmetico.charToSimbol(c) || SimboloPontuacao.charToSimbol(c) && !c.equals('=')) {
                        variosIfsSemFim(c);
                        lexema += c;
                    } else {
                        return tk = null;
                    }
                    break;
                case 1:
                    if (c.equals('=')) {
                        lexema += c;
                        estado = 2;
                    } else {
                        estado = 3;
                    }
                    break;
                case 2:
                    tk.setTipo(Tipo.SATRIBUICAO);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 3:
                    tk.setTipo(Tipo.STIPO);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 4:
                    tk.setTipo(Tipo.SMAIS);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 5:
                    tk.setTipo(Tipo.SMENOS);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 6:
                    tk.setTipo(Tipo.SMULTIPLICACAO);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 7:
                    tk.setTipo(Tipo.SDIVIDIR);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 8:
                    tk.setTipo(Tipo.SPONTO_E_VIRGULA);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 9:
                    tk.setTipo(Tipo.SPONTO);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 10:
                    tk.setTipo(Tipo.SABRE_PARENTESIS);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 11:
                    tk.setTipo(Tipo.SFECHA_PARENTESIS);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 12:
                    if (Character.isDigit(c)) {
                        lexema += c;
                        c = Ler();
                    } else if (!Character.isDigit(c)) {
                        desler(c);
                        estado = 13;
                    }
                    break;
                case 13:
                    tk.setTipo(Tipo.SNUMERO);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                case 14:
                    if (Character.isDigit(c) || Character.isAlphabetic(c) || c.equals('_')) {
                        lexema += c;
                        c = Ler();
                    } else if (!Character.isDigit(c) || !Character.isAlphabetic(c) || !c.equals(')')) {
                        estado = 15;
                    }
                    break;
                case 15:
                    tk.setTipo(Tipo.SIDENTIFICADOR);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
                default:
                    tk.setTipo(Tipo.SERRO);
                    tk.setLexema(lexema);
                    tk.setColuna(colunaAtual);
                    tk.setLinha(linhaAtual);
                    estado = 0;
                    return tk;
            }
            //c = Ler();
        }
    }

    private Character Ler() {
        try {
            coluna++;
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

    // gostei do nome, mt criativo
    private void desler(Character ch) {
        try {
            coluna--;
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
