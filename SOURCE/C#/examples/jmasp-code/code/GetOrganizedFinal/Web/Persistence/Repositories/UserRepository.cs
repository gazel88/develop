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
using GetOrganized.Web.Models;
using NHibernate;
using NHibernate.Criterion;

namespace GetOrganized.Web.Persistence.Repositories
{
    //this class does not inherit from BaseRepository
    public class UserRepository
    {
        private readonly ISession session;

        //Special constructor because this repository is called earlier in the ASP.NET lifecyle
        //when Windsor is not yet created
        public UserRepository()
        {
            session = MvcApplication.InitializeNHibernate().OpenSession();
        }

        public UserRepository(ISession session)
        {
            this.session = session;
        }

        public User GetUserByName(string userName, string appName)
        {
            var u = session.CreateCriteria(typeof (User))
                        .Add(Restrictions.Eq("UserName", userName))
                        .Add(Restrictions.Eq("ApplicationName", appName))
                        .UniqueResult() as User;
            return u;
        }

        public void SaveUser(User userToSave)
        {
            using (ITransaction trans = session.BeginTransaction())
            {
                session.SaveOrUpdate(userToSave);
                trans.Commit();
            }
        }

        public void DeleteUser(User userToDelete)
        {
            using (ITransaction trans = session.BeginTransaction())
            {
                session.Delete(userToDelete);
                trans.Commit();
            }
        }

        public List<User> GetAllUsers(int pageIndex, int pageSize, string appName)
        {
            var users = new List<User>();

            using (ITransaction trans = session.BeginTransaction())
            {
                users = session.CreateCriteria(typeof (User))
                            .Add(Restrictions.Eq("ApplicationName", appName))
                            .SetFirstResult(pageIndex*pageSize).SetMaxResults(pageSize)
                            .List<User>() as List<User>;
            }

            return users;
        }

        public int GetNumberOfUsersOnline(DateTime compareTime, string appName)
        {
            using (ITransaction trans = session.BeginTransaction())
            {
                IList uList = session.CreateCriteria(typeof (User))
                    .Add(Restrictions.Eq("ApplicationName", appName))
                    .Add(Restrictions.Ge("LastActivityDate", compareTime))
                    .List();
                return uList.Count;
            }
        }

        public User GetUserByID(int userID)
        {
            return session.Get<User>(userID);
        }


        public string GetUserNameByEMail(string EMail, string appName)
        {
            using (ITransaction trans = session.BeginTransaction())
            {
                IList uList = session.CreateCriteria(typeof (User))
                    .Add(Restrictions.Eq("EMail", EMail))
                    .Add(Restrictions.Eq("ApplicationName", appName))
                    .List();
                if (uList.Count > 0)
                {
                    var u = (User) uList[0];
                    return u.UserName;
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public List<User> FindUsersByEMail(string email, int pageIndex, int pageSize, string appName)
        {
            var users = new List<User>();

            using (ITransaction trans = session.BeginTransaction())
            {
                users = session.CreateCriteria(typeof (User))
                            .Add(Restrictions.Like("EMail", email, MatchMode.Anywhere))
                            .Add(Restrictions.Eq("ApplicationName", appName))
                            .SetFirstResult(pageIndex*pageSize).SetMaxResults(pageSize)
                            .List<User>() as List<User>;
            }

            return users;
        }

        public List<User> FindUsersByName(string userName, int pageIndex, int pageSize, string appName)
        {
            var users = new List<User>();

            using (ITransaction trans = session.BeginTransaction())
            {
                users = session.CreateCriteria(typeof (User))
                            .Add(Restrictions.Like("UserName", userName, MatchMode.Anywhere))
                            .Add(Restrictions.Eq("ApplicationName", appName))
                            .SetFirstResult(pageIndex*pageSize).SetMaxResults(pageSize)
                            .List<User>() as List<User>;
            }

            return users;
        }
    }
}