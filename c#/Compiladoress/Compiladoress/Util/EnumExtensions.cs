using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection;
using System.Text;

namespace Compiladoress.Util
{
    public static class EnumExtensions
    {

        public static string Description(this IConvertible value)
        {
            FieldInfo fi = value.GetType().GetField(value.ToString());

            try
            {
                DescriptionAttribute[] attributes =
                (DescriptionAttribute[])fi.GetCustomAttributes(
                typeof(DescriptionAttribute),
                false);

                if (attributes != null &&
                    attributes.Length > 0)
                    return attributes[0].Description;
                else
                    return value.ToString();
            }
            catch
            {
                return value.ToString();
            }
        }

        public static string Name(this Enum value)
        {
            return value.ToString();
        }

        public static int ValueAsInt(this Enum value)
        {
            return Convert.ToInt32(value);
        }

        public static char ValueAsChar(this Enum value)
        {
            return Convert.ToChar(value);
        }

        public static T FromChar<T>(string caracter) where T : struct, IConvertible
        {
            return EnumExtensions.FromChar<T>(caracter[0]);
        }

        public static T FromChar<T>(char caracter) where T : struct, IConvertible
        {
            try
            {
                if (Enum.IsDefined(typeof(T), Convert.ToInt32(caracter)))
                {
                    return (T)Enum.ToObject(typeof(T), Convert.ToInt32(caracter));
                }
                else
                {
                    return (T)(Enum.GetValues(typeof(T)).GetValue(0));
                }
            }
            catch
            {
                return (T)(Enum.GetValues(typeof(T)).GetValue(0));
            }


        }

        public static T FromInt<T>(int numero) where T : struct, IConvertible
        {
            try
            {
                if (Enum.IsDefined(typeof(T), numero))
                {
                    return (T)Enum.ToObject(typeof(T), numero);
                }
                else
                {
                    return (T)(Enum.GetValues(typeof(T)).GetValue(0));
                }
            }
            catch
            {
                return (T)(Enum.GetValues(typeof(T)).GetValue(0));
            }
        }

        public static T FromString<T>(string nome) where T : struct, IConvertible
        {
            try
            {
                return (T)Enum.Parse(typeof(T), nome.Replace(" ", ""), true);
            }
            catch
            {
                return (T)(Enum.GetValues(typeof(T)).GetValue(0));
            }

        }

        public static T FromDescription<T>(string nome) where T : struct, IConvertible
        {
            try
            {
                return EnumExtensions.GetItens<T>().FirstOrDefault(x => x.Description() == nome);
            }
            catch
            {
                return (T)(Enum.GetValues(typeof(T)).GetValue(0));
            }

        }

        public static List<T> GetItens<T>() where T : struct, IConvertible
        {
            return Enum.GetNames(typeof(T)).ToArray().Select(e => EnumExtensions.FromString<T>(e)).ToList();

        }

        public static string ValueAsString(this Enum value)
        {
            return value.ValueAsChar().ToString();
        }

    }
}
