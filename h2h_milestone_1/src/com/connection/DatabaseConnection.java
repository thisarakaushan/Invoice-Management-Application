/*Package com.highradius.connection contains java file named as DatabaseConnection with following methods and properties 
   a. List of Invoices 
   b. Method getInvoices to return list of invoices 
   c. Method addInvoice to Add Invoice to the list
*/

package com.highradius.connection;

import com.highradius.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection 
{
		List<Invoice> invoices = new ArrayList<>();

	    public List<Invoice> getInvoices() 
	    {
	        return invoices;
	    }

	    public void addInvoice(Invoice invoice) 
	    {
	        invoices.add(invoice);
	    }
}

