<%-- 
    Document   : users
    Created on : 5-Dec-2022, 11:22:10 AM
    Author     : prabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management System</title>
        <link rel="stylesheet" href="css/styles.css" />
    </head>
    <body>
        <main class="main">
            <div class="top-bar">
                <h1>Manage Users</h1>
                <a href="?">new</a>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <c:url value="" var="edit">
                                <c:param name="edit" value="" />
                                <c:param name="email" value="${user.email}" />
                            </c:url>
                            <c:url value="" var="delete">
                                <c:param name="delete" value="" />
                                <c:param name="email" value="${user.email}" />
                            </c:url>
                            <td><a href="${edit}">edit</a></td>
                            <td><a href="${delete}">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="user" method="POST">
                <c:choose>
                    <c:when test="${user == null}">
                        <input type="hidden" name="_action" value="create" />
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="_action" value="edit" />
                    </c:otherwise>
                </c:choose>
                <label>Email <input name="email" value="${user.email}" 
                                    <c:if test="${user != null}">readonly</c:if>
                                    /></label><br />
                <label>Active <input type="checkbox" name="active" /></label><br />
                <label>First Name <input name="firstName" value="${user.firstName}" /></label><br />
                <label>Last Name <input name="lastName" value="${user.lastName}" /></label><br />
                <label>Password <input type="password" name="password" value="${user.password}" /></label><br />
                <select name="role">
                    <option disabled selected>--- choose role ---</option>
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.roleId}"
                                <c:if test="${role.roleId == user.role}">
                                    selected
                                </c:if>
                                >${role.roleName}</option>
                    </c:forEach>
                </select><br />
                <button type="submit">Submit</button>
            </form>
        </main>
    </body>
</html>