package com.eprescription.web.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.xpath.*;
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
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		if (username.equalsIgnoreCase("Patient")){
			System.out.println("enter if");
			try{
				String PatientID = password;
				Patient myPatient = new Patient();
				myPatient.fetchPatient(PatientID);
				if (myPatient.isPatient()){
					session.setAttribute("patient", myPatient);
					response.sendRedirect("Patient.jsp");
				}
				else{
					//TODO: got back to login page with error message
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		else if (username.equalsIgnoreCase("Doctor") && password.equalsIgnoreCase("Doctor")){
			// TODO go to doctor page
		}
		
		else if (username.equalsIgnoreCase("Pharmacist") && password.equalsIgnoreCase("Pharmacist")){
			// TODO go to pharmacist page
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
