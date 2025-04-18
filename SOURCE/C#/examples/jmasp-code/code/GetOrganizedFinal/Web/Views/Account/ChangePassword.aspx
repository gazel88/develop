﻿<%@ Page Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage" %>

<asp:Content ID="changePasswordHead" ContentPlaceHolderID="head" runat="server">
    <title>Change Password</title>
</asp:Content>

<asp:Content ID="changePasswordContent" ContentPlaceHolderID="MainContent" runat="server">
    <h2>Change Password</h2>
    <p>
        Use the form below to change your password. 
    </p>
    <p>
        New passwords are required to be a minimum of <%=Html.Encode(ViewData["PasswordLength"])%> characters in length.
    </p>
    <%=Html.ValidationSummary()%>

    <%
        using (Html.BeginForm())
        {%>
        <div>
            <fieldset>
                <legend>Account Information</legend>
                <p>
                    <label for="currentPassword">Current password:</label>
                    <%=Html.Password("currentPassword")%>
                    <%=Html.ValidationMessage("currentPassword")%>
                </p>
                <p>
                    <label for="newPassword">New password:</label>
                    <%=Html.Password("newPassword")%>
                    <%=Html.ValidationMessage("newPassword")%>
                </p>
                <p>
                    <label for="confirmPassword">Confirm new password:</label>
                    <%=Html.Password("confirmPassword")%>
                    <%=Html.ValidationMessage("confirmPassword")%>
                </p>
                <p>
                    <input type="submit" value="Change Password" />
                </p>
            </fieldset>
        </div>
    <%
        }%>
</asp:Content>
