/*Package com.highradius.servlets contains 2 file(s) 

b. DataLoadingServlet Class - (Contains Main method) 
   i. Method getInvoice to get all Invoices data using InvoiceDao getInvoice method
*/		  

package com.highradius.servlets;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;
import java.io.IOException;
import java.util.List;

public class DataLoadingServlet 
{
    private InvoiceDao invoiceDao;

    public void init() 
    {
        invoiceDao = new InvoiceDaoImpl();
    }

    public List<Invoice> getInvoices() throws IOException 
    {
        return invoiceDao.getInvoices();
    }
    
    // The main method is provided as an example for testing purposes.
    public static void main(String[] args) 
    {
        DataLoadingServlet servlet = new DataLoadingServlet();
        servlet.init();

        try 
        {
            List<Invoice> invoices = servlet.getInvoices();
            for (Invoice invoice : invoices) 
            {
                System.out.println(invoice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
