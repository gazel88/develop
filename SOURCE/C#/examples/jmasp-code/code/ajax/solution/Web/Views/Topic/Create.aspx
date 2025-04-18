﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<GetOrganized.Web.Models.Topic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Create
</asp:Content>
	
<asp:Content ID="Content3" ContentPlaceHolderID="Head" runat="server">

<script type="text/javascript"> 
  $(document).ready(function()
  {
    $('#Color').ColorPicker(
    {
      onSubmit: function(hsb, hex, rgb) 
      {
        $('#Color').val(hex);
      },
      onBeforeShow: function() 
      {
        $(this).ColorPickerSetColor(this.value);
      }
    })
    .bind('keyup', function() 
    {
    $(this).ColorPickerSetColor(this.value);
    });
  });
  </script>
</asp:Content>	

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <h2>Create</h2>

    <% using (Html.BeginForm()) {%>
        <%= Html.ValidationSummary(true) %>

        <fieldset>
            <legend>Fields</legend>
            
            <div class="editor-label">
                <%= Html.LabelFor(model => model.Id) %>
            </div>
            <div class="editor-field">
                <%= Html.TextBoxFor(model => model.Id) %>
                <%= Html.ValidationMessageFor(model => model.Id) %>
            </div>
            
            <div class="editor-label">
                <%= Html.LabelFor(model => model.Name) %>
            </div>
            <div class="editor-field">
                <%= Html.TextBoxFor(model => model.Name) %>
                <%= Html.ValidationMessageFor(model => model.Name) %>
            </div>
            
                <div class="editor-label">
                <%= Html.LabelFor(model => model.Color) %>
              </div>
              <div class="editor-field">  
                <%= Html.TextBoxFor(model => model.Color) %>
                <%= Html.ValidationMessageFor(model => model.Color) %>
            </div>
            
            <p>
                <input type="submit" value="Create" />
            </p>
        </fieldset>

    <% } %>

    <div>
        <%= Html.ActionLink("Back to List", "Index") %>
    </div>

</asp:Content>



