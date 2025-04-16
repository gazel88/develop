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
using System.Drawing;

namespace GetOrganized.Web.Models
{
    public class Topic
    {
        public static List<Topic> Topics = new List<Topic>
              {
               new Topic {Id = 1, Color = Color.Red, Name = "Work"},
               new Topic {Id = 2, Color = Color.Blue, Name = "Home"}
              };

        public virtual int Id { get; set; }
        public virtual Color Color { get; set; }
        public virtual string Name { get; set; }

        public virtual string ColorHtml
        {
            get { return ColorInWebHex(); }
            set { Color = ColorTranslator.FromHtml(value); }
        }

        public virtual string ColorInWebHex()
        {
            return ColorTranslator.ToHtml(Color);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != typeof (Topic)) return false;
            var other = (Topic) obj;
            return other.Id == Id && 
                other.Color.Equals(Color) && 
                Equals(other.Name, Name); ;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                int result = Id;
                result = (result*397) ^ Color.GetHashCode();
                result = (result*397) ^ (Name != null ? Name.GetHashCode() : 0);
                return result;
            }
        }
    }
}
