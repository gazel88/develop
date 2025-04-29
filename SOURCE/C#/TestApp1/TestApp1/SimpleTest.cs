using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestApp1
{
    class SimpleTest
    {

        public void strTest()
        {
            try
            {
                string aa = null;
                aa.ToString();
                aa = "geel";

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Environment.Exit(0);
            }

        }
    }
}
