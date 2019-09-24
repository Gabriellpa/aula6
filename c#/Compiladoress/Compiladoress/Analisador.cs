using Compiladoress.Estrutura;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Compiladoress
{
    public class Analisador
    {
        private readonly string _path;
        private List<Token> tokens = new List<Token>();
        private int linha = 1;
        private int coluna = 0;
        private int estado = 0;
        private StreamReader r;
        public Analisador(string path)
        {
            _path = path;
        }
        ~Analisador()
        {
            r.Dispose();
            r.Close();
        }

        public List<Token> Analisar()
        {
            try
            {
                r = new StreamReader(this._path, encoding: Encoding.ASCII);
                char c;
                int cI;
                while ((cI = r.Peek()) != -1)
                {
                    c = (char)cI;

                    if (Char.IsWhiteSpace(c))
                    {
                        proximaLinha(c);
                        continue;
                    }
                    c = (char)r.Read(); coluna++;
                    Token t = pegarToken(c);
                    if (t == null) return tokens;
                    tokens.Add(t);
                    if(t.Tipo == EnumTipo.SERRO) return tokens;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            return tokens;
        }

        private Token pegarToken(char c)
        {
            string lexema = "";
            int colunaAtual = 0;
            int linhaAtual = 0;
            while (true)
            {
                switch (this.estado)
                {
                    case 0:
                        colunaAtual = coluna;
                        linhaAtual = linha;
                        if (Char.IsDigit(c))
                        {
                            lexema += c;
                            estado = 12;
                        }
                        else if (Char.IsLetter(c))
                        {
                            lexema += c;
                            estado = 14;
                        }
                        else if (':'.Equals(c))
                        {
                            lexema += c;
                            estado = 1;
                        }
                        else if ((estado = OperadorAritmetico.simboloValido(c)) != -1)
                        {
                            lexema += c;
                        }
                        else if ((estado = SimboloPontuacao.simboloValido(c)) != -1)
                        {
                            lexema += c;
                        }
                        else
                        {
                            return null;
                        }
                        break;
                    case 1:
                        if ('='.Equals(c))
                        {
                            c = (char)r.Read(); coluna++;
                            lexema += c;
                            estado = 2;
                        }
                        else
                        {
                            estado = 3;
                        }
                        break;
                    case 2: return ReturnToken(EnumTipo.SATRIBUICAO, linhaAtual, colunaAtual, lexema);
                    case 3: return ReturnToken(EnumTipo.STIPO, linhaAtual, colunaAtual, lexema);
                    case 4: return ReturnToken(EnumTipo.SMAIS, linhaAtual, colunaAtual, lexema);
                    case 5: return ReturnToken(EnumTipo.SMENOS, linhaAtual, colunaAtual, lexema);
                    case 6: return ReturnToken(EnumTipo.SMULTIPLICACAO, linhaAtual, colunaAtual, lexema);
                    case 7: return ReturnToken(EnumTipo.SDIVIDIR, linhaAtual, colunaAtual, lexema);
                    case 8: return ReturnToken(EnumTipo.SPONTO_E_VIRGULA, linhaAtual, colunaAtual, lexema);
                    case 9: return ReturnToken(EnumTipo.SPONTO, linhaAtual, colunaAtual, lexema);
                    case 10: return ReturnToken(EnumTipo.SABRE_PARENTESIS, linhaAtual, colunaAtual, lexema);
                    case 11: return ReturnToken(EnumTipo.SFECHA_PARENTESIS, linhaAtual, colunaAtual, lexema);
                    case 12:
                        if (Char.IsDigit(c))
                        {
                            lexema += c;
                            c = (char)r.Read(); coluna++;
                        }
                        else
                        {
                            estado = 13;
                        }
                        break;
                    case 13:
                        return ReturnToken(EnumTipo.SNUMERO, linhaAtual, colunaAtual, lexema);
                    case 14:
                        if (Char.IsLetterOrDigit(c) || '_'.Equals(c))
                        {
                            lexema += c;
                            c = (char)r.Read(); coluna++;
                        }
                        else if(!Char.IsLetterOrDigit(c) || !')'.Equals(c)) {
                            estado = 15;
                        }
                        break;
                    case 15: return ReturnToken(EnumTipo.SIDENTIFICADOR, linhaAtual, colunaAtual, lexema);
                    default: return ReturnToken(EnumTipo.SERRO, linhaAtual, colunaAtual, lexema);

                }
                c = (char)r.Peek();
            }
        }
        private void proximaLinha(Char c)
        {
            
            if (c.Equals('\n'))
            {
                linha++;
                coluna = 0;
                c = (char)r.Read();
            }
            else
            {
                c = (char)r.Read(); coluna ++;
            }
            
        }
        private Token ReturnToken(EnumTipo tipo, int linha, int coluna, string lexema)
        {
            estado = 0;
            return new Token
            {
                Tipo = tipo,
                Coluna = coluna,
                Linha = linha,
                Lexema = lexema
            };
        }
    }
}
