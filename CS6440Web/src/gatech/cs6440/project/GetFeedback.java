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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetFeedback
 */
@WebServlet("/GetFeedback")
public class GetFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String DB_URL = "jdbc:mysql://localhost/eprescriptions";
	private static String USER = "root";
	private static String PASS = "admin";
	
	private static String patientID;
	private static String NDC;
	private static String productID;
	private static String drugName;
	private static String dosageForm;
	private static String dosage;
	
	
	private static HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFeedback() {
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
		session = request.getSession(true);
		patientID = (String) request.getParameter("patientID");
		NDC = (String) request.getParameter("NDC");
		drugName = (String) request.getParameter("drugName");
		dosageForm = (String) request.getParameter("dosageForm");
		dosage = (String) request.getParameter("dosage");
		
		

		
		try {
			if(!checkNDC(NDC)){
				//Insert drug into database
				//For some reason some of the NDC that are being returned at not in the database that we downloaded from the FDA
				insertNDCIntoDB();
			}
			
			Map<String, Object> drug = new HashMap<String, Object>();
			drug.put("drugName", drugName);
			drug.put("productID", productID);
			session.setAttribute("drugInfo", drug);
			session.setAttribute("patientID", patientID);
			
			getPatientDrugFeedback();
			
			getOthersDrugFeedback();
			
			response.sendRedirect("Feedback.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	//Checks in the NDC provided is in the database
	private static boolean checkNDC(String NDC) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select PRODUCTID From drugs Where PRODUCTNDC = '"+NDC+"'";
	    	ResultSet rs = null;
	    	rs = stmt.executeQuery(sql);
	    	while(rs.next())
	    	{
	    		productID = rs.getString("PRODUCTID"); 
	    		return true;
	    	}
	    	productID = NDC;
	    	return false;
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
	
	//Inserts the NDC into the datatbase using the NDC as the PRODUCTID
	private static void insertNDCIntoDB() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Insert Into drugs (PRODUCTID, PRODUCTNDC, PROPRIETARYNAME, DOSAGEFORMNAME, ACTIVE_NUMERATOR_STRENGTH, ACTIVE_INGRED_UNIT) "
	    			+ "Values('" + NDC+ "', '" + NDC+ "', '" + drugName+ "', '" + dosageForm + "', '" + dosage+"', '');";
	    	stmt.executeUpdate(sql);

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
	
	//Adds the feedback for the current drug of the patient if it exists
	private static void getPatientDrugFeedback() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);

	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select rating, comments "
	    			+ "From feedback as f inner join patientinfo as p on f.pid = p.PID "
	    			+ "Where p.patientid ='" + patientID + "' and drugndc = '" + productID + "';";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	Map<String, Object> patientFeedback = new HashMap<String, Object>();
	    	
	    	while(rs.next()){
	    		String comment = rs.getString("comments");
	    		String rating = rs.getString("rating");
	    		
	    		patientFeedback.put("comment", comment);
	    		patientFeedback.put("rating", rating);
	    		
	    	}
	    	
	    	session.setAttribute("userFeedback", patientFeedback);

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

	private static void getOthersDrugFeedback() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select rating, comments "
	    			+ "From feedback as f inner join patientinfo as p on f.pid = p.PID "
	    			+ "Where p.patientid !='" + patientID + "' and drugndc = '" + productID + "';";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	List<Map<String, Object>> othersFeedback = new ArrayList<Map<String, Object>>();
	    	
	    	while(rs.next()){
	    		Map<String, Object> patientFeedback = new HashMap<String, Object>();
	    		
	    		String comment = rs.getString("comments");
	    		String rating = rs.getString("rating");
	    		
	    		patientFeedback.put("comment", comment);
	    		patientFeedback.put("rating", rating);
	    		
	    		othersFeedback.add(patientFeedback);
	    	}
	    	
	    	session.setAttribute("othersFeedback", othersFeedback);

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
