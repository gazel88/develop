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
    public class TodoController : Controller
    {
        //
        // GET: /Todo/

        public ActionResult Index()
        {
            ViewData.Model = Todo.ThingsToBeDone;
            return View();
        }

        //
        // GET: /Todo/Details/5

        public ActionResult Details(int id)
        {
            return View();
        }

        //
        // GET: /Todo/Create

        public ActionResult Create()
        {
            return View();
        } 

        //
        // POST: /Todo/Create

        [HttpPost]
        public ActionResult Create(Todo todo)
        {
            try
            {
                Todo.ThingsToBeDone.Add(todo);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: /Todo/Edit/somethingToDo
        public ActionResult Edit(string title)
        {
            ViewData.Model =
              Todo.ThingsToBeDone.Find(todo => todo.Title == title);
            return View();
        }

        // POST: /Todo/Edit?Title=somethingToDo

        [HttpPost]
        public ActionResult Edit(string oldTitle, Todo item)
        {
            try
            {
                Todo.ThingsToBeDone.
                  RemoveAll(aTodo => aTodo.Title == oldTitle);
                Todo.ThingsToBeDone.Add(item);

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }


        //
        // GET: /Todo/Delete?Title=someTitle

        public ActionResult Delete(string title)
        {
            Todo.ThingsToBeDone.
                RemoveAll(todo => todo.Title == title);

            return RedirectToAction("Index");
        }
    }
}
