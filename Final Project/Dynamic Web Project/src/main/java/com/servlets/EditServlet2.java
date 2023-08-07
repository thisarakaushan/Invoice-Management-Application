package com.highradius.servlets;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import java.io.IOException;  
import java.io.PrintWriter;  


@WebServlet("/EditServlet2")  
public class EditServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");  
	    PrintWriter out=response.getWriter(); 
	    
		  int slNo = Integer.parseInt(request.getParameter("slNo"));
	  	  int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
	  	  int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
	  	  String distributionChannel = request.getParameter("distributionChannel");
	  	  String division = request.getParameter("division");
	  	  double releasedCreditValue = Double.parseDouble(request.getParameter("releasedCreditValue"));
	  	  String purchaseOrderType = request.getParameter("purchaseOrderType");
	  	  int companyCode = Integer.parseInt(request.getParameter("companyCode"));
	  	  String orderCreationDate = request.getParameter("orderCreationDate");
	  	  String orderCreationTime = request.getParameter("orderCreationTime");
	  	  String creditControlArea = request.getParameter("creditControlArea");
	  	  int soldToParty = Integer.parseInt(request.getParameter("soldToParty"));
	  	  double orderAmount = Double.parseDouble(request.getParameter("orderAmount"));
	  	  String requestedDeliveryDate = request.getParameter("requestedDeliveryDate");
	  	  String orderCurrency = request.getParameter("orderCurrency");
	  	  String creditStatus = request.getParameter("creditStatus");
	  	  int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
	  	  double amountInUsd = Double.parseDouble(request.getParameter("amountInUsd"));
	  	  long uniqueCustId = Long.parseLong(request.getParameter("uniqueCustId"));
	  	  
	  	Invoice invoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division, releasedCreditValue, purchaseOrderType,
			    companyCode, orderCreationDate, orderCreationTime, creditControlArea, soldToParty, orderAmount, requestedDeliveryDate,
			    orderCurrency, creditStatus, customerNumber, amountInUsd, uniqueCustId);
	  	
	  	int status = InvoiceDaoImpl.updateInvoice(invoice);
	       
	     if(status>0){  
	         response.sendRedirect("DataLoadingServlet\"");  
	     }else{  
	         out.println("Sorry! unable to update record");  
	     }  
	        out.close();  
	}
}
