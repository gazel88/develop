<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" 
 Inherits="System.Web.Mvc.ViewPage<QuoteOMatic.Models.Quote>" %> 

<asp:Content ID="indexTitle" 
  ContentPlaceHolderID="TitleContent" runat="server">
  Quote-o-matic
</asp:Content>

<asp:Content ID="indexContent" 
  ContentPlaceHolderID="MainContent" runat="server">
<h2>Random Quote</h2>
<blockquote>
<b>&quot; <%= Html.Encode(Model.Contents) %> &quot;</b> 
<br />
<span style="padding-left: 100px">
  <%= Html.Encode(Model.Author) %> 
</span> 
</blockquote>
</asp:Content>