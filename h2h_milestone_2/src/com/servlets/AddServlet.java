/*Package com.highradius.servlets contains 2 file(s) 

a. AddServlet Class - (Contains Main method) 
   i. Method addInvoice to Add Invoice data using InvoiceDao insertInvoice method 
*/		  

package com.highradius.servlets;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;
import java.io.IOException;

public class AddServlet 
{
    private InvoiceDao invoiceDao;

    public void init() 
    {
        invoiceDao = new InvoiceDaoImpl();
    }

    public void addInvoice(Invoice invoice) throws IOException 
    {
        invoiceDao.insertInvoice(invoice);
    }
}
