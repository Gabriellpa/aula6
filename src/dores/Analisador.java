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
                if(fimAnalisar(token)){
                    return tokens;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tokens;
    }

    // TODO: LINHA E COLUNA
    private Token pegarToken(Character c) {
        String lexema = "";
        int colunaAtual = 0;
        int linhaAtual = 0;

        while (true) {
            switch (estado) {
                case 0:
                    colunaAtual = coluna;
                    linhaAtual = linha;
                    if (Character.isDigit(c)) {
                        lexema += c;
                        c = Ler();
                        estado = 12;
                    } else if (Character.isAlphabetic(c)) {
                        lexema += c;
                        c = Ler();
                        estado = 14;
                    } else if ( c.equals(':')) {
                        lexema += c;
                        c = Ler();
                        estado = 1;
                    } else if (OperadorRelacional.charToSimbol(c) || OperadorAritmetico.charToSimbol(c) ||
                                SimboloPontuacao.charToSimbol(c) && !c.equals('=')) {
                        variosIfsSemFim(c);
                        lexema += c;
                    } else {
                        return null;
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
                    return retornaToken(Tipo.SATRIBUICAO,lexema,linhaAtual,colunaAtual);
                case 3:
                    return retornaToken(Tipo.STIPO,lexema,linhaAtual,colunaAtual);
                case 4:
                    return retornaToken(Tipo.SMAIS,lexema,linhaAtual,colunaAtual);
                case 5:
                    return retornaToken(Tipo.SMENOS,lexema,linhaAtual,colunaAtual);
                case 6:
                    return retornaToken(Tipo.SMULTIPLICACAO,lexema,linhaAtual,colunaAtual);
                case 7:
                    return retornaToken(Tipo.SDIVIDIR,lexema,linhaAtual,colunaAtual);
                case 8:
                    return retornaToken(Tipo.SPONTO_E_VIRGULA,lexema,linhaAtual,colunaAtual);
                case 9:
                    return retornaToken(Tipo.SPONTO,lexema,linhaAtual,colunaAtual);
                case 10:
                    return retornaToken(Tipo.SABRE_PARENTESIS,lexema,linhaAtual,colunaAtual);
                case 11:
                    return retornaToken(Tipo.SFECHA_PARENTESIS,lexema,linhaAtual,colunaAtual);
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
                    return retornaToken(Tipo.SNUMERO,lexema,linhaAtual,colunaAtual);
                case 14:
                    if (Character.isDigit(c) || Character.isAlphabetic(c) || c.equals('_')) {
                        lexema += c;
                        c = Ler();
                    } else if (!Character.isDigit(c) || !Character.isAlphabetic(c) || !c.equals(')')) {
                        desler(c);
                        estado = 15;
                    }
                    break;
                case 15:
                    return retornaToken(Tipo.SIDENTIFICADOR,lexema,linhaAtual,colunaAtual);
                default:
                   return retornaToken(Tipo.SERRO,lexema,linhaAtual,colunaAtual);
            }
        }
    }

    private Character Ler() {
        try {
            coluna++;
            int c = r.read();
            return c != -1 ? (char) c : (char) -1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return (char) -1;
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

    private boolean fimAnalisar(Token token){
        if (token == null) {
            return true;
        } else if(Tipo.SERRO.equals(token.getTipo())){
            tokens.add(token);
            return true;
        } else {
            tokens.add(token);
            return false;
        }
    }

    private Token retornaToken(Tipo tipo, String lexema, int linha, int coluna ){
        estado = 0;
        return new Token(tipo,lexema,linha,coluna);
    }
}
