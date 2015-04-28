package gatech.cs6440.project;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * Servlet implementation class FillPrescription
 */
@WebServlet("/FillPrescription")
public class FillPrescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//these lines need to match your local mysql settings
	static String DB_URL = "";
	static String USER = "";
	static String PASS = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillPrescription() {
    	super();
        Properties prop = new Properties();
		InputStream input = null;
		input = Patient.class.getResourceAsStream("props.properties");
		try{
			prop.load(input);
			DB_URL = prop.getProperty("databaseURL");
			USER = prop.getProperty("dbuser");
			PASS = prop.getProperty("dbpassword");
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		finally {
			if ( input != null) {
				try {
					input.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;
		int prescriptionID = Integer.parseInt(request.getParameter("medicineid"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection conn = null;
	    Statement stmt = null;
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);

	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query
	    	String sql = "Update medication "
	    			+ "Set status = 'active' "
	    			+ "Where medicationid = " + prescriptionID + ";";
	    	stmt.executeUpdate(sql);
	    	
	    	message = "The prescription has been update.\nPlease hand the prescription to the patient now.";
		    
		    map.put("message", message);
		    map.put("prescriptionID", prescriptionID);
	    	
	    }
	    catch(SQLException se){
	    	//Handle errors for JDBC
	    	se.printStackTrace();
	    }catch(Exception e){
	    	//Handle errors for Class.forName
	    	e.printStackTrace();
	    }finally{
	    	//finally block used to close resources
	    	try{
	    		if(stmt!=null)
	    			conn.close();
	    	}catch(SQLException se){
	    	}// do nothing
	    	try{
	    		if(conn!=null)
	    			conn.close();
	    	}catch(SQLException se){
	    		se.printStackTrace();
	    	}//end finally try
	    }//end try
	    
	    write(response, map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
		response.setContentType("application/json");
		String jsonString = new Gson().toJson(map);
		response.getWriter().write(jsonString);
		response.getWriter().close();
	}

}
