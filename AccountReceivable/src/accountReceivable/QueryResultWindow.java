package accountReceivable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;



public class QueryResultWindow 
{
	private JFrame mF;
	private JPanel mP;
	private JPanel sP;
	private JList<String> mL;
	private JScrollPane msP;
	private JLabel mLab;
	private JButton close;
	
	public QueryResultWindow()
	{
		mF= new JFrame("Query Result!");
		mF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mF.setLayout(new FlowLayout());
		mF.setVisible(true);

	}
	
	public void paidInvoiceQueryLayout(ResultSet result) throws SQLException
	{
		
		//invoice sql to fill arraylist
		Invoice temp = new Invoice();
		
        ArrayList<Invoice> invList = temp.invoiceGrabberGrouper(result);
        System.out.println("hello " + invList.get(1).getamountDue());
        ArrayList<String> invString = new ArrayList<String>();
        //1 COLUMN PER VARIABLE INCLUDING DAYS
       

        File options = new File("options.txt");
        Scanner in;
		try {
			String s = "";
			in = new Scanner(options);
		     while(in.hasNext())
		     {
		    	 s += in.next();
		     }
		     String[] guts = s.split(",");
		     int days =  Integer.parseInt(guts[0]);
		     int columns = Integer.parseInt(guts[1]);
		     
		     String rates = guts[2];
		     String[] guts2 = rates.split("-");//rates for each column 


		     //CALL SQL OFF THAT NEW OBJECT
			
		     //invoice to string
		     for(int i = 0; i<invList.size(); i++)
		     {
		    	 
		    	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    	 Invoice invTemp = invList.get(i);
		    	 String invID = Integer.toString(invTemp.getinvoiceID());
		    	 Date tempID = invTemp.getdateIssued();
		    	 String dateIssued = df.format(tempID);
		    	 Date tempDD = invTemp.getdateDue();
		    	 String dateDue = df.format(tempDD);
		    	 String amountDue = Double.toString(invTemp.getamountDue());
		    	 String custID = Integer.toString(invTemp.getcustomerID());
		    	 String invoice = invID+","+dateIssued+","+dateDue+","+amountDue+","+custID;
		    	
		    	 invString.add(invoice);
		     }
		     System.out.println("invoice to string check");
        
		     JPanel[] jp = new JPanel[(columns+5)];//panel array to hold variables
		     //rotating labels
	         JLabel[] labels = new JLabel[jp.length];
	         int dayz = days;
	         int daysCounter = 1;
	         for(int l = 0; l<labels.length; l++)
	         {
	        	 if (l==0)
	        	 {
	        		 labels[l].setText("InvoiceID");
	        	 }
	        	 else if (l==1)
	        	 {
	        		 labels[l].setText("DateIssued");
	        	 }
	        	 else if (l==2)
	        	 {
	        		 labels[l].setText("DateDue");
	        	 }
	        	 else if (l==3)
	        	 {
	        		 labels[l].setText("AmountDue");
	        	 }
	        	 else if (l==4)
	        	 {
	        		 labels[l].setText("CustomerID");
	        	 }
	        	 else if (l==5)
	        	 {
	        		 labels[l].setText((0)+"-"+(dayz*=daysCounter)+" Days" );
	        		 daysCounter++;
	        	 }
	        	 else if(l<5)
	        	 {
	        		 labels[l].setText(((dayz*daysCounter-dayz))+"-"+(dayz*=daysCounter)+" Days" );
	        	 }
	         }
	         System.out.println("rotating labels check");

		     for(int i = 0; i<jp.length; i++)
		     {
		    	 
		    	 
		    	 
		    	 jp[i] = new JPanel();
		    	 mP = jp[i];
		    	 FlowLayout layout = new FlowLayout();
		    	 mP.setBackground(Color.CYAN);
		    	 mP.setLayout(layout);
		    	 
		    	 String arr[] = new String[invString.size()];
		         for (int k = 0; k < arr.length; k++)
		         {
		        	 
		        	 String[] split = invString.get(k).split(",");
		        	 arr[k] = split[i];
		        	 System.out.println(arr + " arr check");
		        	 
		         }
		         
		         mLab = labels[i];
		    	 mL = new JList<>(arr);
		    	 msP = new JScrollPane(mL);
		    	 
		    	 
		    	 
		    	 mP.add(mLab);
		    	 mP.add(mL);
		    	 mP.add(msP);
		 		
		 		mF.add(mP);

			    mF.pack();
			    mF.setLocationRelativeTo(null);
		     }
		}
		catch(Exception e)
		{
			System.out.println("querypaidresultwindow fail "+ e.getMessage());
		}

        

        
        


	}
	public void unpaidInvoiceQueryLayout(ArrayList<Invoice> inList)
	{
		//sql fill to to arraylist
		//need new arraylists for each age tier
        ArrayList<String> wordlist = new ArrayList<String>();
        wordlist.add("Head");
        wordlist.add("Green");
        wordlist.add("Water");
        wordlist.add("To sing");
        wordlist.add("Dead");
        wordlist.add("Long");
        wordlist.add("Ship");
        wordlist.add("To pay");
        wordlist.add("Window");
        wordlist.add("Friendly");
        wordlist.add("To cook");

        File options = new File("options.txt");
        Scanner in;
		try {
			String s = "";
			in = new Scanner(options);
		     while(in.hasNext())
		     {
		    	 s += in.next();
		     }
		     String[] guts = s.split(",");
		     String days =  guts[0];
		     String tiers = guts[1];
		     String discount = guts[2];
		     
		     int dayz = Integer.parseInt(days);
		     int tierz = Integer.parseInt(tiers);
		     double dizcount = Double.parseDouble(discount);
			
		     JPanel[] jp = new JPanel[tierz];
		     
		     for(int i = 0; i<tierz; i++)
		     {
		    	 
		    	 //ifstatement inside here to distinguish
		    	 
		    	 
		    	 jp[i] = new JPanel();
		    	 mP = jp[i];
		    	 FlowLayout layout = new FlowLayout();
		    	 mP.setBackground(Color.CYAN);
		    	 mP.setLayout(layout);

		    	 mL = new JList<>(wordlist.toArray(new String[0]));
		    	 msP = new JScrollPane(mL);
		    	 mLab= new JLabel("lolz");
		    	 mP.add(mLab);
		    	 mP.add(mL);
		    	 mP.add(msP);
		 		
		 		
		 		mF.add(mP);
		     }
			
			    mF.pack();
			    mF.setLocationRelativeTo(null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
   
        

        
		
	}
	public void addPaymentQueryLayout()
	{
        ArrayList<String> wordlist = new ArrayList<String>();
        wordlist.add("Head");
        wordlist.add("Green");
        wordlist.add("Water");
        wordlist.add("To sing");
        wordlist.add("Dead");
        wordlist.add("Long");
        wordlist.add("Ship");
        wordlist.add("To pay");
        wordlist.add("Window");
        wordlist.add("Friendly");
        wordlist.add("To cook");

        File options = new File("options.txt");
        Scanner in;
		try {
			String s = "";
			in = new Scanner(options);
		     while(in.hasNext())
		     {
		    	 s += in.next();
		     }
		     String[] guts = s.split(",");
		     String days =  guts[0];
		     String tiers = guts[1];
		     String discount = guts[2];
		     
		     int dayz = Integer.parseInt(days);
		     int tierz = Integer.parseInt(tiers);
		     double dizcount = Double.parseDouble(discount);
			
		     QueryResultWindow[] qRW = new QueryResultWindow[tierz];
		     
		     for(int i = 0; i<tierz; i++)
		     {
		    	 
		    	 //ifstatement inside here to distinguish
		    	 this.mP = new JPanel();
		    	 FlowLayout layout = new FlowLayout();
		    	 this.mP.setBackground(Color.PINK);
		    	 this.mP.setLayout(layout);

		    	 this.mL = new JList<>(wordlist.toArray(new String[0]));
		    	 this.msP = new JScrollPane(mL);
		    	 this.mLab= new JLabel("lolz");
		    	 this.mP.add(this.mLab);
		    	 this.mP.add(this.mL);
		    	 this.mP.add(this.msP);
		 		
		 		
		 		mF.add(this.mP);
		     }
			
			    mF.pack();
			    mF.setLocationRelativeTo(null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
   
        

        
	}
}
