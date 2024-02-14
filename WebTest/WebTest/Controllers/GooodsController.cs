using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebTest.Controllers
{
    public class GooodsController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult good()
        {
            return View();
        }
    }
}
