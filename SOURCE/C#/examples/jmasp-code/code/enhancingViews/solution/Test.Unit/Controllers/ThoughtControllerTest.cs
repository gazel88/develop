/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using GetOrganized.Web.Controllers;
using GetOrganized.Web.Models;
using MvcContrib.TestHelper;
using NUnit.Framework;
using Rhino.Mocks;

namespace Test.Unit.Controllers
{
    [TestFixture]
    public class ThoughtControllerTest
    {
        [Test]
        public void Should_List_Thoughts_When_Index_Is_Called()
        {
            var result = (ViewResult)new ThoughtController().Index();

            Assert.AreEqual(Thought.CurrentThoughts, result.ViewData.Model);
        }

        [Test]
        public void Should_List_Topics_When_Creating_New_Thoughts()
        {
            var expectedList = Topic.Topics;
            var viewDataOfTopics = new ThoughtController().
              Create().AssertViewRendered().ViewData["Topics"];
            Assert.AreEqual(expectedList, viewDataOfTopics);
        }

        [Test]
        public void Should_Add_A_Thought_And_Upload_An_Image_On_Create()
        {
            Thought newThought = new Thought
            {
                Id = 3,
                Name = "Research big screen TVs",
                Topic = Topic.Topics.Find(topic => topic.Name == "Home"),
            };

            var collection = new FormCollection { { "Topic.Id", "1" } };

            var mocks = new MockRepository();
            var file = mocks.Stub<HttpPostedFileBase>();
            Expect.Call(file.FileName).Return("bigscreen.jpg"); 
            Expect.Call(file.ContentLength).Return(12);
            mocks.Replay(file);

            var thoughtController = new ThoughtController();
            var builder = new TestControllerBuilder();
            builder.InitializeController(thoughtController);
            builder.Files["ImageAttachment"] = file; 

            thoughtController.Create(newThought, collection).
              AssertActionRedirect().ToAction("Index");

            Assert.Contains(newThought, Thought.CurrentThoughts);

            Assert.IsTrue(newThought.
              ImageAttachment.Contains("UserContent/"));
            Assert.IsTrue(newThought.
              ImageAttachment.Contains("-bigscreen.jpg"));
        }

        [Test]
        public void Should_Display_First_Thought_When_Processing_Thoughts()
        {
            var expectedThought = Thought.CurrentThoughts.First();
            var result = (ViewResult)new ThoughtController().Process();
            Assert.AreEqual(expectedThought, result.ViewData.Model);
        }

        [Test]
        public void Should_Download_File_With_Random_Number_Removed_From_Name()
        {
            var expectedThought = Thought.CurrentThoughts.First();
            expectedThought.ImageAttachment =
              "UserContent/232923-picture.jpg";

            var fileresult = new ThoughtController().
                  Download(expectedThought.Id).
                  AssertResultIs<FilePathResult>();

            //actual filename on web server
            Assert.AreEqual("~/UserContent/232923-picture.jpg",
              fileresult.FileName);

            //file name that user downloads
            Assert.AreEqual("picture.jpg", fileresult.FileDownloadName);
        }

        [Test]
        public void Should_Convert_A_Thought_To_A_Someday()
        {
            Thought writeACompiler = new Thought { Name = "Write a Compiler" };
            new ThoughtController().MakeASomeday(writeACompiler).
              AssertActionRedirect().ToAction("Process");
            Assert.Contains(writeACompiler, Thought.Somedays);
            Assert.IsFalse(Thought.CurrentThoughts.Contains(writeACompiler));
        }
    }
}
