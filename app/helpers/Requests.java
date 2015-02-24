package helpers;

import org.codehaus.jackson.JsonNode;
import play.Logger;
import play.libs.Json;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Requests {

    /**
     * This does a GET request and return it in JSON response
     * @param url Url to GET
     * @return JsonNode object
     */
    public static JsonNode get(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return Json.parse(sb.toString());
            }

        } catch (MalformedURLException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
