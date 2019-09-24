using Compiladoress.Estrutura;
using System;
using System.Collections.Generic;
using System.IO;

namespace Compiladoress
{
    class Program
    {
        static void Main(string[] args)
        {            
            string path = string.Concat(Directory.GetParent(Directory.GetCurrentDirectory()).Parent.Parent.Parent.FullName, "\\files\\program-teste");
            Analisador a = new Analisador(path);
            List<Token> tokens = a.Analisar();
            tokens.ForEach(x => Console.WriteLine(x.ToString));
            Console.WriteLine(path);
        }
    }
}
