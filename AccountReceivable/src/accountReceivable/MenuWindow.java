package accountReceivable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.*;


public class MenuWindow
{
	//loads frame
	private JFrame mF;
	private JLabel header = new JLabel();
	private JLabel body = new JLabel();
	private JButton queryButton = new JButton("Queries");
	private JButton optionsButton = new JButton("Options");
	private JButton DBinit = new JButton("SQL DB Initialization");
	private JButton readMe = new JButton("ReadME");
	private JButton quitButton = new JButton("Quit");
	public FirmDB ARDB;
	
	
	public MenuWindow() 
	{
		mF= new JFrame("AR");
		mF.setResizable(false);
		mF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mF.setLayout(new BorderLayout());
		mF.setVisible(true);

	}
	//method that loads Panels onto the frame.
	public void MenuWindowLayout()
{	
		JPanel textP = new JPanel(new FlowLayout());
		header.setText("Group 123's Account Receivable Program");
		header.setFont(new Font("Serif", Font.BOLD, 18));

		textP.add(header);
		textP.setBackground(Color.RED);

		JPanel contentPanel  = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		layout.setHgap(10);              
		layout.setVgap(10);
		contentPanel.setLayout(layout);  
		
		contentPanel.add(queryButton);
		contentPanel.add(optionsButton);
		contentPanel.add(DBinit);
		contentPanel.add(readMe);
		contentPanel.add(quitButton);
		
		queryButton.setToolTipText("Select this to update the database or run a Query.");
		optionsButton.setToolTipText("Select this to open the Options menu.");
		DBinit.setToolTipText("Select this to intialize the SQL database to default values.");
		readMe.setToolTipText("Select this to understand how the program works");
		quitButton.setToolTipText("Select this to Quit.");
				
		//Add action listener to button
        queryButton.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the Querybutton");
                PromptWindow lolz = new PromptWindow();
                lolz.promptLayout();
            }
        }); 
		
		
        optionsButton.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the Optionsbutton");
                Options lolzz = new Options();
                lolzz.optionsLayout();
            }
        }); 
        
        DBinit.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when DBinit button is pressed
                System.out.println("You initialized the SQL database!");
                try 
                {
                	
            		Connection conn = SimpleDataSource.getConnection();
            		Statement stat = conn.createStatement();
            		ARDB = new FirmDB();
            		Invoice tempI = new Invoice();
            		Customer tempC = new Customer();
            		int invCount = tempI.countInvoices();
            		int custCount = tempC.countCustomers();
            				
				} 
                catch (SQLException e1) 
                {
                	
					System.out.println("Initializing went wrong!");	
			
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }); 
        
        readMe.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when readme is selected
                System.out.println("You clicked readMe, you should be brought to the readme");
                System.exit(0);
            }
        }); 
		
		
		
        quitButton.addActionListener(new ActionListener() 
        {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked quitbutton, you should be returned to the IDE");
                System.exit(0);
            }
        }); 
		
		
		contentPanel.setBackground(Color.ORANGE);
		
		mF.add(contentPanel, BorderLayout.SOUTH);
		mF.add(textP, BorderLayout.NORTH);
	    mF.pack();
		
	}
}



