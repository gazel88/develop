<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Home Page
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="head" 
  runat="server">
<title>Home Page</title>
<script type="text/javascript" language="javascript">
  $(document).ready(function() {
    $("#searchThoughtsTextBox").
      autocomplete("Thought/Search", { minChars: 1 });
    $("#searchButton").click(function() {
      window.location = "Thought/FindDetails?nameOfThought=" + 
        escape($("#searchThoughtsTextBox")[0].value); 
      });
  });
</script>   
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
      <h2>Quick Links</h2>
    <ul>
    <li><%= Html.ActionLink("My Todos", "Index", "Todo") %></li>
  <li><%= Html.ActionLink("Input Your Thoughts", "Create", "Thought") %> </li>
  <li><%= Html.ActionLink("Process Thoughts into Todos", "Process", "Thought") %> </li>
  <li><%= Html.ActionLink("Review Topics", "Index", "Topic") %> </li>
    </ul> 
  
  <h2>Search Thoughts</h2>
  <input id="searchThoughtsTextBox" name="title" type="text" /> 
  <input id="searchButton" type="submit" value="Find" /> 
</asp:Content>
