/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using GetOrganized.Web.Models;
using NHibernate;

namespace GetOrganized.Web.Persistence.Repositories
{
    public class TopicRepository : BaseRepository<Topic>
    {
        public TopicRepository(ISession session)
            : base(session)
        {
        }
    }
}