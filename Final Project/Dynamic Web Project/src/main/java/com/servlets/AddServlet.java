package com.highradius.servlets;

import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_servlet")
public class AddServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
    	if(
    		request.getMethod().equals("OPTIONS")) {
    	    response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    	    response.setHeader("Access-Control-Allow-Methods", "POST");
    	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	    response.setStatus(HttpServletResponse.SC_OK);
    	    System.out.println("Hello1!");
    	    return;
    }	

    	  //int slNo = Integer.parseInt(request.getParameter("slNo"));
    	  int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
    	  int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
    	  String distributionChannel = request.getParameter("distributionChannel");
    	  //String division = request.getParameter("division");
    	  //double releasedCreditValue = Double.parseDouble(request.getParameter("releasedCreditValue"));
    	  //String purchaseOrderType = request.getParameter("purchaseOrderType");
    	  int companyCode = Integer.parseInt(request.getParameter("companyCode"));
    	  String orderCreationDate = request.getParameter("orderCreationDate");
    	  //String orderCreationTime = request.getParameter("orderCreationTime");
    	  //String creditControlArea = request.getParameter("creditControlArea");
    	  //int soldToParty = Integer.parseInt(request.getParameter("soldToParty"));
    	  //double orderAmount = Double.parseDouble(request.getParameter("orderAmount"));
    	  //String requestedDeliveryDate = request.getParameter("requestedDeliveryDate");
    	  String orderCurrency = request.getParameter("orderCurrency");
    	  //String creditStatus = request.getParameter("creditStatus");
    	  int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
    	  double amountInUsd = Double.parseDouble(request.getParameter("amountInUsd"));
    	  //long uniqueCustId = Long.parseLong(request.getParameter("uniqueCustId"));
    	  
    	  Invoice invoice = new Invoice();

		  invoice.setCustomerOrderId(customerOrderId);
		  invoice.setSalesOrg(salesOrg);
		  invoice.setDistributionChannel(distributionChannel);
		  invoice.setCompanyCode(companyCode);
		  invoice.setOrderCreationDate(orderCreationDate);
		  invoice.setOrderCurrency(orderCurrency);
		  invoice.setAmountInUSD(amountInUsd);
		  invoice.setCustomerNumber(customerNumber);
	      //invoice.setUniqueCustId(uniqueCustId);;
		  
		  System.out.println(invoice.getCustomerOrderId());
		  //System.out.println(invoice.getCustomerOrderId());

          InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
          invoiceDao.addInvoice(invoice);

          response.sendRedirect("text.html");
          System.out.println("Database updated");
          
          String classpath = System.getProperty("java.class.path");
          System.out.println("Classpath: " + classpath);  
          
          invoiceDao.getInvoices();
          
    	}
}







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
//
//@WebServlet("/add_servlet")
//public class AddServlet extends HttpServlet 
//{
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//    {
//    	  int slNo = Integer.parseInt(request.getParameter("slNo"));
//    	  int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
//    	  int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
//    	  String distributionChannel = request.getParameter("distributionChannel");
//    	  String division = request.getParameter("division");
//    	  double releasedCreditValue = Double.parseDouble(request.getParameter("releasedCreditValue"));
//    	  String purchaseOrderType = request.getParameter("purchaseOrderType");
//    	  int companyCode = Integer.parseInt(request.getParameter("companyCode"));
//    	  String orderCreationDate = request.getParameter("orderCreationDate");
//    	  String orderCreationTime = request.getParameter("orderCreationTime");
//    	  String creditControlArea = request.getParameter("creditControlArea");
//    	  int soldToParty = Integer.parseInt(request.getParameter("soldToParty"));
//    	  double orderAmount = Double.parseDouble(request.getParameter("orderAmount"));
//    	  String requestedDeliveryDate = request.getParameter("requestedDeliveryDate");
//    	  String orderCurrency = request.getParameter("orderCurrency");
//    	  String creditStatus = request.getParameter("creditStatus");
//    	  int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
//    	  double amountInUsd = Double.parseDouble(request.getParameter("amountInUsd"));
//    	  long uniqueCustId = Long.parseLong(request.getParameter("uniqueCustId"));
//
//    	  Invoice invoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division, releasedCreditValue, purchaseOrderType,
//    			    companyCode, orderCreationDate, orderCreationTime, creditControlArea, soldToParty, orderAmount, requestedDeliveryDate,
//    			    orderCurrency, creditStatus, customerNumber, amountInUsd, uniqueCustId);
//
//
//          InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
//          invoiceDao.addInvoice(invoice);
//
//          response.sendRedirect("text.html");
//          System.out.println("Database updated");
//          
//          String classpath = System.getProperty("java.class.path");
//          System.out.println("Classpath: " + classpath);          
//          
//          invoiceDao.getInvoices();
//    	}   
//}
