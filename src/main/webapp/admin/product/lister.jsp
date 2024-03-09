<%@ page import="com.lus.dawm.atelier1.model.Produit" %>
<%@ page import="com.lus.dawm.atelier1.resources.DB" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Produit> produits = DB.getProduits(request); %>

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
    </tr>
    </thead>
    <tbody>
    <% for (Produit p : produits) { %>
    <tr>
        <td><%= p.getDesignation() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= p.getQte() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
