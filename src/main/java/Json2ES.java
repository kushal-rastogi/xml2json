import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ResourceBundle;

public class Json2ES {

    public static void main(String[] args) throws IOException, ParseException {
        // Reading properties from config.properties
        ResourceBundle rb = ResourceBundle.getBundle("config");

        System.out.println("Fetching index and type name from property file");
        String indexName = rb.getString("indexName");
        String typeName = rb.getString("typeName");

        System.out.println("Creating connection using jest client with ESClient utils");
        JestClient jestClient = ESClientUtils.getClient();
        System.out.println("Creating object of xml2json class");
        Xml2Json xml2Json = new Xml2Json();
        String s = xml2Json.getJSON();

        JSONObject jsonObject1 = new JSONObject(s);
        JSONObject parts = jsonObject1.getJSONObject("PARTS");
        JSONArray part = parts.getJSONArray("PART");

       for (int i =1 ; i< part.length();i++) {
           JSONObject item = part.getJSONObject(i);
            PART source = new PART();
            source.setCOST(String.valueOf(item.get("COST")));
            source.setITEM(String.valueOf(item.get("ITEM")));
            source.setMANUFACTURER(String.valueOf(item.get("MANUFACTURER")));
            source.setMODEL(String.valueOf(item.get("MODEL")));

            // Inserting data in Elastic Search
            Index index = new Index.Builder(source).index(indexName).type(typeName).id(String.valueOf(i)).build();
            jestClient.execute(index);
        }


        // Creating JestClient object using ESClientUtils
        /*JestClient jestClient = ESClientUtils.getClient();
        jestClient.execute(new CreateIndex.Builder(indexName).build());

        *//*Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("number_of_shards",15);
        settingsBuilder.put("number_of_replicas",2);
        jestClient.execute(new CreateIndex.Builder(indexName).settings(settingsBuilder.build().getAsMap()).build());*//*

        String mappings = "{ \"study\" : " +
                "           {\n" +
                "            \"properties\": {\n" +
                "               \"author\": {\n" +
                "                  \"type\": \"text\",\n" +
                "                  \"fields\": {\n" +
                "                     \"keyword\": {\n" +
                "                        \"type\": \"keyword\",\n" +
                "                        \"ignore_above\": 256\n" +
                "                     }\n" +
                "                  }\n" +
                "               },\n" +
                "               \"id\": {\n" +
                "                  \"type\": \"long\"\n" +
                "               },\n" +
                "               \"title\": {\n" +
                "                  \"type\": \"text\",\n" +
                "                  \"fields\": {\n" +
                "                     \"keyword\": {\n" +
                "                        \"type\": \"keyword\",\n" +
                "                        \"ignore_above\": 256\n" +
                "                     }\n" +
                "                  }\n" +
                "               }\n" +
                "            }\n" +
                "         }" +
                " }";

        // Create mapping for type in index
        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, mappings).build();
        jestClient.execute(putMapping);

        String authorName = "umang";
        int id = 1;
        String title = "Java";

        *//*PART source = new PART();
        source.setCOST();
        source.setITEM();
        source.setMANUFACTURER();
        source.setMODEL();*//*

        String source = jsonBuilder()
                .startObject()
                .field("author", authorName)
                .field("id", id)
                .field("title", title)
                .endObject().string();

        // Inserting data in Elastic Search
        Index index = new Index.Builder(source).index(indexName).type(typeName).id(String.valueOf(id)).build();
        jestClient.execute(index);

        String query = "{\n" +
                "   \"query\": {\n" +
                "      \"match_all\": {}\n" +
                "   }\n" +
                "}";

        // For Searching in Elastic Search
        Search.Builder searchBuilder = new Search.Builder(query).addIndex(indexName);
        SearchResult result = null;
        try {
            result = jestClient.execute(searchBuilder.build());
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

        JsonObject obj = (JsonObject) result.getJsonObject().get("hits");
        JsonElement records = obj.get("total");
        int total_records = records.getAsInt();
        //System.out.println(total_records);
        JsonArray objArry = obj.getAsJsonArray("hits");
        for (JsonElement jsonarry: objArry) {
            System.out.println(jsonarry.getAsJsonObject().get("_source").getAsJsonObject().get("author"));
        }*/

    }
}

