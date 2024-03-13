<%@ page import="com.lus.dawm.atelier1.model.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lus.dawm.atelier1.Service.ProduitService" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    ProduitService produitService = null;
    List<Produit> produits = new ArrayList<>();

    try {
        produitService = new ProduitService();
        produits = produitService.getAllProduits();
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
%>

<c:set var="produits" value="<%= produits %>" />

<html>
<head>
    <title>Products List</title>
    <link rel="stylesheet" href="listerStyle.css">
</head>
<body>

<table class="product-table">
    <thead>
    <tr>
        <th>Designation</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${produits}">
        <tr>
            <td><c:out value="${p.designation}" /></td>
            <td><c:out value="${p.description}" /></td>
            <td><c:out value="${p.qte}" /></td>
            <td><c:out value="${p.prix}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
