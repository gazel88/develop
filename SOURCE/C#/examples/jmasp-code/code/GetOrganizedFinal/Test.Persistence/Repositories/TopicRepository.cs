/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System.Collections;
using System.Drawing;
using GetOrganized.Web.Models;
using GetOrganized.Web.Persistence.Repositories;
using NUnit.Framework;

namespace Test.Persistence.Repositories
{
    [TestFixture]
    public class TopicRepositoryTest : RepositoryTestBase
    {
        #region Setup/Teardown

        [SetUp]
        public void Setup()
        {
            //remove this call to base cause Resharper 4.5 doesn't support NUnit 2.5 very well yet
            setup();

            repository = new TopicRepository(session);
        }

        #endregion

        private TopicRepository repository;

        private Topic CreateTopic()
        {
            var topic = new Topic
                            {
                                Name = "A Greatly Debated Topic",
                                Color = Color.AliceBlue
                            };
            repository.SaveOrUpdate(topic);

            session.Flush();
            return topic;
        }

        [Test]
        public void Should_Create_And_Read()
        {
            Topic topic = CreateTopic();
            var actual = (IList) repository.GetAll();
            Assert.Contains(topic, actual);
            Assert.AreEqual(1, actual.Count);
        }

        [Test]
        public void Should_Delete()
        {
            Topic originalTopic = CreateTopic();
            repository.Delete(originalTopic);
            session.Flush();
            Assert.IsEmpty((ICollection) repository.GetAll());
        }

        [Test]
        public void Should_Edit()
        {
            Topic originalTopic = CreateTopic();

            session.Clear();


            Topic toModify = repository.Get(originalTopic.Id);
            toModify.Name = "Get Update working";
            repository.SaveOrUpdate(toModify);

            session.Flush();

            var actual = (IList) repository.GetAll();
            Assert.Contains(toModify, actual);
            Assert.IsFalse(actual.Contains(originalTopic));
        }
    }
}