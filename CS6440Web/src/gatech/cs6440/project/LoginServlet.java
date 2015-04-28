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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static HttpSession session;
    
	
	//these lines need to match your local mysql settings
    static String DB_URL = "";
	static String USER = "";
	static String PASS = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String username = request.getParameter("userName");
        String password = request.getParameter("password");
		if (username.equalsIgnoreCase("PATIENT"))
		{
			try
			{
				String PatientID = (String)request.getParameter("password");
				Patient myPatient = new Patient();

				myPatient.fetchPatient(PatientID);
				if (myPatient.isPatient()){
					session.setAttribute("patient", myPatient);
				}
				else
				{
					throw new ServletException("Patient ID not found: " + PatientID);
				}
				ArrayList<Medication> medList = new ArrayList<Medication>();
				medList = myPatient.getMyMedication();
				
				session.setAttribute("medicationList", medList);
				ArrayList<Problem> probList =  new ArrayList<Problem>();
				probList = myPatient.getMyProblems();
				session.setAttribute("problemList", probList);

				ArrayList<Observation> obList =  new ArrayList<Observation>();
				obList = myPatient.getMyObservations();
				session.setAttribute("ObservationList", obList);

				ArrayList<Allergy> allgeryList =  new ArrayList<Allergy>();
				allgeryList = myPatient.getMyAllergies();
				session.setAttribute("AllergyList", allgeryList);

				
				String url = "PatientDetail.jsp?id=" + PatientID; 
				response.sendRedirect(url);
			}
			catch (Exception e)
			{
				//System.out.println(e);
				throw new ServletException(e);
			}
		}
        else if (username.equalsIgnoreCase("Doctor") && password.equalsIgnoreCase("Doctor")){
        	try {
				getDoctors();
			} catch (Exception e) {
				throw new ServletException(e);
			}
            response.sendRedirect("DrAppointments.jsp");
        }
        
        else if (username.equalsIgnoreCase("Pharmacist") && password.equalsIgnoreCase("Pharmacist")){
        	try {
				getPrescriptions();
			} catch (Exception e) {
				throw new ServletException(e);
			}
            response.sendRedirect("PharmaPatient.jsp");
        }
        else
        {
        	throw new ServletException("Invalid UserName: " + username + " or Password: " + password);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private static void getDoctors () throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    ArrayList<Map<String, Object>> drPatients = new ArrayList<Map<String, Object>>();
	    
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
	    	String sql = "Select Patient_ID, Patient_Name, Appointment From doctor";
	    	ResultSet rs = null;
	    	rs = stmt.executeQuery(sql);
		    	while(rs.next())
		    	{
		    		Map<String, Object> map = new HashMap<String, Object>();
		    		String patientID = rs.getString("Patient_ID");
		    		String patientName = rs.getString("Patient_Name");
		    		String appointment = rs.getString("Appointment");
		    		map.put("patientID", patientID);
		    		map.put("patientName", patientName);
		    		map.put("appointment", appointment);
		    		
		    		drPatients.add(map);
		    	}
		    session.setAttribute("patientList", drPatients);
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

	private static void getPrescriptions() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    ArrayList<Map<String, Object>> prescriptions = new ArrayList<Map<String, Object>>();
	    
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
	    	String sql = "Select p.patientid, d.PROPRIETARYNAME, p.firstname, p.lastname "
	    			+ "From medication as m inner join drugs as d on m.productid = d.PRODUCTID inner join patientinfo as p on p.pid = m.pid "
	    			+ "Where m.status = 'pickup' "
	    			+ "Group by p.patientid;";
	    	ResultSet rs = null;
	    	rs = stmt.executeQuery(sql);
		    	while(rs.next())
		    	{
		    		Map<String, Object> map = new HashMap<String, Object>();
		    		String patientID = rs.getString("patientid");
		    		String drugName = rs.getString("PROPRIETARYNAME");
		    		String patientName = rs.getString("firstname") + " " + rs.getString("lastname");
		    		map.put("patientID", patientID);
		    		map.put("drugName", drugName);
		    		map.put("patientName", patientName);
		    		
		    		prescriptions.add(map);
		    	}
		    session.setAttribute("prescriptions", prescriptions);
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
