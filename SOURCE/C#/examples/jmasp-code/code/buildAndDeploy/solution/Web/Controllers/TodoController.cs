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
using GetOrganized.Web.Gateways;
using GetOrganized.Web.Models;
using GetOrganized.Web.Models.Binders;
using GetOrganized.Web.Persistence;
using GetOrganized.Web.Persistence.Repositories;
using MvcContrib.ActionResults;

namespace GetOrganized.Web.Controllers
{
    [Authorize]
    public class TodoController : Controller
    {
        private readonly TodoRepository repository;
        private readonly BloggerGateway gateway;

        public TodoController(TodoRepository repository, BloggerGateway gateway) 
        {
            this.repository = repository;
            this.gateway = gateway;
        }

        //
        // GET: /Todo/

        public ActionResult Index()
        {
            ViewData["UserName"] = User.Identity.Name;

            ViewData.Model = repository.GetAll();

            if (Request.Headers["Content-Type"] == "text/xml")
                return new XmlResult(ViewData.Model);
            else
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
        [Authorize]
        public ActionResult Create()
        {
            return View();
        }

        [Authorize]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult CreateWithXml(
          [ModelBinder(typeof(TodoXmlBinder))] Todo todo)
        {
            Create(todo);
            return new XmlResult(todo);
        }

        //
        // POST: /Todo/Create
        [Authorize]
        [HttpPost]
        public ActionResult Create(Todo todo)
        {
            todo.Validate(ModelState); 

            if (ModelState.IsValid) 
            {
                CreateTodo(todo);
                if (Request.IsAjaxRequest())
                    return Json(todo);
                return RedirectToAction("Index");
            }
            else 
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
        // POST: /Todo/Delete?Title=someTitle
        [HttpPost]
        public ActionResult Delete(string title)
        {
            Todo.ThingsToBeDone.
                RemoveAll(todo => todo.Title == title);

            if (Request.IsAjaxRequest()) 
                return new EmptyResult();

            return RedirectToAction("Index");
        }

        public ActionResult Convert(Thought thought, string outcome)
        {
            var newTodo = new Todo
            {
                Title = thought.Name,
                Outcome = outcome,
                Topic = Topic.
                Topics.Find(topic =>
                  topic.Id == thought.Topic.Id)
            };

            CreateTodo(newTodo);

            Thought.CurrentThoughts.RemoveAll(thoughtToRemove =>
              thoughtToRemove.Name == thought.Name); 

            return RedirectToAction("Process", "Thought"); 
        }

        public ActionResult PublishToBlogger(string title)
        {
            Todo todo = repository.GetAll().Where(x => x.Title == title).First();

            return Redirect(gateway.AuthenticateURL(todo.Title));
        }

        public ActionResult BloggerAuthorized(string title, string token)
        {
            var todo = repository.GetAll().Where(x => x.Title == title).First();

            gateway.PublishAsDraft(todo, token);

            return Redirect("http://jonathanmccracken.blogspot.com");
        }

        private void CreateTodo(Todo todo)
        {
            Todo.ThingsToBeDone.Add(todo);
            if (Session["SessionSummary"] == null) 
                Session["SessionSummary"] = new SessionSummary();

            var summary = ((SessionSummary)Session["SessionSummary"]); 
            summary.AddedTodos.Add(todo);
        }
    }
}
