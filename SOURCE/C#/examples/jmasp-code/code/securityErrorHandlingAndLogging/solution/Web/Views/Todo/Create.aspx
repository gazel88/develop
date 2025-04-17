<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<GetOrganized.Web.Models.Todo>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Create
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>Create Todo</h2>

        <%= Html.ValidationSummary(true) %>

        <% Html.RenderPartial("CreateElements"); %> 

    <div>
        <%= Html.ActionLink("Back to List", "Index") %>
    </div>

</asp:Content>

