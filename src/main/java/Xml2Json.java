import org.json.JSONObject;
import org.json.XML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ResourceBundle;

public class Xml2Json {

    public String getJSON() throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String url1 = rb.getString("urlName");
        URL url = new URL(url1);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String line1 = null;
        while ((line = bufferedReader.readLine()) != null) {
            line1 = line1 + line;
        }
        System.out.println(line1);
        JSONObject xmlJSONObj = XML.toJSONObject(line1);
        System.out.println(xmlJSONObj);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
    }
}

