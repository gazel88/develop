<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<GetOrganized.Web.Models.SessionSummary>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <h2>New Things You Have to Do</h2>

    <ul>
        <%
            foreach (var todo in Model.AddedTodos)
            {%>
          <li><%=Html.Encode(todo.Title)%></li>
        
        <%
            }%>
    </ul>

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="head" runat="server">
</asp:Content>

