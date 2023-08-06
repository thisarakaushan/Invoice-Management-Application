/*5. Package com.highradius.implementation contains  2 files 
a. InvoiceDao 
   i. It is an interface 
   ii. Method getInvoice return list of invoices 
   iii. Method insertInvoice takes invoice object 
   Iv. Method updateInvoice takes id, and invoice object to update 
   v. Method deleteInvoice takes id to be deleted for invoice */

package com.highradius.implementation;

import com.highradius.model.Invoice;

import java.util.List;

public interface InvoiceDao 
{
	 List<Invoice> getInvoices();
	 void insertInvoice(Invoice invoice);
	 void updateInvoice(int id, Invoice invoice);
	 void deleteInvoice(int id);
}