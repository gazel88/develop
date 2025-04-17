using System;
using System.Collections.Generic;
using System.Linq;
namespace prommersTest
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Solution program = new Solution();
            //program.solution(new int[,] { { 60, 50 }, { 30, 70 }, { 60, 30 },{ 80, 40 } });
            //var answer = program.solution(9, new int[,] { { 1, 3},{ 2, 3 },{ 3, 4 },{ 4, 5 },{ 4, 6 },{ 4, 7 },{ 7, 8 },{ 7, 9 } });
            program.solution();
            //Console.WriteLine(answer);
            Console.ReadLine();
        }
    }

    public class Solution
    {
        public  List<(int, int)> Moves { get; set; } = new List<(int, int)>();
        public void solution()
        {
            int n = 4; // Example with 3 disks
            Moves.Clear();
            MoveDisks(n, 1, 3, 2);
            List<(int, int)> result = Moves;
            foreach (var move in result)
            {
                Console.WriteLine($"{move.Item1} -> {move.Item2}");
            }
        }

        public void MoveDisks(int n, int from, int to, int via)
        {
            if (n == 1)
            {
                Moves.Add((from, to));
                Console.WriteLine("n =" + n + ",from=" + from + ",to=" + to);
            }
            else
            {
                MoveDisks(n - 1, from, via, to);
                Moves.Add((from, to));
                Console.WriteLine("n =" + n + ",from=" + from + ",to=" + to);
                MoveDisks(n - 1, via, to, from);
            }
        }
        //public int solution(int n, int[,] wires)
        //{
        //    bool[,] data = new bool[n, n];

        //    for (int i = 0; i < wires.GetLength(0); i++)
        //    {
        //        data[wires[i, 0] - 1, wires[i, 1] - 1] = true;
        //        data[wires[i, 1] - 1, wires[i, 0] - 1] = true;
        //    }

        //    bool[] path;
        //    int answer = int.MaxValue;
        //    for (int i = 0; i < wires.GetLength(0); i++)
        //    {
        //        data[wires[i, 0] - 1, wires[i, 1] - 1] = false;
        //        data[wires[i, 1] - 1, wires[i, 0] - 1] = false;

        //        path = new bool[n];
        //        DFS(0, data, path);
        //        answer = Math.Min(answer, Math.Abs(path.Count(x => x) * 2 - n));

        //        data[wires[i, 0] - 1, wires[i, 1] - 1] = true;
        //        data[wires[i, 1] - 1, wires[i, 0] - 1] = true;
        //    }

        //    return answer;
        //}

        //private void DFS(int current, bool[,] data, bool[] path)
        //{
        //    path[current] = true;

        //    for (int i = 0; i < data.GetLength(1); i++)
        //    {
        //        if (data[current, i] && !path[i])
        //        {
        //            DFS(i, data, path);
        //        }
        //    }
        //}
        //public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t)
        //{
        //    long answer = -1;
        //    //도시별로 돌림
        //    for (int i = 0; i < g.Length; i++)
        //    {
        //        //가져갈 양
        //        int gtransfer = g[i] > a ? a : g[i];
        //        int stransfer = 0;
        //        // 정해진 양만큼인지 확인
        //        if(gtransfer > w[i])
        //        {
        //            gtransfer = w[i];
        //            a = a - gtransfer;
        //        }
        //        else
        //        {
        //            //은 더 가져가기
        //            stransfer

        //        }
        //        transfer = transfer > w[i] ? transfer : w[i];
        //        //더 가져갈 수 있을때
        //        if(w[i] > transfer)
        //        {
        //            transfer+=
        //        }

        //        g[i] = g[i] - w[i];
        //    }
        //    return answer;
        //}
        public int walletsolution(int[,] sizes)
        {
            int answer = 0;
            int[] val = new int[] {0,0};
            for (int i = 0; i < sizes.GetLength(0); i++)
            {
                int small = 0;
                int big = 0;
                if(sizes[i, 0] > sizes[i, 1])
                {
                    small = sizes[i, 1];
                    big = sizes[i, 0];
                }
                else
                {
                    small =sizes[i, 0];
                    big = sizes[i, 1];
                }

                if(val[0]>val[1])
                {
                    val[1] = small>val[1]?small:val[1];
                    val[0] = big>val[0]?big:val[0];
                }
                else
                {
                    val[0] = small>val[0]?small:val[0];
                    val[1] = big>val[1]?big:val[1];
                }
            }
            answer = val[0] * val[1];
            return answer;
        }
    }
}
