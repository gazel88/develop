using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestApp1
{
    public class DateTest
    {
        public static void DateFormatChangeVariation()
        {
            DateTime d = DateTime.Now;

            Console.WriteLine($"todate : {d.ToString("yyyy-MM-dd HH:mm:ss")}");
            Console.WriteLine($"tick: {d.Ticks}");
            Console.WriteLine($"longtime:{d.ToLongTimeString()}");
            Console.WriteLine($"milli:{d.Millisecond}");
            Console.WriteLine($"milli:{(Int32)(DateTime.Now.AddDays(-7).AddHours(-9).Subtract(new DateTime(1970, 1, 1))).TotalSeconds}");

        }
    }
}
