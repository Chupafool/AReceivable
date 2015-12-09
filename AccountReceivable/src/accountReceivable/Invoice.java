package accountReceivable;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Invoice 
{
	private Date dateIssued;
	private Date dateDue;
	private double amountDue;
	private int invoiceID;
	private int customerID;
	
	public Invoice()
	{
		dateIssued = new Date(2015-12-7);
		dateDue = new Date(2015-12-8);
		amountDue = 0.00;
		invoiceID = -1;
		customerID = -1;
	}
	
	public Invoice (Date n, Date dD, double aD)
	{
		dateIssued = n;
		dateDue = dD;
		amountDue = aD;
	}
	public Invoice (int invID, Date n, Date dD, double aD, int custID)
	{
		invoiceID = invID;
		dateIssued = n;
		dateDue = dD;
		amountDue = aD;
		customerID = custID;
	}
	public void setdateIssued(Date dI)
	{
		this.dateIssued = dI;
	}
	public void setdateDue(Date dD)
	{
		this.dateDue = dD;
	}
	public void setamountDue(double aD)
	{
		this.amountDue = aD;
	}
	public void setInvoiceID(int iD)
	{
		this.invoiceID = iD;
	}
	public void setCustomerID(int iD)
	{
		this.customerID = iD;
	}
	public Date getdateIssued()
	{
		return dateIssued;
	}
	public Date getdateDue()
	{
		return dateDue;
	}
	public double getamountDue()
	{
		return amountDue;
	}
	public int getinvoiceID()
	{
		return invoiceID;
	}
	public int getcustomerID()
	{
		return customerID;
	}
	
	
	public ResultSet find(int invID) throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		ResultSet result = null;
		try
		{
		
			// Does the customer exist?
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM InvoiceTable WHERE InvoiceID = ?");
			try
			{
				stat.setInt(1, invID);
				result = stat.executeQuery();
				// There must be one row returned. 
				result.next();
			}	
			catch(SQLException e)
			{
				System.out.println("Customer does not exist");
			}
		}
		finally
		{
			conn.close();

		}
		return result;

	}
	
	public int countInvoices()
	{
	//count invoices
	int inCount = -1;
	try
	{
		Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		ResultSet invoiceCount = stat.executeQuery("SELECT COUNT(InvoiceID) FROM InvoiceTable");
		invoiceCount.next();
		inCount = invoiceCount.getInt(1);
		System.out.println("INVOICE COUNT" + inCount);
		
	}
	catch(SQLException ez)
	{
		System.out.println("New invoice breaking: " + ez.getMessage());
	}
	return inCount;
	}
	
	public ArrayList<Invoice> invoiceGrabberGrouper(ResultSet rslt) throws SQLException
	{
		ArrayList<Invoice> inList = new ArrayList<Invoice>();	
		ResultSet invoiceT = rslt;
		invoiceT.next();	
		while (invoiceT.next())
		{
			Invoice tempIn = new Invoice();
				
			int invid = invoiceT.getInt(1);
			Date tempDI = invoiceT.getDate(2);
			Date tempDD = invoiceT.getDate(3);
			double amount = invoiceT.getDouble(4);
			int custid = invoiceT.getInt(5);
			
			tempIn.setInvoiceID(invid);
			tempIn.setdateIssued(tempDI);
			tempIn.setdateDue(tempDD);
			tempIn.setamountDue(amount);
			tempIn.setCustomerID(custid);
				
			inList.add(tempIn);

			}
		return inList;
	}
	
	public Invoice findInvoice(int ID) throws SQLException
	{
		Invoice temp = null;
		Connection conn = SimpleDataSource.getConnection();
		try
		{
			// Does the customer exist?
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM InvoiceTable WHERE InvoiceID = ? ");
			try
			{
				stat.setInt(1, ID);
				ResultSet result = stat.executeQuery();
				result.next();
				temp = new Invoice(result.getInt(1), result.getDate(2), result.getDate(3), result.getDouble(4), result.getInt(5));
			}	
			finally
			{
				stat.close();
			}
		}
		finally
		{
			conn.close();
		}
		return temp;
	}

}
