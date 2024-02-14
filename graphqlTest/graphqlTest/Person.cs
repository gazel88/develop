using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace graphqlTest
{
    public class Person
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public bool ForceUser { get; set; }
        public string FavoriteSong { get; set; }
    }
}