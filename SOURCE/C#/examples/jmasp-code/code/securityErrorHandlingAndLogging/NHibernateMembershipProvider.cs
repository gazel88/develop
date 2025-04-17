/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
public class NHibernateMembershipProvider : MembershipProvider
{
  public UserRepository UserRepo { get; set; }
  
  public override void Initialize(string name, NameValueCollection config)
  {
    config = SetConfigDefaults(config);
    name = SetDefaultName(name);
    base.Initialize(name, config);
    ValidatingPassword += NHibernateMembershipProvider_ValidatingPassword;
    SetConfigurationProperties(config);
    CheckEncryptionKey();
    SetUserRepository();
  }

  private string SetDefaultName(string name)
  {
    if (String.IsNullOrEmpty(name))
    {
      name = "NHibernateMembershipProvider";
    }
    return name;
  }

  private void SetUserRepository()
  {
    if (UserRepo == null)
    {
      UserRepo = new UserRepository();
    }
  }

  public override MembershipUser CreateUser(string username, 
    string password, string email, string passwordQuestion, 
    string passwordAnswer, bool isApproved, object providerUserKey, 
    out MembershipCreateStatus status)
  {
    var args = new ValidatePasswordEventArgs(username, password, true);
    OnValidatingPassword(args);
    if (args.Cancel)
    {
      status = MembershipCreateStatus.InvalidPassword;
      return null;
    }
    if ((RequiresUniqueEmail && (GetUserNameByEmail(email) != String.Empty)))
    {
      status = MembershipCreateStatus.DuplicateEmail;
      return null;
    }
    MembershipUser membershipUser = GetUser(username, false);
    if (membershipUser == null)
    {
      var u = new User();
      u.UserName = username;
      u.ApplicationName = _applicationName;
      u.Password = EncodePassword(password);
      u.EMail = email;
      u.PasswordQuestion = passwordQuestion;
      u.PasswordAnswer = EncodePassword(passwordAnswer);
      u.IsApproved = isApproved;
      u.Comment = String.Empty;
      u.CreationDate = DateTime.Now;
	  try{
	    UserRepo.SaveUser(u);
	    status = MembershipCreateStatus.Success;
	  }catch (Exception ex){
	    throw new MemberAccessException(
		"Error processing membership data - " + ex.Message);
      }
      return GetUser(username, false);
    }
    else
    {
      status = MembershipCreateStatus.DuplicateUserName;
    }
	  return null;
    }
  
  //omit rest of the implementation of the class
}
