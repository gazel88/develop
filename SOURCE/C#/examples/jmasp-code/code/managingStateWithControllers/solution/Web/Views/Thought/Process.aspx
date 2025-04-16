<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<GetOrganized.Web.Models.Thought>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Process
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Process Thoughts</h2>
<fieldset> 
  <legend>Thought</legend>
  <p>
    <b><%= Html.Encode(Model.Name) %></b>
  </p>
  <p>   
    Topic:
    <%= Html.Encode(Model.Topic.Name) %>
   </p>
</fieldset>
<% using (Html.BeginForm("Convert", "Todo")) {%> 
<fieldset>
<legend>Actionable</legend>
<p>
  <%= Html.Hidden("Name", Model.Name) %>
  <%= Html.Hidden("Topic.Id", Model.Topic.Id) %>
  Well Defined Outcome:<%= Html.TextBox("Outcome") %>
</p>
<p>   
  <input type="submit" value="Create Action" />
 </p>
 </fieldset>
<% } %>

</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="Head" runat="server">
</asp:Content>

