import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by Flash on 12-May-17.
 */
public class ESClientUtils {

    private static JestClient jestClient;

    private ESClientUtils() {
    }

    public static JestClient getClient() {
        if (jestClient == null) {
            String connectionUrl = "http://localhost:9200";
            JestClientFactory factory = new JestClientFactory();
            factory.setHttpClientConfig(new HttpClientConfig.Builder(connectionUrl).multiThreaded(true).build());
            jestClient = factory.getObject();
        }
        return jestClient;
    }
}
