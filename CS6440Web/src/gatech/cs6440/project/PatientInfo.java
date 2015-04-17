package gatech.cs6440.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import gatech.cs6440.project.Patient;

/**
 * Servlet implementation class PatientInfo
 */
@WebServlet("/PatientInfo")
public class PatientInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			session = request.getSession(true);
			String PatientID = (String)request.getParameter("id");
			Patient myPatient = new Patient();
			myPatient.fetchPatient(PatientID);
			if (myPatient.isPatient()){
				session.setAttribute("patient", myPatient);
				//response.sendRedirect("Patient.jsp");
				
			}
			else{
				//TODO: got back to login page with error message
			}
		}
		catch (Exception e)
		{
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

}
