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
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DrPatientSummary
 */
@WebServlet("/DrPatientSummary")
public class DrPatientSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession session;
	
	
	//these lines need to match your local mysql settings
	static String DB_URL = "";
	static String USER = "";
	static String PASS = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrPatientSummary() {
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
		String username = request.getParameter("patient_id");

		
		try{
			session = request.getSession(true);
			String PatientID = username.trim();
			Patient myPatient = new Patient();
			myPatient.fetchPatient(PatientID);
			if (myPatient.isPatient()){
				session.setAttribute("patient", myPatient);
				getSummary(PatientID);
				response.sendRedirect("DrPatient.jsp");
			}
			else{
				throw new ServletException("Invalid Patient ID: " + username);
			}
		}
		catch(Exception e){
			//System.out.println(e);
			throw new ServletException(e);
		} 
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private static void getSummary(String patientID) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    Map<String, Object> map = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	//System.out.println("Connecting to a selected database...");
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	//System.out.println("Connected server successfully...");

	    	//System.out.println("Inserting records into the table...");
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select Details, Reason, Visit_Reason From doctor Where Patient_ID = '" + patientID + "';";
	    	ResultSet rs = null;
	    	rs = stmt.executeQuery(sql);
		    	while(rs.next())
		    	{
		    		map = new HashMap<String, Object>();
		    		String details = rs.getString("Details");
		    		String reason = rs.getString("Reason");
		    		String visitReason = rs.getString("Visit_Reason");
		    		map.put("details", details);
		    		map.put("reason", reason);
		    		map.put("visitReason", visitReason);
		    		
		    	}
		    session.setAttribute("summary", map);
	    }
		catch(SQLException se){
	    	//Handle errors for JDBC
	    	se.printStackTrace();
	    	throw new ServletException(se);
	    }
	    catch(Exception e){
	    	//Handle errors for Class.forName
	    	e.printStackTrace();
	    	throw new ServletException(e);
	    }
	    finally{
	    	//finally block used to close resources
	    	try{
	    		if(stmt!=null)
	    			conn.close();
	    	}
	    	catch(SQLException se){
	    	
	    	}// do nothing
	    	try{
	    		if(conn!=null)
	    			conn.close();
	    	}
	    	catch(SQLException se){
	    		se.printStackTrace();
	    		throw new ServletException(se);
	    	}//end finally try
	    }//end try
	}

}
