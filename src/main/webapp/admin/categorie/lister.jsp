<%@ page import="com.lus.dawm.atelier1.Service.CategorieService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lus.dawm.atelier1.model.Categorie" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.rmi.RemoteException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    CategorieService categorieService;
    List<Categorie> categories;

    try {
        categorieService = new CategorieService();
        categories = categorieService.getAllCategories();
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
%>
<c:set var="categories" value="<%= categories%>" />

<html>
<head>
    <title>Categorie List</title>
</head>
<body>

<table class="categorie-table">
    <thead>
    <tr>
        <th>Designation</th>
        <th>Description</th>
        <th>Categorie Parent</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${categories}">
        <tr>
            <td>
                <a href="admin/product/lister.jsp?categorieId=<c:out value="${c.id}" />">
                    <c:out value="${c.designation}" />
                </a>
            </td>
            <td><c:out value="${c.description}" /></td>
            <td>
                <c:choose>
                    <c:when test="${c.parentCategorie ne null}">
                        <c:out value="${c.parentCategorie.designation}" />
                    </c:when>
                    <c:otherwise>
                        No Parent Category
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
