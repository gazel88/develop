/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using NHibernate;

namespace GetOrganized.Web.Persistence.Repositories
{
    public abstract class BaseRepository<T>
    {
        protected readonly ISession session;

        protected BaseRepository(ISession session)
        {
            this.session = session;
        }

        public virtual void SaveOrUpdate(T model)
        {
            session.SaveOrUpdate(model);
        }

        public T Get(object primaryKey)
        {
            return session.Get<T>(primaryKey);
        }

        public void Delete(T model)
        {
            session.Delete(model);
        }
    }
}