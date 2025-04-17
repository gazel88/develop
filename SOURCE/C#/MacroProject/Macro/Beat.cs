using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Macro
{
    public class Beat :CafeList
    {
        public string branch;
        public string category;

        public Beat(string date, string time, string theme, string url, string branch)
        {
            this.goalDate = date;
            this.goalTime = time;
            this.goalTheme = theme;
            this.url = url;
            this.branch = branch;
        }
    }
}
