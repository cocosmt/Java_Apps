import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Call {
	
	public static String get_Method(URL link) {
		
		 String result = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) link.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(link.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                result = informationString.toString();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return result;
	}

    public static void main(String[] args) {

        Call m1 = new Call();

			try {
			URL	url = new URL("https://api.agify.io?name=samet");
				Call.get_Method(url);
				System.out.println(m1.get_Method(url));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
    }
	}