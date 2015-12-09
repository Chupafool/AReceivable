package accountReceivable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;


public class PromptWindow 
{
	private JFrame mF;
	private JLabel header = new JLabel();
	private JLabel promptQ = new JLabel();
	private JButton ready = new JButton("Ready?");
	private JTextField FirstName = new JTextField("Enter the first name here, or 'all' for all, press enter");
	private JTextField LastName = new JTextField("Enter the last name here, or 'all' for all, press enter");
	private JTextField earlyDate = new JTextField("Enter the earlier date here, press enter");
	private JTextField lateDate = new JTextField("Enter the latter date here, press enter");
	private JTextField unpaidFirstName = new JTextField("Enter the first name here, press enter");
	private JTextField unpaidLastName = new JTextField("Enter the last name here, press enter");
	private JTextField newFirstName = new JTextField("Enter the first name here, press enter");
	private JTextField newLastName = new JTextField("Enter the last name here, press enter");
	private JTextField newAmount = new JTextField("Enter the amount due, press enter");
	private JTextField dueDate = new JTextField("Enter the date due, press enter");
	private JTextField issuedDate = new JTextField("Enter the issue date, press enter");
	private JTextField newcFirstName = new JTextField("Enter the first name here, press enter");
	private JTextField newcLastName = new JTextField("Enter the last name here, press enter");
	private JTextField editFirstName = new JTextField("Enter the first name here, press enter");
	private JTextField editLastName = new JTextField("Enter the last name here, press enter");
	private JTextField neweditFirstName = new JTextField("Enter the new first name here, press enter");
	private JTextField neweditLastName = new JTextField("Enter the new last name here, press enter");
	private JTextField paymentFirstName = new JTextField("Enter the first name here, press enter");
	private JTextField paymentLastName = new JTextField("Enter the last name here, press enter");
	private JTextField paymentInvoiceID = new JTextField("Enter the invoiceID here, press enter");
	private JTextField paymentAmount = new JTextField("Enter the amount in the payment as a double, press enter");
	private JTextField deleteFirstName = new JTextField("Enter the First name to be deleted, press enter");
	private JTextField deleteLastName = new JTextField("Enter the First name to be deleted, press enter");
	private JCheckBox ruSure = new JCheckBox("Are you sure?");
	private JLabel instructions = new JLabel("Follow the text field's instructions, then press Ready");
	public Customer custPaid, custunPaid, customerPayment;

	

	
	public PromptWindow()
	{
		mF= new JFrame("Prompt Window");
		mF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mF.setLayout(new BorderLayout());
		mF.setVisible(true);

	}
	
	public void promptLayout()
	{
		//drop down menu (combobox)
        JPanel cbPanel = new JPanel(new FlowLayout());
        cbPanel.setSize(300,00);
        cbPanel.setBackground(Color.CYAN);
        String cbItems[] = { "Query customer history for paid invoices", "Query customer history for unpaid invoices", "New Invoice","New Customer", "Edit Customer", "Add Payment", "Delete Customer"  };
        JComboBox<String> cb = new JComboBox<String>(cbItems);
        JLabel info = new JLabel("Use this dropdown menu to select which type of query");
        cbPanel.add(info);
        cbPanel.add(cb);
        cbPanel.add(instructions);




        //first "card" PAID INVOICES
		JPanel paidCard  = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(10);              
		layout.setVgap(10);
		paidCard.setLayout(layout); 
        paidCard.setBackground(Color.YELLOW);
		
		
		paidCard.add(FirstName);
		paidCard.add(LastName);
		paidCard.add(earlyDate);
		paidCard.add(lateDate);
		
		paidCard.add(ready);
		ready.setToolTipText("Click this when you're ready to roll!");

        FirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("FirstName!: " + FirstName.getText());
            }
        }); 
        

        LastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("lastname!!!!!"  + LastName.getText());
            }
        }); 
		
        earlyDate.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("early date!!!!!"  + earlyDate.getText());
            }
        }); 
        
        lateDate.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("latter date!!!!!"  + lateDate.getText());
            }
        }); 
		

        ready.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	
        		
                //SEARCH AND DISPLAY FOR PAID INVOICE HISTORY FOR CUSTOMER

            	Customer custTemp = new Customer(FirstName.getText(), LastName.getText());
            	
            	Invoice invTemp = new Invoice();
            	ArrayList<Invoice> paidInvoiceList = new ArrayList<Invoice>();

            	//NO CUSTOMER FOUND
            		try{
    					if (FirstName.getText().equals("all") && LastName.getText().equals("all"))
    					{
    						Connection conn = SimpleDataSource.getConnection();
    						PreparedStatement pstat2 = conn.prepareStatement("SELECT * FROM InvoiceTable WHERE( amountDue=0 AND DateIssued>= ? AND DateIssued< ? )");

    						pstat2.setDate(1, java.sql.Date.valueOf(earlyDate.getText()));
    						pstat2.setDate(2, java.sql.Date.valueOf(lateDate.getText()));

    						
    						
    						ResultSet paidInvoices = pstat2.executeQuery();
    						QueryResultWindow paidInvoiceWindow = new QueryResultWindow();
    						paidInvoiceWindow.paidInvoiceQueryLayout(paidInvoices);
    						System.out.println("lolqueryresult!!!!!!");
    						paidInvoices.close();
    					}
    					
            		else if(custTemp.find(custTemp.getCustfn(), custTemp.getCustln())==false)
					{
						System.out.println("No such customer found");
					}
					//FOUND CUSTOMER, QUERY FOR PAID INVOICES,  QUERYRESULTWINDOW WITH RESULTSET
					else if(custTemp.find(custTemp.getCustfn(), custTemp.getCustln())==true)
					{
						Connection conn = SimpleDataSource.getConnection();
		        		PreparedStatement pstat = conn.prepareStatement("SELECT * FROM InvoiceTable WHERE CustomerID = ? AND( amountDue=0 AND DateIssued>= ? AND DateDue<= ? )");
						pstat.setInt(1, custTemp.getCustID());
						pstat.setDate(2, java.sql.Date.valueOf(earlyDate.getText()));
						pstat.setDate(3, java.sql.Date.valueOf(lateDate.getText()));
						
						
						ResultSet paidInvoices = pstat.executeQuery();
					
						QueryResultWindow paidInvoiceWindow = new QueryResultWindow();
						paidInvoiceWindow.paidInvoiceQueryLayout(paidInvoices);
						System.out.println("lolqueryresult!!!!!!");
						paidInvoices.close();

					}


					
            		}
            		catch(Exception ezr){
            			System.out.println("Something is wrong in paid nest!" + ezr.getMessage());
            		}

            			
        
            }}); 
            		

		
		//2nd card, UNPAID INVOICES 
		JPanel unpaidCard = new JPanel();

		unpaidCard.add(unpaidFirstName);
		unpaidCard.add(unpaidLastName);
        JButton ready1 = new JButton("Ready?");
        
		unpaidFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("unpaidFirstName!: " + unpaidFirstName.getText());
            }
        }); 
        

        unpaidLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("lastname!!!!!"  + unpaidLastName.getText());
            }
        }); 
		
        

        unpaidCard.add(ready1);
        ready1.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //SEARCH AND DISPLAY FOR CUSTOMER UNPAID INVOICE HISTORY
            	Customer custTemp = new Customer(unpaidFirstName.getText(), unpaidLastName.getText());
            	Invoice invTemp = new Invoice();
            	ArrayList<Invoice> unpaidInvoiceList = new ArrayList<Invoice>();
            	
            	//NO CUSTOMER FOUND
            	try {
					if(custTemp.find(custTemp.getCustfn(), custTemp.getCustln())==false)
					{
						System.out.println("No such customer found");
					}
					//FOUND CUSTOMER, QUERY FOR UNPAID INVOICES, QUERYRESULTWINDOW WITH RESULTSET
					else
					{
						Connection conn = SimpleDataSource.getConnection();
						PreparedStatement pstat = conn.prepareStatement("SELECT * FROM INVOICETABLE WHERE amountDue>0");
						ResultSet paidInvoices = pstat.executeQuery();
						unpaidInvoiceList = invTemp.invoiceGrabberGrouper(paidInvoices);
						QueryResultWindow paidInvoiceWindow = new QueryResultWindow();
						paidInvoiceWindow.unpaidInvoiceQueryLayout(unpaidInvoiceList);
						System.out.println("lol unpaid queryresult!!!!!!");
						
					}
					} 
            	catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("unpaid prompt fail " + e1.getMessage());
				}
            }
        }); 
        
        //3rd card, NEW INVOICE 
        JPanel newinvCard = new JPanel();
        newinvCard.setBackground(Color.BLACK);
        
        newinvCard.add(newFirstName);
        newinvCard.add(newLastName);
        newinvCard.add(issuedDate);
        newinvCard.add(dueDate);
        newinvCard.add(newAmount);
        JButton ready2 = new JButton("Ready?");
        newinvCard.add(ready2);
        
        newFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 // new invoice First NAme
            	System.out.println("newFirstName!: " + newFirstName.getText());
            }
        }); 
        
        newLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //new invoice Last Name
            	System.out.println("newLastName!: " + newLastName.getText());
            }
        }); 

        issuedDate.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //save to prompt window variable for prepared statement
            	System.out.println("issuedDate!: " + issuedDate.getText());
            }
        }); 
        
        newAmount.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	System.out.println("NEW! amount" + newAmount.getText());
            }
        }); 
        dueDate.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	System.out.println("NEW! dateDUE" + dueDate.getText());
            }
        }); 
        
        ready2.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	int custCount;
            	int invCount;
            	Invoice invTemp = new Invoice();
            	Invoice invTemp2;
            	Customer custTemp = new Customer(newFirstName.getText(), newLastName.getText());

            	custCount = custTemp.countCustomers();
            	invCount = invTemp.countInvoices();
            	

            	
            			//IF THE CUSTOMER DOES NOT EXIST, INSERT THE CUSTOMER TO SQL CUSTOMERTABLE, INSERT INVOICE
            			try {
							if(custTemp.find(custTemp.getCustfn(), custTemp.getCustln())==false)
							{
						
								{
									System.out.println("Customer does not exist");
								}	
								
							}
							//EXISTING CUSTOMER, ADD INVOICE
							else
							{
								Connection conn = SimpleDataSource.getConnection();
								int tempCustID = custTemp.findCustomerID();
								PreparedStatement pstat = conn.prepareStatement("INSERT INTO InvoiceTable (InvoiceID, DateIssued, DateDue, AmountDue, CustomerID ) VALUES (?, ?, ?, ?, ?)");
								invTemp2 = new Invoice((invCount+1), java.sql.Date.valueOf(issuedDate.getText()), java.sql.Date.valueOf(dueDate.getText()), Double.parseDouble(newAmount.getText()), tempCustID);
								pstat.setInt(1, invTemp2.getinvoiceID());
								pstat.setDate(2, invTemp2.getdateIssued());
								pstat.setDate(3 , invTemp2.getdateDue());
								pstat.setDouble(4, invTemp2.getamountDue());
								pstat.setInt(5, invTemp2.getcustomerID());
								try{
									pstat.executeUpdate();}
								catch(SQLException eze){
									System.out.println("existing customer invoice insert fail " + eze.getMessage());}
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							System.out.println("new invoice fail " + e1.getMessage());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							System.out.println("new invoice fail 2 " + e2.getMessage());
						}	
                    	System.out.println("NEW! ready");
                    	invCount = invTemp.countInvoices();
            			}
        }); 
        
        //4TH CARD, NEW CUSTOMER
        JPanel newcustCard = new JPanel();
        newcustCard.setBackground(Color.GREEN);
        
        newcustCard.add(newcFirstName);
        newcustCard.add(newcLastName);
        JButton readyFINALLY = new JButton("Ready?");
        newcustCard.add(readyFINALLY);
        
        newcFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("firstName new customer!" + newcFirstName.getText());
            }
        }); 
        newcLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("lastName new customer!" + newcLastName.getText());
            }
        }); 
        
        readyFINALLY.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	int custCount;
            	int invCount;
            	Invoice invTemp = new Invoice();
            	Invoice invTemp2;
            	Customer custTemp = new Customer(newcFirstName.getText(), newcLastName.getText());

            	custCount = custTemp.countCustomers();
            	invCount = invTemp.countInvoices();
            	

            	
            			//IF THE CUSTOMER DOES NOT EXIST, INSERT THE CUSTOMER TO SQL CUSTOMERTABLE
            			
							try {
								if(custTemp.find(custTemp.getCustfn(), custTemp.getCustln())==false)
								{
									Connection conn = SimpleDataSource.getConnection();
									PreparedStatement pstat = conn.prepareStatement("INSERT INTO CustomerTable (CustomerID, FirstName, LastName) VALUES (?, ?, ?)");
									custCount = custCount + 1;
									custTemp.setCustID((custCount)); 
									pstat.setInt(1, custTemp.getCustID());
									pstat.setString(2, custTemp.getCustfn() );
									pstat.setString(3, custTemp.getCustln());
									try{
									pstat.executeUpdate();}
									catch(SQLException eze)
									{
										System.out.println("help! new not exist insert fail " + eze.getMessage());
									}	
									
								}
								//EXISTING CUSTOMER, ADD INVOICE
								else
								{

										System.out.println("This customer exists");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						
                    	System.out.println("NEW cust! ready");
                    	custCount = custTemp.countCustomers();
            }			
            
            }); 
        
        //5TH CARD, EDIT CUSTOMER
        JPanel editcustCard = new JPanel();
        editcustCard.setBackground(Color.LIGHT_GRAY);
        
        editcustCard.add(editFirstName);
        editcustCard.add(editLastName);
        editcustCard.add(neweditFirstName);
        editcustCard.add(neweditLastName);
        JButton finallly = new JButton("Ready?");
        editcustCard.add(finallly);
        
        editFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println(" edit firstName! ready" + editFirstName.getText());
            }
        }); 
        
        editLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println(" edit lastName! ready" + editLastName.getText());
            }
        }); 
        
        neweditFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("  new edit firstName! ready" + neweditFirstName.getText());
            }
        }); 
        
        neweditLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("new edit lastName! ready" + neweditLastName.getText());
            }
        }); 
        
        finallly.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW button listener
            	
        		Customer oldCust = new Customer (editFirstName.getText(), editLastName.getText());
        		Customer newCust = new Customer (neweditFirstName.getText(), neweditLastName.getText());
        		int cID = 0;
        		try {
					cID = oldCust.findCustomerID();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Customer does not exist " + e1.getMessage());;
				}
        		oldCust.setCustID(cID);
        		newCust.setCustID(cID);
        		
        		try
        		{
				Connection conn = SimpleDataSource.getConnection();
				PreparedStatement pstat = conn.prepareStatement("Update CustomerTable SET FirstName = ? , LastName = ? , WHERE CustomerID = ? ");
				pstat.setString(1, newCust.getCustfn());
				pstat.setString(2, newCust.getCustln());
				pstat.setInt(3, newCust.getCustID());
				pstat.executeUpdate();
        		}
        		catch(SQLException ez)
        		{
        			System.out.println("edit update fail " + ez.getMessage());
        		}
            	
            	
            	
            	
            	
            	System.out.println(" edit! ready");
            }
        }); 
        
        

        //6th card, ADD PAYMENT
        JPanel paymentCard = new JPanel();
        paymentCard.setBackground(Color.ORANGE);
        
        paymentCard.add(paymentFirstName);
        paymentCard.add(paymentLastName);
        paymentCard.add(paymentInvoiceID);
        paymentCard.add(paymentAmount);
        JButton ready3 = new JButton("Ready?");
        paymentCard.add(ready3);
        
        paymentFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("firstName! ready");
            }
        }); 
        paymentLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("lastName! ready");
            }
        }); 
        paymentInvoiceID.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("invoiceID! ready");
            }
        }); 
        paymentAmount.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	System.out.println("NEW! amount");
            }
        }); 

        
        ready3.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	//SEARCH FOR INVOICE, GET AMOUNTDUE, UPDATE INVOICE WITH AMOUNTDUE-PAYMENT
            	
            	Invoice invTemp = new Invoice();
            	double payment = Double.parseDouble(paymentAmount.getText());
            	try {
					invTemp = invTemp.findInvoice(Integer.parseInt(paymentInvoiceID.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	double newBalance = invTemp.getamountDue() - payment; 
            	
            	
            	try {
					Connection conn = SimpleDataSource.getConnection();
					PreparedStatement pstat = conn.prepareStatement("Update InvoiceTable SET AmountDue = ? WHERE InvoiceID = ? ");
					pstat.setDouble(1, newBalance);
					pstat.setInt(2, invTemp.getinvoiceID());
					try
					{
						pstat.executeUpdate();
					}
					catch(SQLException eze)
					{
						System.out.println("updatefail " + eze.getMessage());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("update fail " + e1.getMessage());
				}
            	

            	            	
            	 //NEWready button listener
            	QueryResultWindow paymentResultWindow = new QueryResultWindow();
            	paymentResultWindow.addPaymentQueryLayout();
            	System.out.println("PAYMENT! ready new balance=" + newBalance);
            }
        }); 
        
        //7th card, DELETE CUSTOMER
        JPanel deleteCard  = new JPanel();
        deleteCard.setBackground(Color.MAGENTA);
        deleteCard.add(deleteFirstName);
        deleteCard.add(deleteLastName);
        deleteCard.add(ruSure);
        JButton ready4 = new JButton("Ready?");
        deleteCard.add(ready4);
        
        deleteFirstName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("delete first name" + deleteFirstName.getText());
            }
        }); 
        deleteLastName.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	 //NEW textfield listener
            	
            	System.out.println("delete last name " + deleteLastName.getText());
            }
        }); 
        ruSure.addItemListener(new ItemListener()
        {
 
            public void itemStateChanged(ItemEvent e)
            {
            	System.out.println(" lol");
            	
            	if(ruSure.isSelected())
            	{
            		System.out.println("I am checked, m8");
            	}
            	else
            	{
            		System.out.println("Unchecked");
            	}
            }
        }); 
        
        ready4.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
            	if(ruSure.isSelected())
            	{
            		System.out.println("CHECKED AND READY");
            		Customer custTemp = new Customer (deleteFirstName.getText(), deleteLastName.getText());
            		int cID = 0;
            		try {
						cID = custTemp.findCustomerID();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Delete find fail " + e1.getMessage());;
					}
            		custTemp.setCustID(cID);
            		
            		try
            		{
					Connection conn = SimpleDataSource.getConnection();
					PreparedStatement pstat = conn.prepareStatement("DELETE FROM CustomerTable WHERE CustomerID = ? ");
					pstat.setInt(1, cID);
					pstat.executeUpdate();
            		}
            		catch(SQLException ez)
            		{
            			System.out.println("delete custTable fail " + ez.getMessage());
            		}

            		
            		            		
            	}
            	else
            	{
            		System.out.println("I AM NOT CHECKED AND READY");
            	}
            }
        }); 
        
        

        
        JPanel cards = new JPanel(new CardLayout());
        cards.add(paidCard, cbItems[0]);
        cards.add(unpaidCard, cbItems[1]);
        cards.add(newinvCard, cbItems[2]);
        cards.add(newcustCard,cbItems[3]);
        cards.add(editcustCard, cbItems[4]);
        cards.add(paymentCard, cbItems[5]);
        cards.add(deleteCard, cbItems[6]);
        cb.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, jcb.getSelectedItem().toString());
            }
        });



		mF.add(cbPanel, BorderLayout.NORTH);
		mF.add(cards, BorderLayout.SOUTH);
	    mF.pack();
	    mF.setLocationRelativeTo(null);
	}
}

