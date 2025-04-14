using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using GraphQL;
using GraphQL.Types;
using GraphQL.SystemTextJson;

namespace graphqlTest.Controllers
{
    public class PersonController : Controller
    {
        // GET: Person
        public ActionResult Index()
        {
            var schema = Schema.For(@"
              type Query {
                hello: String
                test : String
              }
            ");

            var json = schema.ExecuteAsync(_ =>
            {
                _.Query = "{ hello, test2 }";
                _.Root = new { hello = "Hello World!", test = "newtest" };
            });

            return View(json);
        }


        public Person RetrievePerson(int id)
        {
            // Normally you'd do a database lookup here
            return new Person()
            {
                Id = id,
                FirstName = "Luke",
                LastName = "Skywalker",
                ForceUser = true,
                FavoriteSong = "Papa was a Rollin' Stone",
            };
        }

    }
}