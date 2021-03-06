package gatech.cs6440.project;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class CreatePrescription
 */
@WebServlet("/CreatePrescription")
public class CreatePrescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	
	//these lines need to match your local mysql settings
	static String DB_URL = "";
	static String USER = "";
	static String PASS = "";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePrescription() {
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
		// TODO Auto-generated method stub
		String drugNDC =  (String)request.getParameter("drugNDC");
		String drugAmount = (String)request.getParameter("drugAmount");
		String numRefills = (String)request.getParameter("numRefills");
		String patientID = (String)request.getParameter("patientID");
		int pid = 0;
		String drugProductID = "";
		
		String message = "There was an error saving the prescription, please try again. \n If you continue recieving this error please report it to your local administrator.";
		Map <String, Object> map = new HashMap<String, Object> ();
		
		Connection conn = null;
	    Statement stmt = null;
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	System.out.println("Connecting to a selected database...");
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	System.out.println("Connected server successfully...");

	    	System.out.println("Inserting records into the table...");
	    	stmt = conn.createStatement();
	    	
	    	String getPatientSql = "Select PID From patientinfo Where patientid = '" + patientID + "';";
	    	ResultSet rs = stmt.executeQuery(getPatientSql);

	    	while(rs.next()){
	    		pid = rs.getInt("PID");
	    		
	    	}
	    	
	    	String getDrugProductID = "Select PRODUCTID From drugs Where PRODUCTNDC = '" + drugNDC + "';";
	    	rs = stmt.executeQuery(getDrugProductID);
	    	
	    	while(rs.next()){
	    		drugProductID = rs.getString("PRODUCTID");
	    		
	    	}
	    	
	    	String date_prescribed = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
	    	
	    	String createPrescriptionSql = "Insert Into medication(pid, productid, quantity, refills, date_prescribed, prescriber, status) "
	    			+ "Values(" + pid + ", '" + drugProductID + "', " + drugAmount + ", " + numRefills + ", '" + date_prescribed +"', 'Dr. GATECH', 'pickup');";
	    	System.out.println(createPrescriptionSql);
	    	stmt.executeUpdate(createPrescriptionSql);
	    	
	    	message = "The prescription has been created";
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
	    
    	
    	map.put("message", message);
    	
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
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
		response.getWriter().close();
	}

}