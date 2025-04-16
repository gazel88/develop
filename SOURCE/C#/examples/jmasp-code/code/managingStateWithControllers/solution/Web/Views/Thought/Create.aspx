<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<GetOrganized.Web.Models.Thought>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Create
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>Create</h2>

<form action="Create" method="post" enctype="multipart/form-data">
<fieldset>
  <legend>Fields</legend>
  <p>
    <label for="Id">Id:</label>
    <%= Html.TextBox("Id") %>
    <%= Html.ValidationMessage("Id", "*") %>
  </p>
  <p>
    <label for="Name">Name:</label>
    <%= Html.TextBox("Name") %>
    <%= Html.ValidationMessage("Name", "*") %>
  </p>
  <p>
    <label for="Topic">Topic:</label>
    <%= Html.DropDownList("Topic.Id", 
      (List<SelectListItem>) ViewData["Topics"])%>
  </p>
  <p>
    Attachment: <input type="file" name="ImageAttachment" />
  </p>
  <p>
    <input type="submit" value="Create" />
  </p>
</fieldset>
</form>

    <div>
        <%= Html.ActionLink("Back to List", "Index") %>
    </div>

</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="Head" runat="server">
</asp:Content>

