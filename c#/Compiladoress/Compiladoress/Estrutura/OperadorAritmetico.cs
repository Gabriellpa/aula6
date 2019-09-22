using Compiladoress.Util;
using System;
using System.Collections.Generic;
using System.Text;

namespace Compiladoress.Estrutura
{
    public static class OperadorAritmetico
    {
        private enum Operador
        {
            SOMA = '+',
            SUB = '-',
            DIV = '/',
            MULT = '*'
        }

        public static int simboloValido(char c)
        {
            return (EnumExtensions.ValueAsChar(Operador.SOMA) == c) ? 4 :
                    (EnumExtensions.ValueAsChar(Operador.SUB) == c) ? 5 :
                    (EnumExtensions.ValueAsChar(Operador.MULT) == c) ? 6 :
                    (EnumExtensions.ValueAsChar(Operador.DIV) == c) ? 7:-1;
        }
    }
}
