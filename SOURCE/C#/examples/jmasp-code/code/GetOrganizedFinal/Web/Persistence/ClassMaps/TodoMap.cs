/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
using FluentNHibernate.Mapping;
using GetOrganized.Web.Models;

namespace GetOrganized.Web.Persistence.ClassMaps
{
    public sealed class TodoMap : ClassMap<Todo>
    {
        public TodoMap()
        {
            Id(x => x.Id);
            Map(x => x.Title);
            Map(x => x.Completed);
            Map(x => x.Outcome);
            References(x => x.Topic).ForeignKey().Not.LazyLoad();
        }
    }
}