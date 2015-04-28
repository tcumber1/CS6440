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
 * Servlet implementation class AllPatientServlet
 */
@WebServlet("/AllPatientServlet")
public class AllPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession session;
	private static ArrayList<Map<String, Object>> patients;
	
	
	//these lines need to match your local mysql settings
	static String DB_URL = "";
	static String USER = "";
	static String PASS = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllPatientServlet() {
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
		System.out.println("here");
		
		try {
			session = request.getSession(true);
			getAllPatientsFromDB();
			response.sendRedirect("DrPatientSelection.jsp");
		} catch (Exception e) {
			throw new ServletException(e);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private static void getAllPatientsFromDB() throws Exception{

		Connection conn = null;
	    Statement stmt = null;
	    
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	//System.out.println("Connecting to a selected database...");
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	//System.out.println("Connected server successfully...");

	    	//System.out.println("Inserting records into the table...");
	    	stmt = conn.createStatement();
	    	patients = new ArrayList<Map<String, Object>>();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select patientid, firstname, lastname From patientinfo;";
	    	ResultSet rs = null;
	    	rs = stmt.executeQuery(sql);
	    	while(rs.next())
	    	{
	    		Map<String, Object> patient = new HashMap<String, Object>();
	    		String patientID = rs.getString("patientid");
	    		String firstName = rs.getString("firstname");
	    		String lastName = rs.getString("lastName");
	    		patient.put("patientID", patientID);
	    		patient.put("fullName", firstName + " " + lastName);
	    		System.out.println(firstName + " " + lastName);
	    		
	    		patients.add(patient);

	    	}
	    	session.setAttribute("patients", patients);
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
