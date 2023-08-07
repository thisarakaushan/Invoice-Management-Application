//package com.highradius.servlets;
//
//import com.highradius.implementation.InvoiceDaoImpl;
//import com.highradius.model.Invoice;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/EditServlet")
//public class EditServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get the invoice ID from the request parameters
//        int invoiceId = Integer.parseInt(request.getParameter("id"));
//
//        // Retrieve the invoice from the database using the ID
//        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
//        Invoice invoice = invoiceDao.getInvoiceById(invoiceId);
//
//        // Set the content type to HTML
//        response.setContentType("text/html");
//
//        // Get the PrintWriter object to write the HTML response
//        PrintWriter out = response.getWriter();
//        out.println("<h1>Update Invoice</h1>");  
//
//        // Generate the HTML form for editing the invoice
//        out.println("<html>");
//        out.println("<head>");
//        out.println("<title>Edit Invoice</title>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("<h1>Edit Invoice " + invoiceId +"</h1>");
//        out.println("<form method='post' action='EditServlet2'>");
//        out.println("<input type=\"hidden\" name=\"id\" value=\"" + invoice.getslNo() + "\">");
//        out.println("Customer Order ID: <input type=\"text\" name=\"customerOrderId\" value=\"" + invoice.getCustomerOrderId() + "\"><br>");
//        out.println("Sales Org: <input type=\"text\" name=\"salesOrg\" value=\"" + invoice.getSalesOrg() + "\"><br>");
//        out.println("Distribution Channel: <input type=\"text\" name=\"distributionChannel\" value=\"" + invoice.getDistributionChannel() + "\"><br>");
//        out.println("Division: <input type=\"text\" name=\"division\" value=\"" + invoice.getDivision() + "\"><br>");
//        out.println("Released Credit Value: <input type=\"text\" name=\"releasedCreditValue\" value=\"" + invoice.getReleasedCreditValue() + "\"><br>");
//        out.println("Purchase Order Type: <input type=\"text\" name=\"purchaseOrderType\" value=\"" + invoice.getPurchaseOrderType() + "\"><br>");
//        out.println("Company Code: <input type=\"text\" name=\"companyCode\" value=\"" + invoice.getCompanyCode() + "\"><br>");
//        out.println("Order Creation Date: <input type=\"text\" name=\"orderCreationDate\" value=\"" + invoice.getOrderCreationDate() + "\"><br>");
//        out.println("Order Creation Time: <input type=\"text\" name=\"orderCreationTime\" value=\"" + invoice.getOrderCreationTime() + "\"><br>");
//        out.println("Credit Control Area: <input type=\"text\" name=\"creditControlArea\" value=\"" + invoice.getCreditControlArea() + "\"><br>");
//        out.println("Sold To Party: <input type=\"text\" name=\"soldToParty\" value=\"" + invoice.getSoldToParty() + "\"><br>");
//        out.println("Order Amount: <input type=\"text\" name=\"orderAmount\" value=\"" + invoice.getOrderAmount() + "\"><br>");
//        out.println("Requested Delivery Date: <input type=\"text\" name=\"requestedDeliveryDate\" value=\"" + invoice.getRequestedDeliveryDate() + "\"><br>");
//        out.println("Order Currency: <input type=\"text\" name=\"orderCurrency\" value=\"" + invoice.getOrderCurrency() + "\"><br>");
//        out.println("Credit Status: <input type=\"text\" name=\"creditStatus\" value=\"" + invoice.getCreditStatus() + "\"><br>");
//        out.println("Customer Number: <input type=\"text\" name=\"customerNumber\" value=\"" + invoice.getCustomerNumber() + "\"><br>");
//        out.println("Amount in USD: <input type=\"text\" name=\"amountInUSD\" value=\"" + invoice.getAmountInUSD() + "\"><br>");
//        out.println("Unique Customer ID: <input type=\"text\" name=\"uniqueCustId\" value=\"" + invoice.getUniqueCustId() + "\"><br>");
//        out.println("<input type=\"submit\" value=\"Update\">");
//        out.println("</form>");
//        out.println("</body>");
//        out.println("</html>");
//        System.out.println("Hello");
//    }
//}
//
////import com.highradius.implementation.InvoiceDao;
////import com.highradius.implementation.InvoiceDaoImpl;
////import com.highradius.model.Invoice;
////
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////
////@WebServlet("/edit_invoice")
////public class EditServlet extends HttpServlet {
////	private static final long serialVersionUID = 1L;
////	
////    private InvoiceDao invoiceDao;
////
////    @Override
////    public void init() throws ServletException {
////        super.init();
////        // Initialize the InvoiceDao implementation (You can use dependency injection or a framework to manage this)
////        invoiceDao = new InvoiceDaoImpl();
////    }
////
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        // Retrieve the slNo parameter from the request
////        String slNo = request.getParameter("slNo");
////
////        // Fetch the invoice from the database using the InvoiceDao
////        Invoice invoice = invoiceDao.getInvoiceById(Integer.parseInt(slNo));
////
////        if (invoice != null) {
////            // Set the invoice as an attribute in the request
////            request.setAttribute("invoice", invoice);
////
////            // Forward the request to the edit form page (edit_form.html)
////            request.getRequestDispatcher("edit_form.html").forward(request, response);
////        } else {
////            // Redirect or forward to an error page if the invoice is not found
////            response.sendRedirect("error.html");
////        }
////    }
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        // Retrieve the updated invoice data from the form parameters
////        int slNo = Integer.parseInt(request.getParameter("slNo"));
////        int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
////        int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
////        // Retrieve and process other form parameters as needed
////
////        // Create an Invoice object with the updated data
////        Invoice updatedInvoice = new Invoice();
////        updatedInvoice.setslNo(slNo);
////        updatedInvoice.setCustomerOrderId(customerOrderId);
////        updatedInvoice.setSalesOrg(salesOrg);
////        // Set other properties of the updated invoice object
////
////        // Update the invoice using the InvoiceDao
////        invoiceDao.updateInvoice(updatedInvoice);
////
////        // Redirect or forward to a success page
////        response.sendRedirect("success.html");
////    }
////}
package com.highradius.servlets;

import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Update Invoice</h1>");
        String invoiceId = request.getParameter("id");
        
        System.out.println(invoiceId);
        
        int id = Integer.parseInt(invoiceId);

        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        Invoice invoice = invoiceDao.getInvoiceById(id);

        out.print("<form action='EditServlet2' method='post'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='text' name='slNo' value='" + invoice.getslNo() + "'/></td></tr>");
        out.print("<tr><td>Customer Order ID:</td><td><input type='text' name='customerOrderId' value='" + invoice.getCustomerOrderId() + "'/></td></tr>");
        out.print("<tr><td>Sales Org:</td><td><input type='text' name='salesOrg' value='" + invoice.getSalesOrg() + "'/></td></tr>");
        out.print("<tr><td>Distribution Channel:</td><td><input type='text' name='distributionChannel' value='" + invoice.getDistributionChannel() + "'/></td></tr>");
        out.print("<tr><td>Division:</td><td><input type='text' name='division' value='" + invoice.getDivision() + "'/></td></tr>");
        out.print("<tr><td>Released Credit Value:</td><td><input type='text' name='releasedCreditValue' value='" + invoice.getReleasedCreditValue() + "'/></td></tr>");
        out.print("<tr><td>Purchase Order Type:</td><td><input type='text' name='purchaseOrderType' value='" + invoice.getPurchaseOrderType() + "'/></td></tr>");
        out.print("<tr><td>Company Code:</td><td><input type='text' name='companyCode' value='" + invoice.getCompanyCode() + "'/></td></tr>");
        out.print("<tr><td>Order Creation Date:</td><td><input type='text' name='orderCreationDate' value='" + invoice.getOrderCreationDate() + "'/></td></tr>");
        out.print("<tr><td>Order Creation Time:</td><td><input type='text' name='orderCreationTime' value='" + invoice.getOrderCreationTime() + "'/></td></tr>");
        out.print("<tr><td>Credit Control Area:</td><td><input type='text' name='creditControlArea' value='" + invoice.getCreditControlArea() + "'/></td></tr>");
        out.print("<tr><td>Sold To Party:</td><td><input type='text' name='soldToParty' value='" + invoice.getSoldToParty() + "'/></td></tr>");
        out.print("<tr><td>Order Amount:</td><td><input type='text' name='orderAmount' value='" + invoice.getOrderAmount() + "'/></td></tr>");
        out.print("<tr><td>Requested Delivery Date:</td><td><input type='text' name='requestedDeliveryDate' value='" + invoice.getRequestedDeliveryDate() + "'/></td></tr>");
        out.print("<tr><td>Order Currency:</td><td><input type='text' name='orderCurrency' value='" + invoice.getOrderCurrency() + "'/></td></tr>");
        out.print("<tr><td>Credit Status:</td><td><input type='text' name='creditStatus' value='" + invoice.getCreditStatus() + "'/></td></tr>");
        out.print("<tr><td>Customer Number:</td><td><input type='text' name='customerNumber' value='" + invoice.getCustomerNumber() + "'/></td></tr>");
        out.print("<tr><td>Amount in USD:</td><td><input type='text' name='amountInUSD' value='" + invoice.getAmountInUSD() + "'/></td></tr>");
        out.print("<tr><td>Unique Customer ID:</td><td><input type='text' name='uniqueCustId' value='" + invoice.getUniqueCustId() + "'/></td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}
