package it.michdev.restwebservice.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import ch.qos.logback.core.filter.Filter;
import it.michdev.restwebservice.exception.CurrencyNotFoundException;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.exception.InvalidPeriodException;
import it.michdev.restwebservice.model.HistoricalQuote;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.model.dataseries.LiveSeries;
import it.michdev.restwebservice.model.dataseries.TimeSeries;
import it.michdev.restwebservice.utils.adapter.HistoricalDataAdapter;
import it.michdev.restwebservice.utils.adapter.LiveDataAdapter;
import it.michdev.restwebservice.utils.filter.CurrencyFilter;
import it.michdev.restwebservice.utils.filter.PeriodFilter;
import it.michdev.restwebservice.utils.parser.JsonParser;
import it.michdev.restwebservice.utils.time.Period;
import it.michdev.restwebservice.webclient.FxMarketClient;

@Component
public final class DataService {

    private static ArrayList<LiveQuote> liveDataSet = new ArrayList<>();
    private static FxMarketClient webClient = new FxMarketClient();
    public static Calendar lastUpdate;

    public static void createDataSet() {
        // Scarica i dati aggiornati
        String liveResponse = webClient.requestData().body().toString();

        // Aggiorna la data esatta dell'ultimo aggiornamento
        lastUpdate = Calendar.getInstance();

        // Copia la data e diminuisce l'ora di 2
        Calendar previousDate = (Calendar) lastUpdate.clone();
        previousDate.add(Calendar.HOUR, -2);

        // Scarica i dati risalenti a 2 ore prima(le API sono in GMT) della data
        // dell'ultimo aggiornamento
        String previousResponse = webClient.requestData(previousDate).body().toString();

        // Crea la lista di oggetti LiveQuote
        LiveDataAdapter liveDataAdapter = new LiveDataAdapter(liveResponse, previousResponse);
        liveDataSet.addAll(liveDataAdapter.createList());
    }

    public static void updateDataSet() {
        // Scarica i dati aggiornati
        String liveResponse = webClient.requestData().body().toString();
        lastUpdate = Calendar.getInstance();

        // Effettua il parsing dei dati scaricati
        TypeReference<HashMap<String, Double>> mapTypeRef = new TypeReference<HashMap<String, Double>>() {};
        HashMap<String, Double> updatedList = JsonParser.deserialize(liveResponse, "price", mapTypeRef);
        
        // Aggiorna ogni oggetto presente nell'arraylist Dataset con i nuovi valori
        for (LiveQuote liveQuote : liveDataSet) {
            liveQuote.updateQuote(updatedList.get(liveQuote.getCurrencyPair()));
        }
    }

    public static LiveSeries getOverview() {
        LiveSeries liveSeries = new LiveSeries();
        liveSeries.setDataSeries(liveDataSet);
        return liveSeries;
    }

    public static LiveSeries getLiveQuoteSeries(String baseCurrency) throws CurrencyNotFoundException {
        CurrencyFilter currencyFilter = new CurrencyFilter(baseCurrency);
        LiveSeries liveSeries = new LiveSeries(baseCurrency);
        liveSeries.setDataSeries(FilterService.filterLiveQuotes(currencyFilter, liveDataSet));
        return liveSeries;
    }

    public static LiveSeries getLiveQuoteSeries(String baseCurrency, Set<String> quotesCurrencies)
            throws CurrencyNotFoundException {
        CurrencyFilter currencyFilter = new CurrencyFilter(baseCurrency, quotesCurrencies);
        LiveSeries liveSeries = new LiveSeries(baseCurrency);
        liveSeries.setDataSeries(FilterService.filterLiveQuotes(currencyFilter, liveDataSet));
        return liveSeries;
    }

    public static TimeSeries getHistoricalSeries(String baseCurrency, String bodyRequest)
            throws InvalidPeriodException, IllegalDatePatternException {
      
        return null;
    }

    public static HistoricalQuote getHistoricalSeries(String baseCurrency, String quoteCurrencies,
            Period selectPeriod) {
        return null;
    }
}