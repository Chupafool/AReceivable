package accountReceivable;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

public class test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				    	try {
							SimpleDataSource.init("database.properties");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        try 
				        {
				        	MenuWindow menu = new MenuWindow();
				        	menu.MenuWindowLayout();
				        } 
				        catch (Exception e) 
				        {
				            e.printStackTrace();
				        }

				    }
				});
			}
}
