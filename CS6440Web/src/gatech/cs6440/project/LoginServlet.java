package gatech.cs6440.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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
		if (request.getParameter("userName").trim().toUpperCase().equals("PATIENT"))
		{
			try
			{
				String PatientID = (String)request.getParameter("password");
				Patient myPatient = new Patient();
				DataAccess da = new DataAccess();
				myPatient = da.getPatient(PatientID);
				if (myPatient.isPatient()){
					session.setAttribute("patient", myPatient);
				}
				else
				{
					throw new ServletException("Patient ID not found: " + PatientID);
				}
				ArrayList<Medication> medList = new ArrayList<Medication>();
				medList = da.getMedicationInfo(PatientID);
				
				session.setAttribute("medicationList", medList);
				ArrayList<Problem> probList =  new ArrayList<Problem>();
				probList = da.getProblemInfo(PatientID);
				session.setAttribute("problemList", probList);

				ArrayList<Observation> obList =  new ArrayList<Observation>();
				obList = da.getObservationInfo(PatientID);
				session.setAttribute("ObservationList", obList);
				String url = "PatientDetail.jsp?id=" + PatientID; 
				response.sendRedirect(url);
			}
			catch (Exception e)
			{
				//System.out.println(e);
				throw new ServletException(e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	


}
