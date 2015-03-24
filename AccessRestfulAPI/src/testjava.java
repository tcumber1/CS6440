import java.io.*;
import java.net.*;

//Code to read Medication FHIR API and store it to a string for parsing 

public class testjava {
      
      public static void main(String[] args) throws Exception
      {
        String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/MedicationPrescription?medication.name=protamine";
        URL myurl = new URL(httpsURL);
        HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
        InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String inputLine;
     
        while ((inputLine = in.readLine()) != null)
        {
          System.out.println("\n"+inputLine);
          sb.append(inputLine);
        }
        System.out.println("\n"+sb.toString());
        in.close();
      }
}   
