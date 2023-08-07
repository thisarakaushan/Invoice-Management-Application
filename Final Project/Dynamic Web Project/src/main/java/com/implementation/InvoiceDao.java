package com.highradius.implementation;

import com.highradius.model.Invoice;	
import java.util.List;

public interface InvoiceDao 
{	
	List<Invoice> getInvoices();
	Invoice getInvoiceById(int id);
	void deleteInvoice(int id);
	void addInvoice(Invoice invoice);
//	public int updateInvoice(Invoice invoice);
}
