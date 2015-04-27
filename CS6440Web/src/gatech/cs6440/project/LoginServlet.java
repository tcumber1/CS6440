package gatech.cs6440.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
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
    private HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		
		// Get config Properties
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			input = LoginServlet.class.getResourceAsStream("props.properties");
			
			prop.load(input);
			session.setAttribute("fhirURL", prop.getProperty("fhirURL"));
			session.setAttribute("databaseURL", prop.getProperty("databaseURL"));
			session.setAttribute("dbuser", prop.getProperty("dbuser"));
			session.setAttribute("dbpassword", prop.getProperty("dbpassword"));
			
		}
		catch (IOException ex){
			ex.printStackTrace();
			throw new ServletException("Error reading config.properties file");
		}
		finally {
			if ( input != null) {
				try {
					input.close();
				}
				catch(IOException e) {
					e.printStackTrace();
					throw new ServletException("Error reading config.properties file");
				}
			}
		}
		
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
            response.sendRedirect("DrAppointments.jsp");
        }
        
        else if (username.equalsIgnoreCase("Pharmacist") && password.equalsIgnoreCase("Pharmacist")){
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
	


}
