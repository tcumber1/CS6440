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
import org.json.JSONObject;

/**
 * Servlet implementation class PatientInfo
 */
@WebServlet("/PatientInfo")
public class PatientInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			String PatientID = (String)request.getParameter("id");
			String FHIRResponse = getPatient(PatientID);
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().write(FHIRResponse);
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

	private String getPatient(String id) throws Exception{
		String sURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/3.666643100-01?_format=xml";
		URL myURL = new URL(sURL);
		HttpURLConnection con = (HttpURLConnection) myURL.openConnection();
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String inputLine;
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		while ((inputLine = in.readLine()) != null)
		{
			sb.append(inputLine);
		}
		return sb.toString();
	}
}
