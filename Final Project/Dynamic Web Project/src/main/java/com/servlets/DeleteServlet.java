package com.highradius.servlets;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_invoice")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the invoice ID from the request parameters
        int invoiceId = Integer.parseInt(request.getParameter("id"));

        // Create an instance of the InvoiceDao
        InvoiceDao invoiceDao = new InvoiceDaoImpl();

        // Delete the invoice from the database using the ID
        invoiceDao.deleteInvoice(invoiceId);

        // Redirect to the invoice list page
        response.sendRedirect("invoice_list");
    }
}
