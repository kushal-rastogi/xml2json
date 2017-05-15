import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.scenario.Settings;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import org.elasticsearch.node.Node;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Xml2Json {

    //public static void main(String[] args) throws IOException {
    public String getJSON() throws IOException {
        String url1 = "http://www.mocky.io/v2/590dd64c250000060d807b45";

        URL url = new URL(url1);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String line1 = null;
        while ((line = bufferedReader.readLine()) != null) {
            line1 = line1 + line;
        }
        //System.out.println(line1);
        JSONObject xmlJSONObj = XML.toJSONObject(line1);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
    }


}

