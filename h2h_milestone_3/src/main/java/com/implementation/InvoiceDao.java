package com.highradius.implementation;

import com.highradius.model.Invoice;	
import java.util.List;

public interface InvoiceDao 
{	
	List<Invoice> getInvoices();
	void updateInvoice(Invoice invoice);
	void deleteInvoice(int id);
	void addInvoice(Invoice invoice);
}
