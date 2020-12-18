package it.michdev.restwebservice.webclient;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Calendar;
import org.springframework.web.util.UriComponentsBuilder;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.utils.filter.PeriodFilter;
import it.michdev.restwebservice.utils.parser.DateParser;

/**
 * La classe <code>FxMarketClient</code> estende <code>WebClient</code>.
 * Rappresenta un HTTP REST client al servizio offerto da fxmarketsapi.com ed
 * implementa i metodi per ottenere i dati necessari.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.webclient.WebClient
 */
public class FxMarketClient extends WebClient {

    private URI requestUri;
    private String currenciesPairsQuery;
    private String accessKey;

    /**
     * Costruttore per la classe <code>FxMarketClient</code>.
     */
    public FxMarketClient() {
        super("https://fxmarketapi.com");
        this.accessKey = AssetsManager.getAccessKey();
        this.currenciesPairsQuery = AssetsManager.getCurrenciesList().getCurrenciesPairsQuery();
    }

    /**
     * Effettua una chiamata per scaricare i dati sulle valute aggiornati in tempo
     * reale.
     * 
     * @return oggetto <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public HttpResponse<String> requestData() {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apilive").queryParam("api_key", accessKey)
                .queryParam("currency", currenciesPairsQuery).build().toUri();
        return downloadData(requestUri);
    }

    /**
     * Effettua una chiamata per scaricare i dati sulle valute in un determinato
     * istante
     * 
     * @param date <code>Calendar</code> dell'istante di cui si vogliono i dati.
     * @return oggetto <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public HttpResponse<String> requestData(Calendar date) {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apihistorical").queryParam("api_key", accessKey)
                .queryParam("date", DateParser.getDateAsString(date, DateParser.YYYYMMDDHHmm))
                .queryParam("interval", "minute").queryParam("currency", currenciesPairsQuery).build().toUri();
        return downloadData(requestUri);
    }

    /**
     * Effettua una chiamata per scaricare i dati sulle valute in un determinato
     * intervallo di tempo.
     * 
     * @param date <code>Calendar</code> intervallo di tempo di cui si vogliono i
     *             dati.
     * @return oggetto <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public HttpResponse<String> requestData(PeriodFilter periodFilter, String quoteCurrenciesQuery) {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apitimeseries").queryParam("api_key", accessKey)
                .queryParam("currency", quoteCurrenciesQuery)
                .queryParam("start_date", DateParser.getDateAsString(periodFilter.getParam().getStartDate(), DateParser.YYYYMMDD))
                .queryParam("end_date", DateParser.getDateAsString(periodFilter.getParam().getEndDate(), DateParser.YYYYMMDD))
                .queryParam("interval", "daily").build().toUri();
        return downloadData(requestUri);
    }

}
