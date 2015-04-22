package gatech.cs6440.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class DrugSearch
 */
@WebServlet("/DrugSearch")
public class DrugSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/eprescriptions";
	
	//these lines need to match your local mysql settings
	static final String USER = "root";
	static final String PASS = "admin";
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrugSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		Map <String, Object> map = new HashMap<String, Object> ();
		String searchTerm = (String)request.getParameter("searchTerm");
		System.out.println(searchTerm);
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
	    	
	    	//STEP 3: Create SQL query
	    	String sql = "Select PROPRIETARYNAME, ACTIVE_NUMERATOR_STRENGTH, ACTIVE_INGRED_UNIT, DOSAGEFORMNAME, PRODUCTNDC "
	    			+ "From eprescriptions.drugs Where PROPRIETARYNAME like '%" + searchTerm + "%';";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	//stmt.close();
	    	List<Map<String, Object>> drugs = new ArrayList<Map<String, Object>>();
	    	while(rs.next()){
	    		Map <String, Object> drug = new HashMap<String, Object>();
	    		String name = rs.getString("PROPRIETARYNAME");
	    		String dosage = rs.getString("ACTIVE_NUMERATOR_STRENGTH") + " " + rs.getString("ACTIVE_INGRED_UNIT");
	    		String dosageForm = rs.getString("DOSAGEFORMNAME");
	    		String productNDC = rs.getString("PRODUCTNDC");
	    		
	    		drug.put("name", name);
	    		drug.put("dosage", dosage);
	    		drug.put("dosageForm", dosageForm);
	    		drug.put("productNDC", productNDC);
	    		
	    		drugs.add(drug);
	    	}
	    	String error = "";
	    	
	    	if(drugs.isEmpty()){
	    		error = "No results were found for " + searchTerm + ". Please try a different drug.";
	    	}
	    	
	    	map.put("error", error);
	    	map.put("drugs", drugs);
	    	
	    	write(response, map);
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
