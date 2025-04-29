using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestApp1
{
    class ArrayTest
    {

        public static int[,] Hanoisolution(int n)
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

        public static void HanoiSearch(int n, List<int[]> path, int start, int end, int via)
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
            for (int i = 0; i < words.Length; i++)
            {
                if (passed.Where(x => x == words[i]).Any() || (i > 0 && !words[i].StartsWith(passed[i - 1].Reverse().ElementAt(0))))
                {
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

            for (int i = 0; i < Math.Log(n, 2); i++)
            {
                answer++;
                a = (a + 1) / 2;
                b = (b + 1) / 2;
                if (a == b)
                {
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
            for (int i = 0; i < cards.Length; i++)
            {
                // 처음부터 열린 상자인지 확인
                if (openedBoxIdx.Where(x => x == i).Count() < 1)
                {
                    openedBoxIdx.Add(i);
                    int newboxidx = cards[i] - 1;
                    int cnt = 1;
                    while (openedBoxIdx.Where(x => x == newboxidx).Count() < 1)
                    {
                        openedBoxIdx.Add(newboxidx);
                        newboxidx = cards[newboxidx] - 1;
                        cnt++;
                    }
                    groupSize.Add(cnt);
                }

            }
            if (groupSize.Count > 1)
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

            for (long i = 0; i <= d; i += k)
            {
                int m_i = (int)Math.Sqrt(Math.Pow(d, 2) - Math.Pow(i, 2));
                answer += m_i / k + 1;
            }
            Console.WriteLine(tmp);
            Console.WriteLine("totla=" + answer);
            return answer;
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

        public List<opclass> GetOptionTest(string op)
        {
            List<opclass> ar_lst = new List<opclass>();
            string[] groups = op.Split('/');

            void GetArrList(string[] group, int depth, string[] cur_val)
            {
                if (group.Length == depth)
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
    }
}
