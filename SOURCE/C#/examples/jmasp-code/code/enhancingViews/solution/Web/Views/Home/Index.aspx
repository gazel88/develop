<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Home Page
</asp:Content>

<asp:Content ID="Content2" 
  ContentPlaceHolderID="MainContent" runat="server">
    <h2>Quick Links</h2>
    <ul>
    <li><%= Html.ActionLink("My Todos", "Index", "Todo") %></li>
  <li><%= Html.ActionLink("Input Your Thoughts", "Create", "Thought") %> </li>
  <li><%= Html.ActionLink("Process Thoughts into Todos", "Process", "Thought") %> </li>
  <li><%= Html.ActionLink("Review Topics", "Index", "Topic") %> </li>
    </ul> 
</asp:Content>
