using Compiladoress.Util;
using System;
using System.Collections.Generic;
using System.Text;

namespace Compiladoress.Estrutura
{
    public static class SimboloPontuacao
    {
        private enum Operador
        {
            PONTO_VIRGULA = ';',
            ASPAS = '"',
            A_PARENTESES = '(',
            F_PARENTESES = ')',
            PONTO = '.'
        }

        public static int simboloValido(char c)
        {
            return (EnumExtensions.ValueAsChar(Operador.PONTO_VIRGULA) == c) ? 8 :
                    (EnumExtensions.ValueAsChar(Operador.PONTO) == c)        ? 9 :
                    (EnumExtensions.ValueAsChar(Operador.A_PARENTESES) == c) ? 10 :
                    (EnumExtensions.ValueAsChar(Operador.F_PARENTESES) == c) ? 11 : -1;
                    //(EnumExtensions.ValueAsChar(Operador.ASPAS) == c)        ?  :
        }
    }
}
