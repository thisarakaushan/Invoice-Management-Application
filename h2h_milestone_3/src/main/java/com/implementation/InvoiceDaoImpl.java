package com.highradius.implementation;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao 
{
	 private static final String GET__INVOICES = "SELECT * FROM h2h_oap";
	 private static final String ADD_INVOICE = "INSERT INTO h2h_oap (CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION, RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 private static final String UPDATE_INVOICE = "UPDATE h2h_oap SET CUSTOMER_ORDER_ID=?, SALES_ORG=?, DISTRIBUTION_CHANNEL=?, DIVISION=?, RELEASED_CREDIT_VALUE=?, PURCHASE_ORDER_TYPE=?, COMPANY_CODE=?, ORDER_CREATION_DATE=?, ORDER_CREATION_TIME=?, CREDIT_CONTROL_AREA=?, SOLD_TO_PARTY=?, ORDER_AMOUNT=?, REQUESTED_DELIVERY_DATE=?, ORDER_CURRENCY=?, CREDIT_STATUS=?, CUSTOMER_NUMBER=?, AMOUNT_IN_USD=?, UNIQUE_CUST_ID=? WHERE Sl_no=?";
	 private static final String DELETE_INVOICE = "DELETE FROM h2h_oap WHERE Sl_no=?";
	
	@Override
	public void addInvoice(Invoice invoice) 
	{
		//Implementation		
	}

	@Override
	public List<Invoice> getInvoices() 
	{
		return null;
	}
	
	@Override
    public void deleteInvoice(int id) 
	{
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_INVOICE)) 
        {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void updateInvoice(Invoice invoice) 
	{
		try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_INVOICE)) 
		{

			stmt.setInt(1, invoice.getCustomerOrderId());
			stmt.setInt(2, invoice.getSalesOrg());
			stmt.setString(3, invoice.getDistributionChannel());
			stmt.setString(4, invoice.getDivision());
			stmt.setDouble(5, invoice.getReleasedCreditValue());
			stmt.setString(6, invoice.getPurchaseOrderType());
			stmt.setInt(7, invoice.getCompanyCode());
			stmt.setString(8, invoice.getOrderCreationDate());
			stmt.setString(9, invoice.getOrderCreationTime());
			stmt.setString(10, invoice.getCreditControlArea());
			stmt.setInt(11, invoice.getSoldToParty());
			stmt.setDouble(12, invoice.getOrderAmount());
			stmt.setString(13, invoice.getRequestedDeliveryDate());
			stmt.setString(14, invoice.getOrderCurrency());
			stmt.setString(15, invoice.getCreditStatus());
			stmt.setInt(16, invoice.getCustomerNumber());
			stmt.setDouble(17, invoice.getAmountInUSD());
            stmt.setLong(18, invoice.getUniqueCustId());
            stmt.setInt(19, invoice.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }	
	}

	public static String getGetInvoices() {
		return GET__INVOICES;
	}

	public static String getAddInvoice() {
		return ADD_INVOICE;
	}

}
