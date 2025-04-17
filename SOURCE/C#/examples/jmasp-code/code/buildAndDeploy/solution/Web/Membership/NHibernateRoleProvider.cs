/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using System.Web.Security;
using GetOrganized.Web.Persistence.Repositories;

namespace GetOrganized.Web.Membership
{
    public class NHibernateRoleProvider : RoleProvider
    {
        private readonly RoleRepository roleRepository;

        public NHibernateRoleProvider()
        {
            roleRepository = new RoleRepository();
        }

        public NHibernateRoleProvider(RoleRepository roleRepository)
        {
            this.roleRepository = roleRepository;
        }

        public override void AddUsersToRoles(string[] usernames, string[] roleNames)
        {
            roleRepository.AddUsersToRoles(usernames, roleNames);
        }

        public override string[] GetRolesForUser(string username)
        {
            return roleRepository.GetRolesForUser(username);
        }


        public override string ApplicationName
        {
            get { throw new NotImplementedException(); }
            set { throw new NotImplementedException(); }
        }

        public override bool IsUserInRole(string username, string roleName)
        {
            throw new NotImplementedException();
        }

        public override void CreateRole(string roleName)
        {
            throw new NotImplementedException();
        }

        public override bool DeleteRole(string roleName, bool throwOnPopulatedRole)
        {
            throw new NotImplementedException();
        }

        public override bool RoleExists(string roleName)
        {
            throw new NotImplementedException();
        }

        public override void RemoveUsersFromRoles(string[] usernames, string[] roleNames)
        {
            throw new NotImplementedException();
        }

        public override string[] GetUsersInRole(string roleName)
        {
            throw new NotImplementedException();
        }

        public override string[] GetAllRoles()
        {
            throw new NotImplementedException();
        }

        public override string[] FindUsersInRole(string roleName, string usernameToMatch)
        {
            throw new NotImplementedException();
        }
    }
}