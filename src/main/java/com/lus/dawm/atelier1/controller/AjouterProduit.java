package com.lus.dawm.atelier1.controller;

import com.lus.dawm.atelier1.resources.DB;
import com.lus.dawm.atelier1.model.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ajouterProduit")
public class AjouterProduit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("produitId"));
            String designation = req.getParameter("designation");
            String description = req.getParameter("description");
            int qte = Integer.parseInt(req.getParameter("qte"));
            double prix = Double.parseDouble(req.getParameter("prix"));

            Produit produit = new Produit(id, designation, description, qte, prix ,null, null);
            DB.addProduits(req ,produit);

            resp.sendRedirect("admin/product/lister.jsp");
        }catch (NumberFormatException | NullPointerException ignored){

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
