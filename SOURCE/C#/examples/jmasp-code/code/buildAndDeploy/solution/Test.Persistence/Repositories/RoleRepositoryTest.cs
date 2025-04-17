/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System.Collections.Generic;
using GetOrganized.Web.Models;
using GetOrganized.Web.Persistence.Repositories;
using NUnit.Framework;

namespace Test.Persistence.Repositories
{
    [TestFixture]
    public class RoleRepositoryTest : RepositoryTestBase
    {
        private RoleRepository roleRepository;
        private UserRepository userRepository;

        [SetUp]
        public void Setup()
        {
            userRepository = new UserRepository(session);
            roleRepository = new RoleRepository(session);
        }

        [Test]
        public void Should_Add_Roles_To_User()
        {
            const string expectedRole = "role1";
            var membershipUser = new User { UserName = "foo", ApplicationName = "GetOrganized" };

            userRepository.SaveUser(membershipUser);
            userRepository.GetAllUsers(0, 20, "GetOrganized");
            roleRepository.AddRole(expectedRole);
            session.Flush();


            roleRepository.AddUsersToRoles(new[] { membershipUser.UserName }, new[] { expectedRole });
            session.Flush();

            var actualRoles = roleRepository.GetRolesForUser(membershipUser.UserName);

            Assert.Contains(expectedRole, actualRoles);
            Assert.AreEqual(1, actualRoles.Length);
        }

    }
}
