/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿
using FluentNHibernate.Mapping;
using GetOrganized.Web.Models;

namespace GetOrganized.Web.Persistence.ClassMaps
{
    public class UserMap : ClassMap<User>
    {
        public UserMap()
        {
            Id(x => x.UserID);
            Map(x => x.UserName).Unique();
            Map(x => x.PasswordQuestion);
            Map(x => x.ApplicationName);
            Map(x => x.Comment);
            Map(x => x.EMail);
            Map(x => x.LastPasswordChangedDate);
            Map(x => x.CreationDate);
            Map(x => x.LastLoginDate);
            Map(x => x.LastLockedOutDate);
            Map(x => x.LastActivityDate);
            Map(x => x.IsApproved);
            Map(x => x.IsLockedOut);
            HasManyToMany(x => x.Roles).Not.LazyLoad();
        }
    }
}
