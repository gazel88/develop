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
using GetOrganized.Web.Models.Validator;
using NUnit.Framework;

namespace Test.Unit.Model
{
    [TestFixture]
    public class TodoTest
    {
        private bool IsValid(IValidatable toValidate) 
        {
            var state = new ModelStateDictionary();
            toValidate.Validate(state);
            return state.IsValid; 
        }

        [Test]
        public void Should_Be_Concise_With_Title_Length_To_Maximum_Of_25_Characters()
        {
            var longTodo = new Todo {Title = "123456789ABCDEF123456789ABCDEF"};
            var twentyFiveCharacterTodo =
                new Todo {Title = "123456789ABCDEF1234567"};
            var shortTodo = new Todo {Title = "1234"};

            Assert.IsFalse(IsValid(longTodo)); 
            Assert.IsTrue(IsValid(twentyFiveCharacterTodo));
            Assert.IsTrue(IsValid(shortTodo));
        }
    }
}