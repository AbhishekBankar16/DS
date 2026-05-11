import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CalculatorClient {

    public static void main(String[] args) {

        try {

            int a = 10;
            int b = 20;

            // URL Request
            URL url = new URL(
                    "http://localhost:8000/add?a=" + a + "&b=" + b);

            // Open connection
            HttpURLConnection con =
                    (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {

                response.append(inputLine);
            }

            in.close();

            // Print server response
            System.out.println("Response from Server: "
                    + response.toString());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}