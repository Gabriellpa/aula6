using System;
using System.Collections.Generic;
using System.Text;

namespace Compiladoress.Estrutura
{
    public class Token
    {
        public EnumTipo Tipo { get; set; }
        public string Lexema { get; set; }
        public int Linha { get; set; }
        public int Coluna { get; set; }

        public string ToString => string.Format("Token : tipo = {0},       lexama = {1},         linha = {2},                coluna = {3}", this.Tipo.ToString(), this.Lexema, this.Linha.ToString(), this.Coluna.ToString());        
    }
}
