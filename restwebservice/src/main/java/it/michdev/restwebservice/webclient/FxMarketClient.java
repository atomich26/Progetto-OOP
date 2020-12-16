package it.michdev.restwebservice.webclient;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Calendar;
import org.springframework.web.util.UriComponentsBuilder;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.utils.filter.PeriodFilter;
import it.michdev.restwebservice.utils.parser.DateParser;

public class FxMarketClient extends WebClient {

    private URI requestUri;
    private String currenciesPairsQuery;
    private String accessKey;

    public FxMarketClient() {
        super("https://fxmarketapi.com");
        this.accessKey = AssetsManager.getAccessKey();
        this.currenciesPairsQuery = AssetsManager.getCurrenciesList().getCurrenciesPairsQuery();
    }

    public HttpResponse<String> requestData() {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apilive").queryParam("api_key", accessKey)
                .queryParam("currency", currenciesPairsQuery).build().toUri();
        return downloadData(requestUri);
    }

    public HttpResponse<String> requestData(Calendar date) {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apihistorical").queryParam("api_key", accessKey)
                .queryParam("date", DateParser.getDateAsString(date, DateParser.YYYYMMDDHHmm))
                .queryParam("interval", "minute").queryParam("currency", currenciesPairsQuery).build().toUri();
        return downloadData(requestUri);
    }

    public HttpResponse<String> requestData(PeriodFilter filterPeriod, ArrayList<String> quoteCurrencies) {
        requestUri = UriComponentsBuilder.fromUriString(endpoint).path("apitimeseries").queryParam("api_key", accessKey)
                .queryParam("currency", currenciesPairsQuery)
                .queryParam("start_date", DateParser.getDateAsString(filterPeriod.getParam().getStartDate(), DateParser.YYYYMMDD))
                .queryParam("end_date", DateParser.getDateAsString(filterPeriod.getParam().getEndDate(), DateParser.YYYYMMDD))
                .build().toUri();
        return downloadData(requestUri);
    }

}
