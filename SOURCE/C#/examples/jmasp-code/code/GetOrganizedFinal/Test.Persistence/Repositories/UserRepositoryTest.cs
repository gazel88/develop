/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System.Collections;
using GetOrganized.Web.Models;
using GetOrganized.Web.Persistence.Repositories;
using NUnit.Framework;

namespace Test.Persistence.Repositories
{
    [TestFixture]
    public class UserRepositoryTest : RepositoryTestBase
    {
        private UserRepository repository;

        [SetUp]
        public void Setup()
        {
            repository = new UserRepository(session);
        }

        [Test]
        public void Should_Create_And_Read()
        {
            var membershipUser = new User {UserName = "foo", ApplicationName = "GetOrganized"};

            repository.SaveUser(membershipUser);
            var actual = (IList) repository.GetAllUsers(0, 20, "GetOrganized");
            Assert.Contains(membershipUser, actual);
            Assert.AreEqual(1, actual.Count);
        }

    }
}
