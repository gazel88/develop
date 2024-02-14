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
            //tt.test();

            //tt.test2();
            //tt.test3();

            tt.numCalTest();
            Console.ReadLine();
        }
    }

    public class Example
    {
        public static void Generic<T>(T toDisplay)
        {
            Console.WriteLine("\r\nHere it is: {0}", toDisplay);
        }
    }

    public class Test
    {
        public void numCalTest()
        {
            List<int> a = makeintArr(new List<int>());
            ParallelOptions p = new ParallelOptions { MaxDegreeOfParallelism = 3 };
            Parallel.ForEach(a, p, (num) =>
            {
                Console.ForegroundColor = (ConsoleColor)Thread.CurrentThread.ManagedThreadId;
                Console.WriteLine("num=" + num + ", taskid =" + Thread.CurrentThread.ManagedThreadId);
                Thread.Sleep(100);
            });
        }

        public List<int> makeintArr(List<int> lst)
        {
            for(int i = 0; i < 1000; i++)
            {
                lst.Add(i);
            }
            return lst;

        }
        private static async Task<String> ReadCharacters(String fn)
        {
            String text;
            using (StreamReader sr = new StreamReader(fn))
            {
                text = await sr.ReadToEndAsync();
            }
            return text;
        }
        public void parallelTest2()
        {
            Task<String> task = ReadCharacters(@".\CallOfTheWild.txt");
            String text = task.Result;
            IEnumerable<int> test = new int[] { 1, 2, 3, 4, 5, 6, 6, 8 };

            int nVowels = 0;
            int nNonWhiteSpace = 0;
            Object obj = new Object();

            ParallelLoopResult result = Parallel.ForEach(text,
                                                         (ch) => {
                                                             Char uCh = Char.ToUpper(ch);
                                                             if ("AEIOUY".IndexOf(uCh) >= 0)
                                                             {
                                                                 lock (obj)
                                                                 {
                                                                     nVowels++;
                                                                 }
                                                             }
                                                             if (!Char.IsWhiteSpace(uCh))
                                                             {
                                                                 lock (obj)
                                                                 {
                                                                     nNonWhiteSpace++;
                                                                 }
                                                             }
                                                         });
            
            Console.WriteLine("Total characters:      {0,10:N0}", text.Length);
            Console.WriteLine("Total vowels:          {0,10:N0}", nVowels);
            Console.WriteLine("Total non-white-space:  {0,10:N0}", nNonWhiteSpace);
        }
        public void cancellationTokenTest()
        {
            // Define the cancellation token.
            CancellationTokenSource source = new CancellationTokenSource();
            CancellationToken token = source.Token;

            Random rnd = new Random();
            Object lockObj = new Object();

            List<Task<int[]>> tasks = new List<Task<int[]>>();
            TaskFactory factory = new TaskFactory(token);
            for (int taskCtr = 0; taskCtr <= 10; taskCtr++)
            {
                int iteration = taskCtr + 1;
                tasks.Add(factory.StartNew(() => {
                    int value;
                    int[] values = new int[10];
                    for (int ctr = 1; ctr <= 10; ctr++)
                    {
                        lock (lockObj)
                        {
                            value = rnd.Next(0, 101);
                        }
                        if (value == 0)
                        {
                            source.Cancel();
                            Console.WriteLine("Cancelling at task {0}", iteration);
                            break;
                        }
                        values[ctr - 1] = value;
                    }
                    return values;
                }, token));
            }
            try
            {
                Task<double> fTask = factory.ContinueWhenAll(tasks.ToArray(),
                (results) => {
                    Console.WriteLine("Calculating overall mean...");
                    long sum = 0;
                    int n = 0;
                    foreach (var t in results)
                    {
                        foreach (var r in t.Result)
                        {
                            sum += r;
                            n++;
                        }
                    }
                    return sum / (double)n;
                }, token);
                Console.WriteLine("The mean is {0}.", fTask.Result);
            }
            catch (AggregateException ae)
            {
                foreach (Exception e in ae.InnerExceptions)
                {
                    if (e is TaskCanceledException)
                        Console.WriteLine("Unable to compute mean: {0}",
                           ((TaskCanceledException)e).Message);
                    else
                        Console.WriteLine("Exception: " + e.GetType().Name);
                }
            }
            finally
            {
                source.Dispose();
            }
        }
        public void parallelForeachTest()
        {
            // The sum of these elements is 40.
            int[] input = { 4, 1, 6, 2, 9, 5, 10, 3 };
            int sum = 0;

            try
            {
                Parallel.ForEach(
                        input,                          // source collection
                        () => 0,                            // thread local initializer
                        (n, loopState, localSum) =>     // body
                    {
                            localSum += n;
                            Console.WriteLine("Thread={0}, n={1}, localSum={2}", Thread.CurrentThread.ManagedThreadId, n, localSum);
                            return localSum;
                        },
                        (localSum) => Interlocked.Add(ref sum, localSum)                    // thread local aggregator
                    );

                Console.WriteLine("\nSum={0}", sum);
            }
            // No exception is expected in this example, but if one is still thrown from a task,
            // it will be wrapped in AggregateException and propagated to the main thread.
            catch (AggregateException e)
            {
                Console.WriteLine("Parallel.ForEach has thrown an exception. THIS WAS NOT EXPECTED.\n{0}", e);
            }
        }
        static void BasicAction()
        {
            Console.WriteLine("Method=alpha, Thread={0}", Thread.CurrentThread.ManagedThreadId);
        }
        public void parallelInvokeTest()
        {
            try
            {
                Parallel.Invoke(
                    BasicAction,	// Param #0 - static method
                    () =>			// Param #1 - lambda expression
                    {
                        Console.WriteLine("Method=beta, Thread={0}", Thread.CurrentThread.ManagedThreadId);
                    },
                    delegate ()		// Param #2 - in-line delegate
                    {
                        Console.WriteLine("Method=gamma, Thread={0}", Thread.CurrentThread.ManagedThreadId);
                    }
                );
            }
            // No exception is expected in this example, but if one is still thrown from a task,
            // it will be wrapped in AggregateException and propagated to the main thread.
            catch (AggregateException e)
            {
                Console.WriteLine("An action has thrown an exception. THIS WAS UNEXPECTED.\n{0}", e.InnerException.ToString());
            }
        }

        static void RunExample(Action<string> action, string prefix)
        {
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine($"{Environment.NewLine}Starting '{prefix}'...");
            action(prefix);
            Console.WriteLine($"{Environment.NewLine}Finished '{prefix}'{Environment.NewLine}");
        }


        static async Task DoSomethingAsync(int i, string prefix)
        {
            await Task.Delay(i * 1000);
            Console.WriteLine($"Finished: {prefix}[{i}]");
        }

        public void TaskTest()
        {
            var indexes = new int[] { 1, 2, 3 };

            RunExample((prefix) => Parallel.ForEach(indexes, (i) =>  DoSomethingAsync(i, prefix)),
                "Parallel.Foreach");

            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.WriteLine("*You'll notice the tasks haven't run yet, because the main thread was not blocked*");
            Console.WriteLine("Press any key to start the next example...");
            Console.ReadKey();

            RunExample((prefix) => Task.WhenAll(indexes.Select(i => DoSomethingAsync(i, prefix)).ToArray()).Wait(),
                "Task.WhenAll");
            Console.WriteLine("All tasks are done.  Press any key to close...");
            Console.ReadKey();
        }
        public void test3()
        {
            Console.WriteLine("heelo");
        }
        public void test2()
        {
            try
            {
                string aa = null;
                aa.ToString();
                aa = "geel";

            }catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                Environment.Exit(0);
            }
            
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

}
