using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Threading;
using System.Threading.Tasks;

namespace TestApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
        
            //ArrayTest.Hanoisolution(20);

            //EnumTest.enumTest();

            //ParallerTest.NumCalTest();

            //DateTest.DateFormatChangeVariation();
            Console.ReadLine();
        }
    }



    public class Test
    {
        public void test()
        {
            Console.WriteLine("\r\n--- generic method.");

            // Create a Type object representing class Example, and
            // get a MethodInfo representing the generic method.
            //
            Type ex = typeof(Example);
            MethodInfo mi = ex.GetMethod("Generic");

            DisplayGenericMethodInfo(mi);

            // Assign the int type to the type parameter of the Example
            // method.
            //
            MethodInfo miConstructed = mi.MakeGenericMethod(typeof(int));

            DisplayGenericMethodInfo(miConstructed);

            // Invoke the method.
            object[] args = { 42 };
            miConstructed.Invoke(null, args);

            // Invoke the method normally.
            Example.Generic<int>(42);

            // Get the generic type definition from the closed method,
            // and show it's the same as the original definition.
            //
            MethodInfo miDef = miConstructed.GetGenericMethodDefinition();
            Console.WriteLine("\r\nThe definition is the same: {0}",
                miDef == mi);
        }

        private static void DisplayGenericMethodInfo(MethodInfo mi)
        {
            Console.WriteLine("\r\n{0}", mi);

            Console.WriteLine("\tIs this a generic method definition? {0}",
                mi.IsGenericMethodDefinition);

            Console.WriteLine("\tIs it a generic method? {0}",
                mi.IsGenericMethod);

            Console.WriteLine("\tDoes it have unassigned generic parameters? {0}",
                mi.ContainsGenericParameters);

            // If this is a generic method, display its type arguments.
            //
            if (mi.IsGenericMethod)
            {
                Type[] typeArguments = mi.GetGenericArguments();

                Console.WriteLine("\tList type arguments ({0}):",
                    typeArguments.Length);

                foreach (Type tParam in typeArguments)
                {
                    // IsGenericParameter is true only for generic type
                    // parameters.
                    //
                    if (tParam.IsGenericParameter)
                    {
                        Console.WriteLine("\t\t{0}  parameter position {1}" +
                            "\n\t\t   declaring method: {2}",
                            tParam,
                            tParam.GenericParameterPosition,
                            tParam.DeclaringMethod);
                    }
                    else
                    {
                        Console.WriteLine("\t\t{0}", tParam);
                    }
                }
            }
        }
    }
    public class Example
    {
        public static void Generic<T>(T toDisplay)
        {
            Console.WriteLine("\r\nHere it is: {0}", toDisplay);
        }
    }

    public class opclass
    {
        public string val1;
        public string val2;
        public string val3;
        public string val4;
    }
}
