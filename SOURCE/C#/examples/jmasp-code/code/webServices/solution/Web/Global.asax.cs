/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using Castle.Core;
using Castle.Facilities.FactorySupport;
using Castle.MicroKernel.Registration;
using Castle.Windsor;
using FluentNHibernate.Cfg.Db;
using GetOrganized.Web;
using GetOrganized.Web.Gateways;
using GetOrganized.Web.Persistence;
using NHibernate;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;
using Web.Controllers;

namespace Web
{
    // Note: For instructions on enabling IIS6 or IIS7 classic mode, 
    // visit http://go.microsoft.com/?LinkId=9394801

    public class MvcApplication : HttpApplication
    {
        private readonly object lockObject = new object();
        private bool wasNHibernateInitialized;


        protected void Application_Start()
        {
            SetupWindsorContainer();
            RegisterRoutes(RouteTable.Routes);
        }


        private void Application_BeginRequest(object sender, EventArgs e)
        {
            InitializeNHibernate();
        }

        private void Application_EndRequest(object sender, EventArgs e)
        {
            NHibernateSessionStorage.DisposeCurrent();
        }

        private void InitializeNHibernate()
        {
            if (!wasNHibernateInitialized)
            {
                lock (lockObject)
                {
                    if (!wasNHibernateInitialized)
                    {
                        NHibernateConfiguration.Init(
                            MsSqlConfiguration.MsSql2005.
                                ConnectionString(builder => builder.
                                                                FromConnectionStringWithKey("ApplicationServices")),
                            UpdateDatabase());

                        wasNHibernateInitialized = true;
                    }
                }
            }
        }

        private Action<Configuration> UpdateDatabase()
        {
            return config => new SchemaUpdate(config).Execute(false, true);
        }

        private void SetupWindsorContainer()
        {
            IWindsorContainer container = new WindsorContainer();

            RegisterControllers(container);
            RegisterNHibernateSessionFactory(container);
            RegisterRepositories(container);
            RegisterGateways(container);
        }

        private void RegisterControllers(IWindsorContainer container)
        {
            ControllerBuilder.Current.
                SetControllerFactory(new WindsorControllerFactory(container));
            container.RegisterControllers(typeof (HomeController).Assembly);
        }

        private void RegisterNHibernateSessionFactory(IWindsorContainer container)
        {
            container.AddFacility<FactorySupportFacility>();
            container.Register(Component.For<ISession>().
                                   UsingFactoryMethod(() =>
                                                      NHibernateSessionStorage.RetrieveSession()).
                                   LifeStyle.Is(LifestyleType.Transient));
        }

        private void RegisterGateways(IWindsorContainer container)
        {
            container.AddComponentLifeStyle("bloggerGateway",
              typeof(BloggerGateway), LifestyleType.Transient);
        }

        private void RegisterRepositories(IWindsorContainer container)
        {
            IEnumerable<Type> repositories = Assembly.GetExecutingAssembly().
                GetTypes().Where(IsRepository);

            foreach (Type repository in repositories)
            {
                container.AddComponentLifeStyle(repository.Name, repository,
                                                LifestyleType.Transient);
            }
        }

        private bool IsRepository(Type type)
        {
            return type.Namespace != null && type.IsClass && !type.IsAbstract &&
                   type.Namespace.Contains("GetOrganized.Web.Persistence.Repositories");
        }

        private static void RegisterRoutes(RouteCollection routes)
        {
            RouteDefinitions.AddRoutes(routes);
        }
    }
}