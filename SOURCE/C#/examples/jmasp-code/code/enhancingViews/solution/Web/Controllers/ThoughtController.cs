/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using System.IO;
using System.Linq;
using System.Web;
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
            ViewData.Model = Thought.CurrentThoughts;
            return View();
        }

        //
        // GET: /Thought/Create

        public ActionResult Create()
        {
            ViewData["Topics"] = Topic.Topics;
            return View();
        }

        // POST: /Thought/Create
        [HttpPost]
        public ActionResult Create([Bind(Exclude = "Topic")] Thought thought, FormCollection collection)
        {
            thought.Topic = Topic.Topics.Find(x => x.Id == Int32.Parse(collection["Topic.Id"]));

            HttpPostedFileBase file = Request.Files["ImageAttachment"]; 
            if (file.ContentLength != 0)
            {
                int randomNumber =
                    new Random().
                        Next(100000, Int32.MaxValue); 
                string imgUrl =
                    "UserContent/" + randomNumber
                    + "-" + file.FileName;
                file.SaveAs(
                    Server.MapPath("~/UserContent") + "/" +
                    randomNumber + "-" + file.FileName); 
                thought.ImageAttachment = imgUrl;
            }

            Thought.CurrentThoughts.Add(thought);
            return RedirectToAction("Index");
        }


        public ViewResult Process()
        {
            ViewData.Model = Thought.CurrentThoughts.First();
            return View();
        }

        public FilePathResult Download(int id)
        {
            Thought thought = Thought.CurrentThoughts.Find(x => x.Id == id);

            return File("~/" +
              thought.ImageAttachment, "application/octet-stream",
                Path.GetFileName(thought.ImageAttachment)
                    .Split(new[] { '-' }, 2)[1]);
        }

        // POST: /Thought/MakeASomeday

        [HttpPost]
        public ActionResult MakeASomeday(Thought aThoughtToDoSomeday)
        {
            Thought.Somedays.Add(aThoughtToDoSomeday);
            Thought.CurrentThoughts.Remove(aThoughtToDoSomeday);

            return RedirectToAction("Process");
        }
    }
}