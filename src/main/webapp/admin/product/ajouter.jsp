<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="ajouterStyle.css">
    <title>Ajouter un produit</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/ajouterProduit" method="post" class="product-form">

    <label for="produitId">Product ID:</label>
    <input type="number" name="produitId" id="produitId" required/>

    <label for="designation">Designation:</label>
    <input type="text" name="designation" id="designation" required/>

    <label for="description">Description:</label>
    <input type="text" name="description" id="description" required/>

    <label for="qte">Quantity:</label>
    <input type="number" name="qte" id="qte" required/>

    <label for="prix">Prix:</label>
    <input type="number" name="prix" id="prix" required/>

    <input type="submit" name="ajoutBtn" value="Ajouter">

</form>

</body>
</html>
