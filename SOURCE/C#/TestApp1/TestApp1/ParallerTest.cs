using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TestApp1
{
    class ParallerTest
    {
        public static void NumCalTest()
        {
            List<int> a = MakeIntList(new List<int>());
            ParallelOptions p = new ParallelOptions { MaxDegreeOfParallelism = 3 };
            Parallel.ForEach(a, p, (num) =>
            {
                Console.ForegroundColor = (ConsoleColor)Thread.CurrentThread.ManagedThreadId;
                Console.WriteLine("num=" + num + ", taskid =" + Thread.CurrentThread.ManagedThreadId);
                Thread.Sleep(100);
            });
        }

        public static void parallelTest2()
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
        public static List<int> MakeIntList(List<int> lst)
        {
            for (int i = 0; i < 1000; i++)
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

        static void BasicAction()
        {
            Console.WriteLine("Method=alpha, Thread={0}", Thread.CurrentThread.ManagedThreadId);
        }
    }
}
