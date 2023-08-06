package com.highradius.model;

public class Invoice 
{
	private int id;

    public Invoice(int id, int salesOrg, String distributionChannel, String division, 
    		double releasedCreditValue, String purchaseOrderType, int companyCode, 
    		String orderCreationDate, String orderCreationTime, String creditControlArea, 
    		int soldToParty, double orderAmount, String requestedDeliveryDate, String orderCurrency, 
    		String creditStatus, int customerNumber, double amountInUSD, long uniqueCustId) 
    {
       this.id = id;
    }

	public int getId() 
    {
        return id;
    }
	
    public void setId(int id) 
    {
        this.id = id;
    }
}

//	public int getCustomerOrderId() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public int getSalesOrg() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getDistributionChannel() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getDivision() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public double getReleasedCreditValue() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getPurchaseOrderType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public int getCompanyCode() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getOrderCreationTime() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getOrderCreationDate() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getCreditControlArea() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public int getSoldToParty() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public double getOrderAmount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getRequestedDeliveryDate() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getOrderCurrency() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public int getCustomerNumber() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getCreditStatus() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public double getAmountInUSD() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public long getUniqueCustId() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}