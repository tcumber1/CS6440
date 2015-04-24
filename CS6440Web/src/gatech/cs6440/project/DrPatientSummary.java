package gatech.cs6440.project;

import java.io.IOException;

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
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrPatientSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("patient_id");
		System.out.println("in DrPatientSummary"+username);

		
		try{
			session = request.getSession(true);
			String PatientID = username.trim();
			Patient myPatient = new Patient();
			Observation myObs = new Observation();
			myPatient.fetchPatient(PatientID);
			if (myPatient.isPatient()){
				session.setAttribute("patient", myPatient);
				session.setAttribute("obs", myObs);
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

}
