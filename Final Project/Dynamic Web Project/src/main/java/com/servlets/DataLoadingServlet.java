package com.highradius.servlets;

import com.google.gson.Gson;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/data_loading")
public class DataLoadingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type to JSON
        response.setContentType("application/json");
        
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Allow GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow the Content-Type header

        // Get the PrintWriter object to write the JSON response
        PrintWriter out = response.getWriter();

        // Retrieve the list of invoices from the database
        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        List<Invoice> invoices = invoiceDao.getInvoices();

        // Convert the list of invoices to JSON using Gson
        Gson gson = new Gson();
        String json = gson.toJson(invoices);

        // Write the JSON response
        out.println(json);
    }
}



//text.html
//<!DOCTYPE html>
//<html>
//<head>
//    <title>Invoice Form</title>
//</head>
//<body>
//   <h1>Form for adding Invoice</h1>
//
//	<form method="POST" action="add_servlet">
//  		// <label for="slNo">Sl_no:</label>
//  		// <input type="number" id="slNo" name="slNo" required>
//
//	  <label for="customerOrderId">CUSTOMER_ORDER_ID:</label>
//	  <input type="number" id="customerOrderId" name="customerOrderId">
//	
//	  <label for="salesOrg">SALES_ORG:</label>
//	  <input type="number" id="salesOrg" name="salesOrg">
//	
//	  <label for="distributionChannel">DISTRIBUTION_CHANNEL:</label>
//	  <input type="text" id="distributionChannel" name="distributionChannel">
//	
//	//   <label for="division">DIVISION:</label>
//	//   <input type="text" id="division" name="division">
//	
//	//   <label for="releasedCreditValue">RELEASED_CREDIT_VALUE:</label>
//	//   <input type="number" step="0.01" id="releasedCreditValue" name="releasedCreditValue">
//	
//	//   <label for="purchaseOrderType">PURCHASE_ORDER_TYPE:</label>
//	//   <input type="text" id="purchaseOrderType" name="purchaseOrderType">
//	
//	  <label for="companyCode">COMPANY_CODE:</label>
//	  <input type="number" id="companyCode" name="companyCode">
//	
//	  <label for="orderCreationDate">ORDER_CREATION_DATE:</label>
//	  <input type="text" id="orderCreationDate" name="orderCreationDate">
//	
//	//   <label for="orderCreationTime">ORDER_CREATION_TIME:</label>
//	//   <input type="text" id="orderCreationTime" name="orderCreationTime">
//	
//	//   <label for="creditControlArea">CREDIT_CONTROL_AREA:</label>
//	//   <input type="text" id="creditControlArea" name="creditControlArea">
//	
//	//   <label for="soldToParty">SOLD_TO_PARTY:</label>
//	//   <input type="number" id="soldToParty" name="soldToParty">
//	
//	//   <label for="orderAmount">ORDER_AMOUNT:</label>
//	//   <input type="number" step="0.01" id="orderAmount" name="orderAmount">
//	
//	//   <label for="requestedDeliveryDate">REQUESTED_DELIVERY_DATE:</label>
//	//   <input type="text" id="requestedDeliveryDate" name="requestedDeliveryDate">
//	
//	  <label for="orderCurrency">ORDER_CURRENCY:</label>
//	  <input type="text" id="orderCurrency" name="orderCurrency">
//	
//	//   <label for="creditStatus">CREDIT_STATUS:</label>
//	//   <input type="text" id="creditStatus" name="creditStatus">
//	
//	  <label for="customerNumber">CUSTOMER_NUMBER:</label>
//	  <input type="number" id="customerNumber" name="customerNumber">
//	
//	  <label for="amountInUsd">AMOUNT_IN_USD:</label>
//	  <input type="number" step="0.01" id="amountInUsd" name="amountInUsd">
//	
//	//   <label for="uniqueCustId">UNIQUE_CUST_ID:</label>
//	//   <input type="number" id="uniqueCustId" name="uniqueCustId">
//	
//	  <input type="submit" value="Submit">
//	</form>
//
//	<h1>Edit Invoice</h1>
//	    <form method="get" action="EditServlet">
//	        slNo: <input type="text" name="id"><br>
//	        <input type="submit" value="Edit">
//	    </form>
//
//</body>
//</html>

