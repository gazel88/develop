/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using GetOrganized.Web.Models;

namespace GetOrganized.Web.Controllers
{
    public class SessionController : Controller
    {
        //
        // GET: /SessionSummary/

        public ActionResult Index()
        {
            if (Session["SessionSummary"] == null)
                Session["SessionSummary"] = new SessionSummary();

            return View(Session["SessionSummary"]);
        }

    }
}
