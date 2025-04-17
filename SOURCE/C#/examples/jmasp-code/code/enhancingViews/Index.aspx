<asp:Content ID="Content2" 
  ContentPlaceHolderID="MainContent" runat="server">
    <h2>Quick Links</h2>
    <ul>
    <li><%= Html.ActionLink("My Todos", //<label id="use.action.link"/>
        "Index", "Todo") %></li>
  <li><%= Html.ActionLink("Input Your Thoughts",
      "Create", "Thought") %> </li>
  <li><%= Html.ActionLink("Process Thoughts into Todos", 
      "Process", "Thought") %> </li>
  <li><%= Html.ActionLink("Review Topics", 
      "Index", "Topic") %> </li>
    </ul> 
</asp:Content>