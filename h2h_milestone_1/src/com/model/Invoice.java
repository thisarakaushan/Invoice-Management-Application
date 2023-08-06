/*Package com.highradius.model contains 1 file(s) 
a. Invoice 
   i. It is a class 
   ii. Parameterized Constructor 
   iii. Method getter(s) and setter(s)
*/

package com.highradius.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice 
{
	private int id;

    public Invoice(int id) 
    {
        this.id = id;
    }

    public int getInvoiceNumber() 
    {
        return id;
    }

    public void setInvoiceNumber(int id) 
    {
        this.id = id;
    }

    // Example of using ArrayList to store multiple instances of Invoice
    public static void main(String[] args) 
    {
        List<Invoice> invoiceList = new ArrayList<>();

        Invoice invoice1 = new Invoice(1);
        invoiceList.add(invoice1);

        Invoice invoice2 = new Invoice(2);
        invoiceList.add(invoice2);

        for (Invoice invoice : invoiceList) 
        {
            System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
            System.out.println();
        }
    }
}

