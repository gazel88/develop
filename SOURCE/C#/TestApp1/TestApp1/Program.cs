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
            tt.arrTest();

            //tt.validTest("https://i.postimg.cc/rsdFPYPC/ffa33b71eaa16.png");

            //tt.GetPath();

            //string test = "파랑,노랑,빨강/90,100,105/상의,하의,모자/꽃무늬,민무늬,줄무늬";
            //var lst = tt.GenerateStockValueList(test);
            //var lst = tt.GetOptionTest(test);
            //foreach (var item in lst)
            //{
            //    Console.WriteLine($"item1={item.val1},item2={item.val2},item3={item.val3},item4={item.val4}");

            //}
            //tt.arr_test();

            //tt.Hanoisolution(3);
            //tt.solution(3, new string[]{ "hello", "one", "even", "never", "now", "world", "draw" });
            //tt.test();

            //tt.test2();
            //tt.test3();

            //tt.numCalTest();

            //tt.datetostamp();
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
        public void arrTest()
        {
            // Creates and initializes a new Array.
            Array myIntArray = Array.CreateInstance(typeof(int), 5);

            myIntArray.SetValue(8, 0);
            myIntArray.SetValue(2, 1);
            myIntArray.SetValue(6, 2);
            myIntArray.SetValue(3, 3);
            myIntArray.SetValue(7, 4);

            // Do the required sort first
            Array.Sort(myIntArray);

            // Displays the values of the Array.
            Console.WriteLine("The int array contains the following:");
            PrintValues(myIntArray);

            // Locates a specific object that does not exist in the Array.
            object myObjectOdd = 1;
            FindMyObject(myIntArray, myObjectOdd);

            // Locates an object that exists in the Array.
            object myObjectEven = 6;
            FindMyObject(myIntArray, myObjectEven);
        }

        public static void FindMyObject(Array myArr, object myObject)
        {
            int myIndex = Array.BinarySearch(myArr, myObject);
            if (myIndex < 0)
            {
                Console.WriteLine("The object to search for ({0}) is not found. The next larger object is at index {1}.", myObject, ~myIndex);
            }
            else
            {
                Console.WriteLine("The object to search for ({0}) is at index {1}.", myObject, myIndex);
            }
        }

        public static void PrintValues(Array myArr)
        {
            int i = 0;
            int cols = myArr.GetLength(myArr.Rank - 1);
            foreach (object o in myArr)
            {
                if (i < cols)
                {
                    i++;
                }
                else
                {
                    Console.WriteLine();
                    i = 1;
                }
                Console.Write("\t{0}", o);
            }
            Console.WriteLine();
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
        public List<opclass> GetOptionTest(string op)
        {
            List<opclass> ar_lst = new List<opclass>();
            string[] groups = op.Split('/');

            void GetArrList(string[] group, int depth, string[] cur_val)
            {
                if(group.Length == depth)
                {
                    opclass opclass = new opclass();
                    opclass.val1 = cur_val[0];
                    opclass.val2 = cur_val.Length > 1 ? cur_val[1] : "";
                    opclass.val3 = cur_val.Length > 2 ? cur_val[2] : "";
                    opclass.val4 = cur_val.Length > 3 ? cur_val[3] : "";

                    ar_lst.Add(opclass);
                    return;
                }
                    string[] options = group[depth].Split(',');
                    foreach (var item in options)
                    {
                        string[] next_val = new string[cur_val.Length + 1];
                        Array.Copy(cur_val, next_val, cur_val.Length);
                        //for (int i = 0; i < cur_val.Length; i++)
                        //{
                        //    next_val[i] = cur_val.ElementAt(i);
                        //}
                        next_val[cur_val.Length] = item;
                        GetArrList(group, depth + 1, next_val);
                    }

                

            }
            GetArrList(groups, 0, new string[0]);

            return ar_lst;

        }


        public List<dynamic> GenerateStockValueList(string optionValues)
        {
            var stockValueList = new List<dynamic>();

            // Split the input string into groups using '/'
            var optionGroups = optionValues.Split('/');

            // Use a helper method for recursive combination generation
            void GenerateCombinations(string[] groups, int depth, string[] currentValues)
            {
                if (depth == groups.Length)
                {
                    // Base case: Add the current combination to the list
                    stockValueList.Add(new
                    {
                        val1 = currentValues.Length > 0 ? currentValues[0] : "",
                        val2 = currentValues.Length > 1 ? currentValues[1] : "",
                        val3 = currentValues.Length > 2 ? currentValues[2] : "",
                        val4 = currentValues.Length > 3 ? currentValues[3] : ""
                    });
                    return;
                }

                // Get the current group and split it into individual options
                var options = groups[depth].Split(',');
                foreach (var option in options)
                {
                    // Recurse with the current option added to the combination
                    var nextValues = new string[currentValues.Length + 1];
                    Array.Copy(currentValues, nextValues, currentValues.Length);
                    nextValues[currentValues.Length] = option;
                    GenerateCombinations(groups, depth + 1, nextValues);
                }
            }

            // Start generating combinations
            GenerateCombinations(optionGroups, 0, Array.Empty<string>());

            return stockValueList;
        }

        public List<dynamic> GetStockValues(string[] groups, int depth, string[] curVal)
        {
            var stockValueList = new List<dynamic>();
            if (depth == groups.Length)
            {
                stockValueList.Add(new
                {
                    val1 = curVal.Length > 0 ? curVal[0] : "",
                    val2 = curVal.Length > 1 ? curVal[1] : "",
                    val3 = curVal.Length > 2 ? curVal[2] : "",
                    val4 = curVal.Length > 3 ? curVal[3] : ""
                });
                return stockValueList;
            }
            var options = groups[depth].Split(',');
            foreach (var option in options)
            {
                // Recurse with the current option added to the combination
                var nextValues = new string[curVal.Length + 1];
                Array.Copy(curVal, nextValues, curVal.Length);
                nextValues[curVal.Length] = option;
                stockValueList.Add(GetStockValues(groups, depth + 1, nextValues));
            }
            return stockValueList;
        }

        public void arr_test()
        {
            string ori = "test";
            string[] ori_arr = ori.Split(',');
            int r = ori_arr.Length;
            foreach (var item in ori_arr)
            {
            Console.WriteLine(Guid.NewGuid());
            Console.WriteLine(Guid.NewGuid());

            }
        }
        public int[,] Hanoisolution(int n)
        {
            List<int[]> path = new List<int[]>();
            HanoiSearch(3, path, 1, 3, 2);

            int rowCount = path.Count;
            int colCount = path[0].Length;
            int[,] answer = new int[rowCount, colCount];
            for (int i = 0; i < rowCount; i++)
            {
                for (int j = 0; j < colCount; j++)
                {
                    answer[i, j] = path[i][j];
                }
            }
            return answer;
        }

        public void HanoiSearch(int n, List<int[]>path, int start, int end, int via)
        {
            if (n == 1)
            {
                path.Add(new int[] { start, end });
                Console.WriteLine("n=" + n + ",start:" + start + ",end=" + end);
            }
            else
            {
                HanoiSearch(n - 1, path, start, via, end);
                Console.WriteLine("n=" + n + ",start:" + start + ",end=" + end);
                path.Add(new int[] { start, end });
                HanoiSearch(n - 1, path, via, end, start);
            }
        }

        public int Andsolution(int hp)
        {
            int answer = 0;
            int general = 5;
            int generalCnt = 0;
            int soldier = 3;
            int soldierCnt = 0;
            int worker = 1;
            int workerCnt = 0;

            generalCnt = hp / general;
            soldierCnt = (hp % general) / soldier;
            workerCnt = (hp % general) % soldier;

            answer = generalCnt + soldierCnt + workerCnt;

            return answer;
        }


        public int[] WordSolution(int n, string[] words)
        {
            int[] answer = { };
            int person = 0;
            int cnt = 0;
            List<string> passed = new List<string>();

            // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다. 
            for(int i =0;i<words.Length; i++)
            {
                if (passed.Where(x => x == words[i]).Any() || (i > 0 &&  !words[i].StartsWith(passed[i - 1].Reverse().ElementAt(0)))){
                    person = (i % n) + 1;
                    cnt = (i / n) + 1;
                    break;
                }
                passed.Add(words[i]);
            }

            answer = new int[] { person, cnt };
            System.Console.WriteLine("Hello C#");

            return answer;
        }
        public int tournamentSolution(int n, int a, int b)
        {
            int answer = 0;

            for(int i=0;i<Math.Log(n, 2); i++)
            {
                answer++;
                a = (a + 1) / 2;
                b = (b + 1) / 2;
                if (a ==  b){
                    break;
                }
            }

            // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
            System.Console.WriteLine("Hello C#");

            return answer;
        }

        public int boXsolution(int[] cards)
        {
            List<int> groupSize = new List<int>();
            List<int> openedBoxIdx = new List<int>();
            int answer = 0;
            for(int i = 0; i< cards.Length; i++)
            {
                // 처음부터 열린 상자인지 확인
                if(openedBoxIdx.Where(x=>x == i).Count() < 1)
                {
                    openedBoxIdx.Add(i);
                    int newboxidx = cards[i] - 1;
                    int cnt = 1;
                    while(openedBoxIdx.Where(x => x == newboxidx).Count() < 1)
                    {
                        openedBoxIdx.Add(newboxidx);
                        newboxidx = cards[newboxidx] - 1;
                        cnt++;
                    }
                    groupSize.Add(cnt);
                }

            }
            if(groupSize.Count > 1)
            {
                groupSize.Sort((a, b) => b.CompareTo(a));
                answer = groupSize[0] * groupSize[1];
            }
            else
            {
                answer = 0;
            }

            return answer;
        }
        

        public long pointSolution(int k, int d)
        {
            long answer = 0;
            string tmp = "";

            for (long i = 0; i <= d; i+=k)
            {
                int m_i = (int)Math.Sqrt(Math.Pow(d, 2) - Math.Pow(i, 2));
                answer += m_i / k + 1;
            }
            Console.WriteLine(tmp);
            Console.WriteLine("totla="+answer);
            return answer;
        }

        public void datetostamp()
        {
            DateTime d = DateTime.Now;
            
            Console.WriteLine($"todate : {d.ToString("yyyy-MM-dd HH:mm:ss")}");
            Console.WriteLine($"tick: {d.Ticks}");
            Console.WriteLine($"longtime:{d.ToLongTimeString()}");
            Console.WriteLine($"milli:{d.Millisecond}");
            Console.WriteLine($"milli:{(Int32)(DateTime.Now.AddDays(-7).AddHours(-9).Subtract(new DateTime(1970, 1, 1))).TotalSeconds}");

        }
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
