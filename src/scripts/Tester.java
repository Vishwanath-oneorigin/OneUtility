package scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tester {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.oneorigin.us/v2-q-ai/transcripts/verify_user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String cookieHeader = connection.getHeaderField("token_transcriptqa");
                if (cookieHeader != null) {
                    // Do something with the cookie value
                    System.out.println("Set-Cookie: " + cookieHeader);
                } else {
                    System.out.println("No Set-Cookie header found in the response.");
                }

                // Read the response content
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Process the response content
                System.out.println("Response content: " + response.toString());
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

