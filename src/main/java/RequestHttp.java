import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RequestHttp {
    private final URI uriGet = URI.create("https://eventsync.portaltecsinapse.com.br/public/recrutamento/input?email=joicearaujo10@gmail.com");
    private final URI uriPost = URI.create("https://eventsync.portaltecsinapse.com.br/public/recrutamento/finalizar?email=joicearaujo10@gmail.com");
    private final HttpClient client = HttpClient.newHttpClient();

    public void getRequest() {
        try {
            final String jsonPath = "src/main/resources/recrutamento.json";

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uriGet)
                    .GET()
                    .build();

            client.send(request, HttpResponse
                    .BodyHandlers.ofFile(
                            Path.of(jsonPath),
                            StandardOpenOption.CREATE,
                            StandardOpenOption.WRITE,
                            StandardOpenOption.TRUNCATE_EXISTING
                    )
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void postRequest(String param) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uriPost)
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(param))
                .build();

        System.out.print(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
    }
}


