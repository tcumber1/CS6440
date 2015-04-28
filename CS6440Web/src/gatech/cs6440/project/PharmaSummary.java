package gatech.cs6440.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PharmaSummary
 */
@WebServlet("/PharmaSummary")
public class PharmaSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmaSummary() {
        super();
        // TODO Auto-generated constructor stub
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
				response.sendRedirect("PharmaPatientDetail.jsp");
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
