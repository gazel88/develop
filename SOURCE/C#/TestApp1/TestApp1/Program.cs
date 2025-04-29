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
            Test tt = new Test();
     

            //tt.validTest("https://i.postimg.cc/rsdFPYPC/ffa33b71eaa16.png");

            //tt.GetPath();


            //ArrayTest.Hanoisolution(20);

            //EnumTest.enumTest();

            //ParallerTest.NumCalTest();

            //DateTest.DateFormatChangeVariation();
            Console.ReadLine();
        }
    }



    public class Test
    {
        void pathtest()
        {
            string a = "";
            string b = "/data/goods/jhl0111/small/icon/20241129110030060fd74f9f7-76a8-4bde-9d6b-e5d0998b7bda.gif";
        }


        
        public void validTest(string test)
        {
            string[] allowExt = "jpg,gif,jpeg,png,bmp".Split(',');
            bool is_ok = allowExt.Any(x => string.Equals(x, Path.GetExtension(test).ToLower().Substring(1)));
            Console.WriteLine(is_ok);
            //int pi = 0;
            //int.TryParse(test, out pi);
            //Console.WriteLine(pi);
        }
        public void GetPath()
        {
            string basepath = AppDomain.CurrentDomain.BaseDirectory;
            
            Console.WriteLine(basepath);
        }
        

        
        
        
        public void test()
        {
            Console.WriteLine("\r\n--- Examine a generic method.");

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
