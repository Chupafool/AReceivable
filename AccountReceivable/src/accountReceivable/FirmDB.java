package accountReceivable;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;




public class FirmDB 
{
	public FirmDB() throws SQLException, ClassNotFoundException, IOException
	{
		
		Connection conn = SimpleDataSource.getConnection();
		Statement stat = conn.createStatement();
		Statement stat5 = conn.createStatement();
		
		
	
			try
			{

				
				try{
					stat.execute("DROP TABLE InvoiceTable");}
					catch(SQLException rious){
						System.out.println(rious.getMessage());
						
					}
				try{
					stat.execute("DROP TABLE CustomerTable");}
					catch(SQLException s){
						System.out.println(s.getMessage());
						
					}

				
				
				
			
			try
			{
				stat5.execute("CREATE TABLE CustomerTable (CustomerID INT NOT NULL PRIMARY KEY, FirstName VARCHAR(20), LastName VARCHAR(20))");
			}
			catch(SQLException ez)
			{
				System.out.println("Exception during create table sequence: "+ ez.getMessage());
			}
			try
			{
				stat.execute("CREATE TABLE InvoiceTable (InvoiceID int NOT NULL PRIMARY KEY, DateIssued DATE, DateDue DATE, AmountDue DOUBLE PRECISION, CustomerID INT) ");

			}
			catch(SQLException ezee)
			{
				System.out.println("Exception during create table sequence2: "+ ezee.getMessage());
			}
			
			
			int customerIDs[] = {1,2,3,4};
			String firstNames[] = {"Jon","Kitten","Winter","Aragorn"};
			String lastNames[] = {"Snow","Mittens","Wyvern","King of Gondor"};
			
			int invoiceIDs[] = {1,2,3,4};//yyyy-MM-dd
			Date datesDue[] = {new Date(1999,01,01), new Date(2001,05,11), new Date(1994,06,04), new Date(1776,07,04)};
			Date datesIssued[] = {new Date(90,11,3), new Date(1943,06,01), new Date(1930,05,07), new Date(1812,05,10)};
			System.out.println("LOL" + datesIssued[2]);
			double amountsDue[] = {0.00, 99.87, 54.24, 21.61};
			stat.close();
		
			
			try
			{
			//initializing the db customer
			for(int i = 0; i<customerIDs.length; i++)
			{
				try
				{
				PreparedStatement stat2 = conn.prepareStatement("INSERT INTO CustomerTable (CustomerID, FirstName, LastName) VALUES (?, ?, ?)");
				stat2.setInt(1, customerIDs[i]);
				stat2.setString(2, firstNames[i]);
				stat2.setString(3, lastNames[i]);
				stat2.execute();
				stat2.close();
				}
				catch(SQLException eze)
				{
					System.out.println("initialize loop fail" + eze.getMessage());
				}
			}
			//initializing the db invoice
			for(int i = 0; i<invoiceIDs.length; i++)
			{
				try
				{
				PreparedStatement stat2 = conn.prepareStatement("INSERT INTO InvoiceTable (InvoiceID, DateIssued, DateDue, AmountDue, CustomerID ) VALUES (?, ?, ?, ?, ?)");
				System.out.println(invoiceIDs[i]);
				
				stat2.setInt(1, invoiceIDs[i]);
				stat2.setDate(2, datesIssued[i]);
				stat2.setDate(3, datesDue[i]);
				stat2.setDouble(4, amountsDue[i]);
				stat2.setInt(5, customerIDs[i]);
				stat2.execute();
				stat2.close();
				}
				catch(SQLException eze)
				{
					System.out.println("initialize loop fail inv" + eze.getMessage());
				}
			}
			
			}
			finally
			{
			
				conn.close();
				
			}
			}
		finally{
			System.out.println("hurray");
		}
			
		

	
	}
	

	

	
	
}
