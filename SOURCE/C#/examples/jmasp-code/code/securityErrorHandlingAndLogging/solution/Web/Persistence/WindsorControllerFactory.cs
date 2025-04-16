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
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using Castle.Windsor;

namespace GetOrganized.Web.Persistence
{
    public class WindsorControllerFactory : DefaultControllerFactory
    {
        private readonly IWindsorContainer container;

        public WindsorControllerFactory(IWindsorContainer container)
        {
            this.container = container;
        }

        protected override IController GetControllerInstance(
          RequestContext context, Type controllerType)
        {
            if (controllerType == null)
            {
                throw new HttpException(404,
                  string.Format("The controller for path '{0}' " +
                  "could not be found or it does not implement IController.",
                  context.HttpContext.Request.Path));
            }

            return (IController)container.Resolve(controllerType);
        }
    }
}
