package it.michdev.restwebservice.webclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * La classe astratta <code>WebClient</code> rappresenta un generico REST client
 * che definisce i metodi per effettuare richieste ad un endpoint definito.
 * Questa classe è estesa da <code>FxMarketClient</code>.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.webclient.FxMarketClient
 * @see it.michdev.restwebservice.service.DataService
 */
public abstract class WebClient {

    protected String endpoint;
    private HttpClient httpClient;

    /**
     * Costruttore per la classe <code>WebClient</code>
     * 
     * @param endpoint endpoint a cui effettuare le richieste HTTP
     */
    public WebClient(String endpoint) {
        this.endpoint = endpoint;
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Effettua una chiamata all'endpoint definito utilizzando l'oggetto
     * <code>URI</code>, passato come argomento, e scarica i dati ottenuti come
     * oggetto <code>HttpResponse</code>.
     * 
     * @param requestUri <code>URI</code> dell'endpoint
     * @return <code>HttpResponse</code> contenente i dati ottenuti.
     */
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

    /**
     * Metodo astratto, implementato nelle sottoclassi di <code>WebClient</code>.
     * Costruisce un <code>URI</code> ed ottiene i dati della richiesta HTTP
     * effettuata.
     * 
     * @return <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public abstract HttpResponse<String> requestData();
}
