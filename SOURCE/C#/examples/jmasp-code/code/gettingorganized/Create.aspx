<h2>Create</h2>

<% using (Html.BeginForm()) {%>
  <%= Html.ValidationSummary(true) %> //<label id="validation.summary"/>
<fieldset>
  <legend>Fields</legend>
  <div class="editor-label">
    <%= Html.LabelFor(model => model.Completed) %>
  </div>
  <div class="editor-field">
    <%= Html.TextBoxFor(model => model.Completed) %>
    <%= Html.ValidationMessageFor(model => model.Completed) %>
  </div>
  <div class="editor-label">
    <%= Html.LabelFor(model => model.Title) %>
  </div>
  <div class="editor-field">
    <%= Html.TextBoxFor(model => model.Title) //<label id="input.textbox"/> %> 
    <%= Html.ValidationMessageFor(model => model.Title) %>
  </div>
  <p>
    <input type="submit" value="Create" />
  </p>
</fieldset>
<% } %>
