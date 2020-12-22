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
 * Rappresenta un HTTP REST client del servizio offerto da <i>fxmarketsapi.com</i> ed
 * implementa i metodi per ottenere i dati necessari.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.webclient.WebClient
 */
public class FxMarketClient extends WebClient {

    private URI requestUri;
    private String currenciesPairsQuery;
    private String accessKey;

    /**
     * Costruttore per la classe <code>FxMarketClient</code>.
     * Inizializza le variabili d'istanza utili per effettuare chiamate HTTP.
     */
    public FxMarketClient() {
        super("https://fxmarketapi.com");
        this.accessKey = AssetsManager.getAccessKey();
        this.currenciesPairsQuery = AssetsManager.getCurrenciesList().getCurrenciesPairsQuery();
    }

    /**
     * Effettua una richiesta HTTP per scaricare i dati sulle valute aggiornati in tempo
     * reale.
     * 
     * @return <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public HttpResponse<String> requestData() {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apilive").queryParam("api_key", accessKey)
                .queryParam("currency", currenciesPairsQuery).build().toUri();
        return downloadData(requestUri);
    }

    /**
     * Effettua una richiesta HTTP per scaricare i dati sulle valute in un determinato
     * istante di tempo.
     * 
     * @param date <code>Calendar</code> dell'istante di cui si vogliono i dati.
     * @return <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public HttpResponse<String> requestData(Calendar date) {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apihistorical").queryParam("api_key", accessKey)
                .queryParam("date", DateParser.getDateAsString(date, DateParser.YYYYMMDDHHmm))
                .queryParam("interval", "minute").queryParam("currency", currenciesPairsQuery).build().toUri();
        return downloadData(requestUri);
    }

    /**
     * Effettua una richiesta HTTP per scaricare i dati sulle valute di un determinato
     * intervallo di tempo.
     * 
     * @param periodFilter filtro del periodo di cui si vogliono i
     *             dati.
     * @param quoteCurrenciesQuery query delle coppie di valute di cui si vuole ottenere i dati.
     * @return <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public HttpResponse<String> requestData(PeriodFilter periodFilter, String quoteCurrenciesQuery) {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apitimeseries").queryParam("api_key", accessKey)
                .queryParam("currency", quoteCurrenciesQuery)
                .queryParam("start_date",
                        DateParser.getDateAsString(periodFilter.getParam().getStartDate(), DateParser.YYYYMMDD))
                .queryParam("end_date",
                        DateParser.getDateAsString(periodFilter.getParam().getEndDate(), DateParser.YYYYMMDD))
                .queryParam("interval", "daily").build().toUri();
        return downloadData(requestUri);
    }
}
