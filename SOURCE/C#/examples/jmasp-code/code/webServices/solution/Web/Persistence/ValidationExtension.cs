/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿using System.Web.Mvc;
using GetOrganized.Web.Models;
using NHibernate.Validator.Engine;

namespace GetOrganized.Web.Persistence
{
    public static class ValidationExtension
    {
        public static void Validate(this IValidatable model,
          ModelStateDictionary state)
        {
            InvalidValue[] invalidValues =
              new ValidatorEngine().Validate(model);

            foreach (var error in invalidValues)
            {
                var errorMessage = error.PropertyName + " " + error.Message;
                state.AddModelError(errorMessage, errorMessage);
            }
        }
    }
}
