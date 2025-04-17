/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System.Security.Principal;
using System.Web.Mvc;
using System.Web.Routing;
using GetOrganized.Web;
using GetOrganized.Web.Controllers;
using GetOrganized.Web.Models;
using GetOrganized.Web.Persistence.Repositories;
using MvcContrib.TestHelper;
using NHibernate;
using NUnit.Framework;
using System.Collections.Generic;
using Rhino.Mocks;
using Test.Unit.Helper;

namespace Test.Unit.Controllers
{
    [TestFixture]
    public class TodoControllerTest
    {
      private TestControllerBuilder builder;
      private TodoController todoController;
      private MockRepository mocks;
      private ISession session;
      private TodoRepository todoRepository;

      [SetUp]
      public void setup()
      {
        mocks = new MockRepository();
        builder = new TestControllerBuilder();
	    session = mocks.DynamicMock<ISession>();
        todoRepository = mocks.StrictMock<TodoRepository>(session); 
        todoController = new TodoController(todoRepository); 
        builder.InitializeController(todoController);

        var routes = RouteTable.Routes;
        routes.Clear();
        RouteDefinitions.AddRoutes(routes);
      }

      [Test]
      public void Should_Display_Todo_List_And_Logged_In_Users_Name()
      {
        const string userName = "Jonathan";
        var todoList = new List<Todo> 
          { new Todo { Title = "Refactor to NHibernate" } };

        builder.HttpContext.User = 
          new GenericPrincipal(new GenericIdentity(userName), null);

        Expect.Call(todoRepository.GetAll()).Return(todoList); 
        mocks.ReplayAll(); 

        var viewData = todoController.Index().
          AssertViewRendered().ViewData;

        Assert.AreEqual(todoList, viewData.Model);

        Assert.AreEqual(userName, viewData["UserName"]);
        mocks.VerifyAll(); 
      }
            
        [Test]
        public void Should_Load_Create_View()
        {
            var viewResult = (ViewResult) todoController.Create();
            Assert.AreEqual(string.Empty, viewResult.ViewName);
        }


        [Test]
        public void Should_Delete_Todo_Item()
        {
            var mistakeTodo = Todo.ThingsToBeDone[0];

            var redirectToRouteResult = (RedirectToRouteResult)
              todoController.Delete(mistakeTodo.Title);

            Assert.IsFalse(Todo.ThingsToBeDone.Contains(mistakeTodo));
            Assert.AreEqual("Index",redirectToRouteResult.RouteValues["action"]);
        }

        [Test]
        public void Should_Load_A_Todo_Item_For_Editing()
        {
            var editTodo = Todo.ThingsToBeDone[0];

            var viewResult = (ViewResult)todoController.Edit(editTodo.Title);

            Assert.AreEqual(editTodo, viewResult.ViewData.Model);
        }

        [Test]
        public void Should_Edit_Todo_Item()
        {
            var editedTodo = new Todo { Title = "Get A LOT MORE milk" };

            var redirectToRouteResult = (RedirectToRouteResult)
                todoController.Edit("Get Milk", editedTodo);

            Assert.Contains(editedTodo, Todo.ThingsToBeDone);
            Assert.AreEqual("Index", redirectToRouteResult.RouteValues["action"]);
        }

        [Test]
        public void Should_Convert_A_Thought_To_A_Todo()
        {
            var expectedTodo = new Todo 
            {
                Title = "Build a killer web site",
                Outcome = "Site has 100 visitors per day",
                Topic = Topic.Topics[0]
            };

            var thought = new Thought { Name = "Build a killer web site", Topic = Topic.Topics[0] };

            var result = (RedirectToRouteResult) todoController.
              Convert(thought, "Site has 100 visitors per day");

            Assert.Contains(expectedTodo, Todo.ThingsToBeDone);
            Assert.IsFalse(Thought.CurrentThoughts.Contains(thought));
            Assert.AreEqual("Process", result.RouteValues["action"]);
            Assert.AreEqual("Thought", result.RouteValues["controller"]);
        }

        [Test]
        public void Should_Be_Logged_In_To_Do_Anything_With_Todos()
        {
            TestHelper.AssertIsAuthorized(typeof(TodoController)); 
        }

        [Test]
        public void Should_Be_Logged_In_To_Create()
        {
            TestHelper.AssertIsAuthorized(
              typeof(TodoController), "Create");
            TestHelper.AssertIsAuthorized(
              typeof(TodoController), "Create", typeof(Todo));
        }

        [Test]
        public void Should_Route_To_Edit_Page()
        {
            "~/Todo/Edit".ShouldMapTo<TodoController>(x => x.Edit(null));
        }

        [Test]
        public void Should_Add_Todo_Item()
        {
          var todo = new Todo 
            {Title = "Learn MVC Controllers"};
          
          var sessionSummary = new SessionSummary();
          sessionSummary.AddedTodos.Add(todo);
          
          todoController.Create(todo).
            AssertActionRedirect().ToAction("Index");
            
          Assert.Contains(todo, Todo.ThingsToBeDone);
          Assert.AreEqual(sessionSummary, 
          todoController.Session["SessionSummary"]);
        }

        [Test]
        public void Should_Display_Errors_When_Todo_Is_Not_Valid()
        {
            var invalidTodo =
              new Todo { Title = "123456789ABCDEF123456789ABCDEF" };

            var modelState = todoController.Create(invalidTodo).
              AssertViewRendered().ViewData.ModelState;

            Assert.IsTrue(modelState.ContainsKey("Title length must be between 0 and 25"));
        }
    }
}
