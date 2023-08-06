/*5. Package com.highradius.implementation contains  2 files 

b. InvoiceDaoImpl 
   i. It is a class implements InvoiceDao 
   ii. Method insertInvoice should call DatabaseConnection addInvoice method 
   iii. Method getInvoice should call DatabaseConnection getInvoices method*/

package com.highradius.implementation;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao 
{
    private DatabaseConnection databaseConnection;

    public InvoiceDaoImpl() 
    {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public List<Invoice> getInvoices() 
    {
        return databaseConnection.getInvoices();
    }

    @Override
    public void insertInvoice(Invoice invoice) 
    {
        databaseConnection.addInvoice(invoice);
    }

    @Override
    public void updateInvoice(int id, Invoice invoice) 
    {
    	
    }

    @Override
    public void deleteInvoice(int id) 
    {
       
    }
}
