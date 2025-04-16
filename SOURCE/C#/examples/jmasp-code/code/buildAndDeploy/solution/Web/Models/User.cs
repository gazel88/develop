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

namespace GetOrganized.Web.Models
{
    public class User
    {
        public User()
        {
            Roles = new List<Role>();
        }

        public virtual int UserID { get; set; }
        public virtual string UserName { get; set; }
        public virtual string ApplicationName { get; set; }
        public virtual string EMail { get; set; }
        public virtual string Comment { get; set; }
        public virtual string Password { get; set; }
        public virtual string PasswordQuestion { get; set; }
        public virtual string PasswordAnswer { get; set; }
        public virtual bool IsApproved { get; set; }
        public virtual DateTime? LastActivityDate { get; set; }
        public virtual DateTime? LastLoginDate { get; set; }
        public virtual DateTime? LastPasswordChangedDate { get; set; }
        public virtual DateTime? CreationDate { get; set; }
        public virtual bool IsOnline { get; set; }
        public virtual bool IsLockedOut { get; set; }
        public virtual DateTime? LastLockedOutDate { get; set; }
        public virtual int FailedPasswordAttemptCount { get; set; }
        public virtual DateTime? FailedPasswordAttemptWindowStart { get; set; }
        public virtual int FailedPasswordAnswerAttemptCount { get; set; }
        public virtual DateTime? FailedPasswordAnswerAttemptWindowStart { get; set; }
        public virtual DateTime? PrevLoginDate { get; set; }

        public virtual IList<Role> Roles { get; set; }
    }
}
