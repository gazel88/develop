/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
using GetOrganized.Web.Controllers;
using GetOrganized.Web.Models;
using MvcContrib.TestHelper;
using NUnit.Framework;

namespace Test.Unit.Controller
{
    [TestFixture]
    public class SessionControllerTest
    {
        [Test]
        public void Should_Display_SessionSummary_On_Index()
        {
            var summary = new SessionSummary {AddedTodos = {new Todo {Title = "Complete Management Report"}}};

            var sessionController = new SessionController();
            var builder = new TestControllerBuilder();
            builder.InitializeController(sessionController);
            builder.Session["SessionSummary"] = summary;

            Assert.AreEqual(summary, sessionController.Index().AssertViewRendered().ViewData.Model);
        }
    }
}