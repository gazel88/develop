<div class="editor-label">
	<%= Html.LabelFor(model => model.Topic) %>
</div>
<div class="editor-field">
	<%= Html.DropDownList("Topic.Id", 
		(List<SelectListItem>) ViewData["Topics"])%>
</div>