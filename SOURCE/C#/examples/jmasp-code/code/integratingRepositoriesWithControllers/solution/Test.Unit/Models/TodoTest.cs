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
using System.Text;
using System.Web.Mvc;
using GetOrganized.Web.Models;
using GetOrganized.Web.Persistence;
using NUnit.Framework;

namespace Test.Unit.Models
{
    [TestFixture]
    public class TodoTest
    {
        [Test]
        public void Title_Length_Should_Be_To_Maximum_Of_25_Characters()
        {
            Todo longTodo = new Todo { Title = "123456789ABCDEF123456789ABCDEF" };
            Todo twentyFiveCharacterTodo =
              new Todo { Title = "123456789ABCDEF1234567" };
            Todo shortTodo = new Todo { Title = "1234" };

            Assert.IsFalse(IsValid(longTodo)); 
            Assert.IsTrue(IsValid(twentyFiveCharacterTodo));
            Assert.IsTrue(IsValid(shortTodo));
        }

        private bool IsValid(IValidatable toValidate) 
        {
            ModelStateDictionary state = new ModelStateDictionary();
            toValidate.Validate(state);
            return state.IsValid; 
        }
    }
}
