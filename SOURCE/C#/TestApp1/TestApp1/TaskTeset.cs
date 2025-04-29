using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TestApp1
{
    class TaskTeset
    {

        public void TaskTest()
        {
            var indexes = new int[] { 1, 2, 3 };

            RunExample((prefix) => Parallel.ForEach(indexes, (i) => DoSomethingAsync(i, prefix)),
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
    }
}
