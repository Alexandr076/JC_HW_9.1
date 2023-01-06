import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
        final ObjectMapper mapper = new ObjectMapper();

        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(URL);

        httpGet.setHeader(HttpHeaders.ACCEPT,
                ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        List<Cat> responce = mapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<List<Cat>>() {});
        responce.stream().forEach(
            e -> {
                if (e.getUpvotes()>0) {
                    System.out.println(e.toString());
                }
            }
        );
    }
}
