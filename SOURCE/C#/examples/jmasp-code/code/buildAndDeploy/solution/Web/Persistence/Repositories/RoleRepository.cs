/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using GetOrganized.Web.Models;
using NHibernate;
using NHibernate.Criterion;
using Web;

namespace GetOrganized.Web.Persistence.Repositories
{
    //this class does not inherit from BaseRepository
    public class RoleRepository
    {
        private readonly ISession session;

        //Special constructor because this repository is called earlier in the ASP.NET lifecyle
        //when Windsor is not yet created
        public RoleRepository()
        {
            session = MvcApplication.InitializeNHibernate().OpenSession();
        }

        public RoleRepository(ISession session)
        {
            this.session = session;
        }

        public void AddUsersToRoles(string[] usernames, string[] roleNames)
        {
            var users = session.CreateQuery(
                "from User as user where user.UserName in ( '" + 
                String.Join("','", usernames) + "' )").List<User>();
            
            var roles = session.CreateQuery(
                "from Role as role where role.Name in ( '" + 
                String.Join("','", roleNames) + "' )").List<Role>();
            
            foreach (var user in  users)
            {
                foreach (var role in roles)
                {
                    user.Roles.Add(role);
                }
                session.Save(user);
            }
        }
        
        public string[] GetRolesForUser(string username)
        {
            var roles = (List<Role>) session.CreateQuery(
                "select user.Roles from User as user where user.UserName = '" + username + "'").List<Role>();

            return roles.ConvertAll(x => x.Name).ToArray();
        }

        public void AddRole(string roleName)
        {
            session.Save(new Role {Name = roleName});
        }
    }
}
