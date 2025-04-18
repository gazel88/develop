/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
﻿
using System.Web.Mvc;

namespace GetOrganized.Web.Models
{
    // a marker for Models that can be validated
    public interface IValidatable
    {
        void Validate(ModelStateDictionary state);
    }
}
