/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
[Test]
public void Should_Be_Logged_In_To_Do_Anything_With_Todos()
{
  typeof (TodoController).GetCustomAttributes(false).
    ToList().ForEach(Console.WriteLine); 
}     
//Output is:
//System.Web.Mvc.AuthorizeAttribute
[Test]
public void Should_Be_Logged_In_To_Do_Anything_With_Todos()
{
  Assert.IsTrue(typeof (TodoController).
    GetCustomAttributes(false).
    Any(o => o.GetType() == typeof (AuthorizeAttribute)));
}
[Test]
public void Should_Be_Logged_In_To_Do_Anything_With_Todos()
{
  AssertIsAuthorized(typeof (TodoController));
}

private static void AssertIsAuthorized(ICustomAttributeProvider type)
{
  Assert.IsTrue(type.GetCustomAttributes(false).
    Any(o => o.GetType() == typeof (AuthorizeAttribute)));
}
//Inside TodoControllerTest.cs
[Test]
public void Should_Be_Logged_In_To_Do_Anything_With_Todos()
{
  TestHelper.AssertIsAuthorized(typeof (TodoController)); 
}

//Inside TestHelper.cs
namespace Test.Unit.Helper
{
 public class TestHelper
 {
  public static void AssertIsAuthorized(ICustomAttributeProvider type)
  {
  Assert.IsTrue(type.GetCustomAttributes(false).
  Any(o => o.GetType() == typeof (AuthorizeAttribute)));
  }
 }
}
[Test]
public void Should_Be_Logged_In_To_Create()
{
  TestHelper.AssertIsAuthorized(
    typeof(TodoController), "Create");
  TestHelper.AssertIsAuthorized(
    typeof(TodoController), "Create",  typeof(Todo));
}

//...inside TodoController.cs we add [Authorize] to both methods

[Authorize]
public ActionResult Create() { // omitted }

[Authorize]
[HttpPost]
public ActionResult Create(Todo todo)  { // omitted }
public static void AssertIsAuthorized(Type type, string action, 
    params Type[] parameters)
{
  AssertIsAuthorized(type.GetMethod(action, parameters));
}
[Test]
public void  Should_Display_A_List_Of_Todo_Items_And_Logged_In_Users_Name()
{
  var todoController = new TodoController();

  todoController.HttpContext.User = 
    new GenericPrincipal(new GenericIdentity("Jonathan"), null); 

  var viewResult = (ViewResult) todoController.Index();
  
  Assert.AreEqual(Todo.ThingsToBeDone, viewResult.ViewData.Model );
  Assert.AreEqual("Jonathan", viewResult.ViewData["UserName"]);
}
[Test]
public void  Should_Display_A_List_Of_Todo_Items_And_Logged_In_Users_Name()
{
    var todoController = new TodoController();
    var builder = new TestControllerBuilder();
    builder.InitializeController(todoController); 
    builder.HttpContext.User = 
  new GenericPrincipal(
    new GenericIdentity("Jonathan"), null);
    
	var viewResult = (ViewResult) 
	  todoController.Index().AssertViewRendered();
	
    Assert.AreEqual("Jonathan", viewResult.ViewData["UserName"]);
	Assert.AreEqual(Todo.ThingsToBeDone, viewResult.ViewData.Model );
}
[Test]
public void Should_Add_Todo_Item()
{
  var todo = new Todo 
    {Title = "Learn MVC Controllers"};
  
  var sessionSummary = new SessionSummary();
  sessionSummary.AddedTodos.Add(todo);

  var todoController = new TodoController();
  var builder = new TestControllerBuilder();
  builder.InitializeController(todoController);
  
  todoController.Create(todo).
    AssertActionRedirect().ToAction("Index");
    
  Assert.Contains(todo, Todo.ThingsToBeDone);
  Assert.AreEqual(sessionSummary, 
  todoController.Session["SessionSummary"]);
}
[SetUp]
public void setup()
{
  // omitted other setup information
  var routes = RouteTable.Routes;
  routes.Clear();
  RouteDefinitions.AddRoutes(routes);
}
[Test]
public void Should_Route_To_Edit_Page()
{
  "~/Todo/Edit".
    ShouldMapTo<TodoController>(x => x.Edit(null));
}
