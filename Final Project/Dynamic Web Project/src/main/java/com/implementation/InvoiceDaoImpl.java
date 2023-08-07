package com.highradius.implementation;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao 
{
    private static final String GET_INVOICES = "SELECT * FROM h2h_oap LIMIT 30";
//  private static final String ADD_INVOICE = "INSERT INTO h2h_oap (Sl_no,CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION, RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_INVOICE = "INSERT INTO h2h_oap (CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CURRENCY, CUSTOMER_NUMBER, AMOUNT_IN_USD) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
    private static final String UPDATE_INVOICE = "UPDATE h2h_oap SET Sl_no=?,CUSTOMER_ORDER_ID=?, SALES_ORG=?, DISTRIBUTION_CHANNEL=?, DIVISION=?, RELEASED_CREDIT_VALUE=?, PURCHASE_ORDER_TYPE=?, COMPANY_CODE=?, ORDER_CREATION_DATE=?, ORDER_CREATION_TIME=?, CREDIT_CONTROL_AREA=?, SOLD_TO_PARTY=?, ORDER_AMOUNT=?, REQUESTED_DELIVERY_DATE=?, ORDER_CURRENCY=?, CREDIT_STATUS=?, CUSTOMER_NUMBER=?, AMOUNT_IN_USD=?, UNIQUE_CUST_ID=? WHERE Sl_no=?";
    private static final String DELETE_INVOICE = "DELETE FROM h2h_oap WHERE Sl_no=?";

    @Override
    public void addInvoice(Invoice invoice) 
    {
        try (Connection conn = DatabaseConnection.getConnection();
        	PreparedStatement stmt = conn.prepareStatement(ADD_INVOICE)) 
        {
//            stmt.setInt(1, invoice.getslNo());
//            stmt.setInt(2, invoice.getCustomerOrderId());
//            stmt.setInt(3, invoice.getSalesOrg());
//            stmt.setString(4, invoice.getDistributionChannel());
//            stmt.setString(5, invoice.getDivision());
//            stmt.setDouble(6, invoice.getReleasedCreditValue());
//            stmt.setString(7, invoice.getPurchaseOrderType());
//            stmt.setInt(8, invoice.getCompanyCode());
//            stmt.setString(9, invoice.getOrderCreationDate());
//            stmt.setString(10, invoice.getOrderCreationTime());
//            stmt.setString(11, invoice.getCreditControlArea());
//            stmt.setInt(12, invoice.getSoldToParty());
//            stmt.setDouble(13, invoice.getOrderAmount());
//            stmt.setString(14, invoice.getRequestedDeliveryDate());
//            stmt.setString(15, invoice.getOrderCurrency());
//            stmt.setString(16, invoice.getCreditStatus());
//            stmt.setInt(17, invoice.getCustomerNumber());
//            stmt.setDouble(18, invoice.getAmountInUSD());
//            stmt.setLong(19, invoice.getUniqueCustId());

          stmt.setInt(1, invoice.getCustomerOrderId());
          stmt.setInt(2, invoice.getSalesOrg());
          stmt.setString(3, invoice.getDistributionChannel());
          stmt.setInt(4, invoice.getCompanyCode());
          stmt.setString(5, invoice.getOrderCreationDate());
          stmt.setString(6, invoice.getOrderCurrency());
          stmt.setInt(7, invoice.getCustomerNumber());
          stmt.setDouble(8, invoice.getAmountInUSD());
          stmt.executeUpdate();
        
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    

    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_INVOICES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setslNo(rs.getInt("Sl_no"));
                invoice.setCustomerOrderId(rs.getInt("CUSTOMER_ORDER_ID"));
                invoice.setSalesOrg(rs.getInt("SALES_ORG"));
                invoice.setDistributionChannel(rs.getString("DISTRIBUTION_CHANNEL"));
                invoice.setDivision(rs.getString("DIVISION"));
                invoice.setReleasedCreditValue(rs.getDouble("RELEASED_CREDIT_VALUE"));
                invoice.setPurchaseOrderType(rs.getString("PURCHASE_ORDER_TYPE"));
                invoice.setCompanyCode(rs.getInt("COMPANY_CODE"));
                invoice.setOrderCreationDate(rs.getString("ORDER_CREATION_DATE"));
                invoice.setOrderCreationTime(rs.getString("ORDER_CREATION_TIME"));
                invoice.setCreditControlArea(rs.getString("CREDIT_CONTROL_AREA"));
                invoice.setSoldToParty(rs.getInt("SOLD_TO_PARTY"));
                invoice.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
                invoice.setRequestedDeliveryDate(rs.getString("REQUESTED_DELIVERY_DATE"));
                invoice.setOrderCurrency(rs.getString("ORDER_CURRENCY"));
                invoice.setCreditStatus(rs.getString("CREDIT_STATUS"));
                invoice.setCustomerNumber(rs.getInt("CUSTOMER_NUMBER"));
                invoice.setAmountInUSD(rs.getDouble("AMOUNT_IN_USD"));
                invoice.setUniqueCustId(rs.getLong("UNIQUE_CUST_ID"));

                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public void deleteInvoice(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_INVOICE)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int updateInvoice(Invoice invoice) {
    	int status = 0;
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_INVOICE)) {

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
//            stmt.setInt(19, invoice.getId());

            stmt.executeUpdate();
        }catch(Exception ex){ex.printStackTrace();}  
        
        return status;  
    }  
    
    @Override
    public Invoice getInvoiceById(int invoiceId) {
        Invoice invoice = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_INVOICES)) {
            stmt.setInt(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    invoice = new Invoice();
                    invoice.setslNo(rs.getInt("Sl_no"));
                    invoice.setCustomerOrderId(rs.getInt("CUSTOMER_ORDER_ID"));
                    invoice.setSalesOrg(rs.getInt("SALES_ORG"));
                    invoice.setDistributionChannel(rs.getString("DISTRIBUTION_CHANNEL"));
                    invoice.setDivision(rs.getString("DIVISION"));
                    invoice.setReleasedCreditValue(rs.getDouble("RELEASED_CREDIT_VALUE"));
                    invoice.setPurchaseOrderType(rs.getString("PURCHASE_ORDER_TYPE"));
                    invoice.setCompanyCode(rs.getInt("COMPANY_CODE"));
                    invoice.setOrderCreationDate(rs.getString("ORDER_CREATION_DATE"));
                    invoice.setOrderCreationTime(rs.getString("ORDER_CREATION_TIME"));
                    invoice.setCreditControlArea(rs.getString("CREDIT_CONTROL_AREA"));
                    invoice.setSoldToParty(rs.getInt("SOLD_TO_PARTY"));
                    invoice.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
                    invoice.setRequestedDeliveryDate(rs.getString("REQUESTED_DELIVERY_DATE"));
                    invoice.setOrderCurrency(rs.getString("ORDER_CURRENCY"));
                    invoice.setCreditStatus(rs.getString("CREDIT_STATUS"));
                    invoice.setCustomerNumber(rs.getInt("CUSTOMER_NUMBER"));
                    invoice.setAmountInUSD(rs.getDouble("AMOUNT_IN_USD"));
                    invoice.setUniqueCustId(rs.getLong("UNIQUE_CUST_ID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }
}


//
//package com.highradius.implementation;
//
//import com.highradius.connection.DatabaseConnection;
//import com.highradius.model.Invoice;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class InvoiceDaoImpl implements InvoiceDao 
//{
//    private static final String GET_INVOICES = "SELECT * FROM h2h_oap LIMIT 30";
//    private static final String ADD_INVOICE = "INSERT INTO h2h_oap (Sl_no,CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION, RELEASED_CREDIT_VALUE, PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, AMOUNT_IN_USD, UNIQUE_CUST_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    private static final String UPDATE_INVOICE = "UPDATE h2h_oap SET Sl_no=?,CUSTOMER_ORDER_ID=?, SALES_ORG=?, DISTRIBUTION_CHANNEL=?, DIVISION=?, RELEASED_CREDIT_VALUE=?, PURCHASE_ORDER_TYPE=?, COMPANY_CODE=?, ORDER_CREATION_DATE=?, ORDER_CREATION_TIME=?, CREDIT_CONTROL_AREA=?, SOLD_TO_PARTY=?, ORDER_AMOUNT=?, REQUESTED_DELIVERY_DATE=?, ORDER_CURRENCY=?, CREDIT_STATUS=?, CUSTOMER_NUMBER=?, AMOUNT_IN_USD=?, UNIQUE_CUST_ID=? WHERE Sl_no=?";
//    private static final String DELETE_INVOICE = "DELETE FROM h2h_oap WHERE Sl_no=?";
//
//    @Override
//    public void addInvoice(Invoice invoice) 
//    {
//        try (Connection conn = DatabaseConnection.getConnection();
//        	PreparedStatement stmt = conn.prepareStatement(ADD_INVOICE)) 
//        {
//            stmt.setInt(1, invoice.getslNo());
//            stmt.setInt(2, invoice.getCustomerOrderId());
//            stmt.setInt(3, invoice.getSalesOrg());
//            stmt.setString(4, invoice.getDistributionChannel());
//            stmt.setString(5, invoice.getDivision());
//            stmt.setDouble(6, invoice.getReleasedCreditValue());
//            stmt.setString(7, invoice.getPurchaseOrderType());
//            stmt.setInt(8, invoice.getCompanyCode());
//            stmt.setString(9, invoice.getOrderCreationDate());
//            stmt.setString(10, invoice.getOrderCreationTime());
//            stmt.setString(11, invoice.getCreditControlArea());
//            stmt.setInt(12, invoice.getSoldToParty());
//            stmt.setDouble(13, invoice.getOrderAmount());
//            stmt.setString(14, invoice.getRequestedDeliveryDate());
//            stmt.setString(15, invoice.getOrderCurrency());
//            stmt.setString(16, invoice.getCreditStatus());
//            stmt.setInt(17, invoice.getCustomerNumber());
//            stmt.setDouble(18, invoice.getAmountInUSD());
//            stmt.setLong(19, invoice.getUniqueCustId());
//
//            stmt.executeUpdate();
//        
//        } 
//        catch (SQLException e) 
//        {
//            e.printStackTrace();
//        }
//    }
//    
//
//    @Override
//    public List<Invoice> getInvoices() {
//        List<Invoice> invoices = new ArrayList<>();
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(GET_INVOICES);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Invoice invoice = new Invoice();
//                invoice.setslNo(rs.getInt("Sl_no"));
//                invoice.setCustomerOrderId(rs.getInt("CUSTOMER_ORDER_ID"));
//                invoice.setSalesOrg(rs.getInt("SALES_ORG"));
//                invoice.setDistributionChannel(rs.getString("DISTRIBUTION_CHANNEL"));
//                invoice.setDivision(rs.getString("DIVISION"));
//                invoice.setReleasedCreditValue(rs.getDouble("RELEASED_CREDIT_VALUE"));
//                invoice.setPurchaseOrderType(rs.getString("PURCHASE_ORDER_TYPE"));
//                invoice.setCompanyCode(rs.getInt("COMPANY_CODE"));
//                invoice.setOrderCreationDate(rs.getString("ORDER_CREATION_DATE"));
//                invoice.setOrderCreationTime(rs.getString("ORDER_CREATION_TIME"));
//                invoice.setCreditControlArea(rs.getString("CREDIT_CONTROL_AREA"));
//                invoice.setSoldToParty(rs.getInt("SOLD_TO_PARTY"));
//                invoice.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
//                invoice.setRequestedDeliveryDate(rs.getString("REQUESTED_DELIVERY_DATE"));
//                invoice.setOrderCurrency(rs.getString("ORDER_CURRENCY"));
//                invoice.setCreditStatus(rs.getString("CREDIT_STATUS"));
//                invoice.setCustomerNumber(rs.getInt("CUSTOMER_NUMBER"));
//                invoice.setAmountInUSD(rs.getDouble("AMOUNT_IN_USD"));
//                invoice.setUniqueCustId(rs.getLong("UNIQUE_CUST_ID"));
//
//                invoices.add(invoice);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return invoices;
//    }
//
//    @Override
//    public void deleteInvoice(int id) {
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(DELETE_INVOICE)) {
//
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static int updateInvoice(Invoice invoice) {
//    	int status = 0;
//    
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(UPDATE_INVOICE)) {
//
//            stmt.setInt(1, invoice.getCustomerOrderId());
//            stmt.setInt(2, invoice.getSalesOrg());
//            stmt.setString(3, invoice.getDistributionChannel());
//            stmt.setString(4, invoice.getDivision());
//            stmt.setDouble(5, invoice.getReleasedCreditValue());
//            stmt.setString(6, invoice.getPurchaseOrderType());
//            stmt.setInt(7, invoice.getCompanyCode());
//            stmt.setString(8, invoice.getOrderCreationDate());
//            stmt.setString(9, invoice.getOrderCreationTime());
//            stmt.setString(10, invoice.getCreditControlArea());
//            stmt.setInt(11, invoice.getSoldToParty());
//            stmt.setDouble(12, invoice.getOrderAmount());
//            stmt.setString(13, invoice.getRequestedDeliveryDate());
//            stmt.setString(14, invoice.getOrderCurrency());
//            stmt.setString(15, invoice.getCreditStatus());
//            stmt.setInt(16, invoice.getCustomerNumber());
//            stmt.setDouble(17, invoice.getAmountInUSD());
//            stmt.setLong(18, invoice.getUniqueCustId());
////            stmt.setInt(19, invoice.getId());
//
//            stmt.executeUpdate();
//        }catch(Exception ex){ex.printStackTrace();}  
//        
//        return status;  
//    }  
//    
//    @Override
//    public Invoice getInvoiceById(int invoiceId) {
//        Invoice invoice = null;
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(GET_INVOICES)) {
//            stmt.setInt(1, invoiceId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    invoice = new Invoice();
//                    invoice.setslNo(rs.getInt("Sl_no"));
//                    invoice.setCustomerOrderId(rs.getInt("CUSTOMER_ORDER_ID"));
//                    invoice.setSalesOrg(rs.getInt("SALES_ORG"));
//                    invoice.setDistributionChannel(rs.getString("DISTRIBUTION_CHANNEL"));
//                    invoice.setDivision(rs.getString("DIVISION"));
//                    invoice.setReleasedCreditValue(rs.getDouble("RELEASED_CREDIT_VALUE"));
//                    invoice.setPurchaseOrderType(rs.getString("PURCHASE_ORDER_TYPE"));
//                    invoice.setCompanyCode(rs.getInt("COMPANY_CODE"));
//                    invoice.setOrderCreationDate(rs.getString("ORDER_CREATION_DATE"));
//                    invoice.setOrderCreationTime(rs.getString("ORDER_CREATION_TIME"));
//                    invoice.setCreditControlArea(rs.getString("CREDIT_CONTROL_AREA"));
//                    invoice.setSoldToParty(rs.getInt("SOLD_TO_PARTY"));
//                    invoice.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
//                    invoice.setRequestedDeliveryDate(rs.getString("REQUESTED_DELIVERY_DATE"));
//                    invoice.setOrderCurrency(rs.getString("ORDER_CURRENCY"));
//                    invoice.setCreditStatus(rs.getString("CREDIT_STATUS"));
//                    invoice.setCustomerNumber(rs.getInt("CUSTOMER_NUMBER"));
//                    invoice.setAmountInUSD(rs.getDouble("AMOUNT_IN_USD"));
//                    invoice.setUniqueCustId(rs.getLong("UNIQUE_CUST_ID"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return invoice;
//    }
//}





