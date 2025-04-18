/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using NHibernate;
using NHibernate.Cfg;

namespace GetOrganized.Web.Persistence
{
    public class NHibernateConfiguration
    {
        public static ISessionFactory SessionFactory { get; private set; }

        public static ISessionFactory Init(IPersistenceConfigurer databaseConfig, Action<Configuration> schemaConfiguration)
        {
            return SessionFactory = Fluently.Configure()
                .Database(
                    databaseConfig)
                .Mappings(m => m.FluentMappings.AddFromAssemblyOf<NHibernateConfiguration>())
                .ExposeConfiguration(schemaConfiguration)
                .BuildSessionFactory();
        }

        public static ISession CreateAndOpenSession()
        {
            return SessionFactory.OpenSession();
        }
    }
}