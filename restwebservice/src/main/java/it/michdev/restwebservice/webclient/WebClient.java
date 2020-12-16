package it.michdev.restwebservice.webclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Vector;
import org.springframework.web.util.UriComponentsBuilder;
import it.michdev.restwebservice.core.AssetsManager;

public abstract class WebClient {

    protected String endpoint;
    private HttpClient httpClient;

    public WebClient(String endpoint) {
        this.endpoint = endpoint;
        this.httpClient = HttpClient.newHttpClient();
    }
    
    protected HttpResponse<String> downloadData(URI requestUri) {
        HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).build();
        HttpResponse<String> requestResponse;
        try {
            requestResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return requestResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract HttpResponse<String> requestData();
}
