package accountReceivable;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class Main
{

    public static void main(String[] args)  throws ClassNotFoundException, IOException, SQLException {
    	
    	SimpleDataSource.init("database.properties");
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
}