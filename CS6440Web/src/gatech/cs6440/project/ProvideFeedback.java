package gatech.cs6440.project;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class ProvideFeedback
 */
@WebServlet("/ProvideFeedback")
public class ProvideFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	
	//these lines need to match your local mysql settings
	static String DB_URL = "";
	static String USER = "";
	static String PASS = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvideFeedback() {
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
		Map <String, Object> map = new HashMap<String, Object> ();
		String patientID = request.getParameter("patientID");
		String productID = request.getParameter("drugID");
		String comments = request.getParameter("comments");
		String rating = request.getParameter("rating");
		String message = "An error has occured when trying to add the feedback. \nPLease try again.";
		
		try {
			int feedbackID = getFeedbackID(patientID, productID);
			if(feedbackID == -1){
				int pid = getPID(patientID);
				insertNewFeedback(pid, productID, comments, rating);
				message = "Thank you for providing feedback";
			}
			else{
				updateFeedback(feedbackID, comments, rating);
				message = "Your feedback has been updated.";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("message", message);
		write(response, map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	//returns the feedbackID if feedback from the user for the drug already exists, if not returns -1
	private static int getFeedbackID(String patientID, String productID) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select f.feedbackid "
	    			+ "From feedback as f inner join patientinfo as p on f.pid = p.PID "
	    			+ "Where p.patientid ='" + patientID + "' and drugndc = '" + productID + "';";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	
	    	while(rs.next()){
	    		return rs.getInt("feedbackid");
	    	}
	    	
	    	return -1;
	    	
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

	//adds new feedback to the feedback table
	private static void insertNewFeedback(int pid, String productID, String comments, String ratings) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Insert Into feedback (pid, drugndc, comments, rating) "
	    			+ "Values ('" + pid + "', '" + productID + "', '" + comments + "', '" + ratings + "');";
	    	System.out.println(sql);
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
	
	//updates feedback that already exists
	private static void updateFeedback(int feedbackID, String comments, String rating) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Update feedback "
	    			+ "Set comments = '" + comments + "', rating = '" + rating + "' "
	    			+ "Where feedbackid = " + feedbackID + ";";
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
	
	//return pid for specific patientID
	private static int getPID(String patientID) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    
	    try{
	    	//STEP 1: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");

	    	//STEP 2: Open a connection
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();
	    	
	    	//STEP 3: Create SQL query 
	    	String sql = "Select pid "
	    			+ "From patientinfo "
	    			+ "Where patientid ='" + patientID + "';";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	
	    	while(rs.next()){
	    		return rs.getInt("pid");
	    	}
	    	
	    	return -1;
	    	
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
	
	private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
		response.setContentType("application/json");
		String jsonString = new Gson().toJson(map);
		response.getWriter().write(jsonString);
		response.getWriter().close();
	}
}
