/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using System.Linq;
using System.Web.Mvc;
using GetOrganized.Web.Models;

namespace GetOrganized.Web.Controllers
{
    public class ThoughtController : Controller
    {
        //
        // GET: /Thought/

        public ActionResult Index()
        {
            ViewData.Model = Thought.Thoughts;
            return View();
        }

        //
        // GET: /Thought/Create

        public ActionResult Create()
        {
            ViewData["Topics"] = Topic.Topics.ConvertAll(topic => 
              new SelectListItem
              {
                  Text = topic.Name,
                  Value = topic.Id.ToString()
              });
            return View();
        }

        // POST: /Thought/Create
        [HttpPost]
        public ActionResult Create([Bind(Exclude = "Topic")] Thought thought, FormCollection collection)
        {
            thought.Topic = Topic.Topics.Find(x => x.Id == Int32.Parse(collection["Topic.Id"]));

            Thought.Thoughts.Add(thought);
            return RedirectToAction("Index");
        }

        public ViewResult Process()
        {
            ViewData.Model = Thought.Thoughts.First();
            return View();
        }
    }
}
