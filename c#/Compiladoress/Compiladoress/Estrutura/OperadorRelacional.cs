using Compiladoress.Util;
using System;
using System.Collections.Generic;
using System.Text;

namespace Compiladoress.Estrutura
{
    public static class OperadorRelacional
    {
        private enum Operador
        {
            MAIOR = '>',
            MENOR = '<'
        }

        public static int simboloValido(char c)
        {
            return (EnumExtensions.ValueAsChar(Operador.MAIOR) == c) ? -1 : 
                    (EnumExtensions.ValueAsChar(Operador.MENOR) == c) ? -1 : -1;
        }
    }
}
