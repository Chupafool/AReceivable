package accountReceivable;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer 
{
	private String firstName;
	private String lastName;
	private String name = firstName + lastName;
	private int customerID;
	
	public Customer(String f, String l)
	{
		firstName = f;
		lastName = l;
		
	}
	
	public Customer(String f, String l, int id)
	{
		firstName = f;
		lastName = l;
		customerID = id;
	}
	public Customer()
	{
		firstName = "Kritz";
		lastName = "allDay";
	}
	
	public String getCustfn()
	{
		return firstName;
	}
	public String getCustln()
	{
		return lastName;
	}
	public int getCustID()
	{
		return customerID;
	}
	public void setCustID(int i)
	{
		this.customerID=i;
	}
	
	public void customerInsert() throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();		
			try
			{
				try
				{
					PreparedStatement statEZ = conn.prepareStatement("SELECT COUNT(CustomerID) FROM CustomerTable");
					ResultSet result = statEZ.executeQuery();
					result.next();
					int custCount = result.getInt(1);
					statEZ.close();
					
					PreparedStatement stat = conn.prepareStatement("INSERT INTO CustomerTable (CustomerID, FirstName, LastName) VALUES (?, ?, ?)");
					stat.setInt(1, custCount+1);
					stat.setString(2, firstName);
					stat.setString(3, lastName);
					stat.execute();
					stat.close();
	
				}
				catch(SQLException ez)
				{
					System.out.println("count customerinsert is messed");
				}
		}
		finally
		{
			conn.close();
		}
	}
	public int findCustomerID() throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		int custID;
		try
		{
			// Does the customer exist?
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM CustomerTable WHERE FirstName= ? AND LastName= ? ");
			try
			{
				stat.setString(1, this.firstName);
				stat.setString(2, this.lastName);
				ResultSet result = stat.executeQuery();
				result.next();
				custID = result.getInt(1);
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
		return custID;
	}
	
	public boolean find(String first, String last) throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		try
		{
			// Does the customer exist?
			PreparedStatement stat = conn.prepareStatement("SELECT COUNT(*) FROM CustomerTable WHERE FirstName= ? AND LastName= ? ");
			try
			{
				stat.setString(1, first);
				stat.setString(2, last);
				ResultSet result = stat.executeQuery();
				// There must be one row returned. 
				result.next();
				if (result.getInt(1) == 0)
				{
					return false;
				}
				// customer exists: return it.
				else
				{
					return true;
				}
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
	}
	
	public int countCustomers()
	{
	//count invoices
	int custCount = -1;
	try
	{
		Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		ResultSet customerCount = stat.executeQuery("SELECT COUNT(CustomerID) FROM CustomerTable");
		customerCount.next();
		custCount = customerCount.getInt(1);
		System.out.println("CUSTOMER COUNT" + custCount);
		
	}
	catch(SQLException ez)
	{
		System.out.println("New invoice breaking: " + ez.getMessage());
	}
	return custCount;
	}
	
	/*public ArrayList<Invoice> invoiceGrabberGrouper(ResultSet rslt) throws SQLException
	{
		ArrayList<Invoice> inList = new ArrayList<Invoice>();	
		ResultSet invoiceT = rslt;
			
		while (invoiceT.next())
		{
			Invoice tempIn = new Invoice();
				
			int invid = invoiceT.getInt(1);
			Date tempDI = invoiceT.getDate(2);
			Date tempDD = invoiceT.getDate(3);
			double amount = invoiceT.getDouble(4);
			int custid = invoiceT.getInt(5);
			
			tempIn.setInvoiceID(invid);
			tempIn.setdateIssue(tempDI);
			tempIn.setdateDue(tempDD);
			tempIn.setamountDue(amount);
			tempIn.setCustomerID(custid);
				
			inList.add(tempIn);

			}
		return inList;
	}*/
}


