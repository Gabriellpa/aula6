using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

namespace Compiladoress.Estrutura
{
    public enum EnumTipo
    {        
        [Description("erro")]
        SERRO,
        [Description("programa")]
        SPROGRAMA,
        [Description("var")]
        SVAR,
        [Description("doispontos")]
        SDOISPONTOS,
        [Description("inicio")]
        SINICIO,
        [Description("fim")]
        SFIM,
        [Description(":=")]
        SATRIBUICAO,
        [Description(":")]
        STIPO,
        [Description("escreva")]
        SESCREVA,
        [Description("inteiro")]
        SINTEIRO,
        [Description(";")]
        SPONTO_E_VIRGULA,
        [Description(".")]
        SPONTO,
        [Description("+")]
        SMAIS,
        [Description("-")]
        SMENOS,
        [Description("*")]
        SMULTIPLICACAO,
        [Description("/")]
        SDIVIDIR,
        [Description("0")]
        SNUMERO,
        [Description("variavel")]
        SIDENTIFICADOR,
        [Description("(")]
        SABRE_PARENTESIS,
        [Description(")")]
        SFECHA_PARENTESIS
    }
}
