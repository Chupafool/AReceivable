	package accountReceivable;
	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
	
	
	public class Options 
	{
		private JFrame mF;
		private JLabel header = new JLabel();
		private JLabel promptQ = new JLabel();
		private JLabel instructions = new JLabel("Follow the text field's instructions, then press Save and then Next.");
		private JButton save = new JButton("Save?");
		private JTextField columnLength = new JTextField("How many days is each column? (TYPICALLY 30 DAYS) Press enter");
		private JTextField  columnCount= new JTextField("How many Columns? Assuming your last column is a greater than boundry (int). Press enter");
		private JTextField pPenalty = new JTextField("What % penalty in each successive column? Delimit with '-'");
		
		private String days = "";
		private String tiers = "";
		private String discount = "";
		
		public Options()
		{
			mF= new JFrame("Options");
			mF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			mF.setLayout(new FlowLayout());
			mF.setVisible(true);
	
		}
		
		public void optionsLayout()
		{
			JPanel contentPanel  = new JPanel();
			contentPanel.setSize(300,300);
			GridLayout layout = new GridLayout(0,1);
			layout.setHgap(10);              
			layout.setVgap(10);
			contentPanel.setLayout(layout);  
			

			contentPanel.add(instructions);
			contentPanel.add(columnLength);
			contentPanel.add(columnCount);
			contentPanel.add(pPenalty);
			contentPanel.add(save);
			
			
			
			
			columnLength.addActionListener(new ActionListener() 
	        {
	 
	            public void actionPerformed(ActionEvent e)
	            {
	                //save to options variable
	            	days = columnLength.getText();
	            	System.out.println(days);
	            }
	        }); 

			columnCount.addActionListener(new ActionListener() 
	        {
	 
	            public void actionPerformed(ActionEvent e)
	            {
	                //save to options variable
	            	
	            	System.out.println("lolos2!!!!! " + columnCount.getText());
	            }
	        }); 

			pPenalty.addActionListener(new ActionListener() 
	        {
	 
	            public void actionPerformed(ActionEvent e)
	            {
	                //save to options variable

	            	System.out.println("lolos3!!!!!  " + pPenalty.getText());
	            }
	        }); 

	        save.addActionListener(new ActionListener() 
	        {
	 
	            public void actionPerformed(ActionEvent e)
	            {
	                //write to options.txt
	            	System.out.println("saved!");
	            	
	            	File optionsF = new File("options.txt");
	            	String guts = columnLength.getText() + "," + columnCount.getText() + "," + pPenalty.getText();
	            	
	            	//deleting the file
	            	if(optionsF.exists())
	            	{
						optionsF.delete();
	            	}
	            	//replacing the file with current parameters
	            	try 
	            	{
						optionsF.createNewFile();

						FileWriter fw = new FileWriter(optionsF.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(guts);
						bw.close();
						System.out.println("Saved to 'options.txt'");
					} 
	            	catch (IOException ez) 
	            	{
						
						ez.printStackTrace();
					}
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            }
	        }); 
			
			
			contentPanel.setBackground(Color.MAGENTA);
			
			mF.add(contentPanel);
		    mF.pack();
		    mF.setLocationRelativeTo(null);
	
		}
		
		
	}
