<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" 
Inherits= "System.Web.Mvc.ViewPage<IEnumerable<GetOrganized.Web.Models.Todo>>" %>
<%@ Import Namespace="MvcContrib.UI.Grid" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" 
runat="server">
	Index
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="head" 
  runat="server">
  <title>My Todos</title>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" 
  runat="server">
  <h2><%= ViewData["UserName"] %>'s Todos</h2>

<%= Html.Grid(Model).Columns(column => {  
    column.For(  
         todo =>
         Html.ActionLink("Delete", "Delete", new {todo.Title})). 
         Named("Delete").DoNotEncode(); 
     column.For(
        todo =>
        Html.ActionLink("Edit", "Edit", new { todo.Title })).
        Named("Edit").DoNotEncode();
     column.For(todo => todo.Title);
      })
    .Attributes(style => "text-align: center;") 
  .Empty("You have completed everything. Congrats!") 
 %>
   
  <p>
      <%= Html.ActionLink("Create New", "Create") %>
  </p>
</asp:Content>