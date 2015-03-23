import java.io.*;
import java.net.*;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLPeerUnverifiedException;

public class Testing {
	
	/*
	public static void main(String[] args) throws IOException {
        URL url = new URL("https://taurus.i3l.gatech.edu:8443/HealthPort/fhir");
        //https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient
        String query = "Patient/";

        //make connection
        URLConnection urlc = url.openConnection();

        //use post mode
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false);

        //send query
        PrintStream ps = new PrintStream(urlc.getOutputStream());
        ps.print(query);
        ps.close();

        //get result
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc
            .getInputStream()));
        String output = null;
        while ((output=br.readLine())!=null) {
            System.out.println(output);
        }
        br.close();
    }


*/
	
	
	
	
	  public static void main(String[] args)
	  throws Exception
	  {
	    String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient";
	    URL myurl = new URL(httpsURL);
	    HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
	    InputStream ins = con.getInputStream();
	    InputStreamReader isr = new InputStreamReader(ins);
	    BufferedReader in = new BufferedReader(isr);
	 
	    String inputLine;
	 
	    while ((inputLine = in.readLine()) != null)
	    {
	      System.out.println(inputLine);
	    }
	 
	    in.close();
	  }
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
