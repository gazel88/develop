using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace testConsole_2404
{
    public class _test
    {
        public _test()
        {

        }
        public int Add(Ref r)
        {
            Ref nr = new Ref();
            nr = r;
            int ret = Ref.a + nr.b;
            Ref.a = 10;
            return ret;
        }

        public void composetest()
        {
            Ref r;
            int a = 1;
            Console.WriteLine(a);
            Console.WriteLine(r.b);
        }

    }

    public class Ref
    {
        public static int a
        {
            get { return 10; }
            set
            {
                value = 10;
            }
        }  
        
        public int b { get; set; }
      
        public Ref()
        {
        }
    }
   
}
