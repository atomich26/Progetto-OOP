package it.michdev.restwebservice.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.exception.CurrencyNotFoundException;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.exception.InvalidPeriodException;
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

/**
 * La classe <code>DataService</code> rappresenta un servizio per l'applicazione
 * Spring. Contiene i metodi per ottenere i dati da un dataset locale, scaricato
 * per i valori aggiornati, o per inoltrare richieste <code>HTTP</code< alle
 * fxmarketapi.com.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.service.FilterService
 * @see it.michdev.restwebservice.service.StatisticsService
 */
@Component
public final class DataService {

    private static ArrayList<LiveQuote> liveDataSet = new ArrayList<>();
    private static FxMarketClient webClient = new FxMarketClient();
    public static Calendar lastUpdate;

    /**
     * Crea un dataset locale in memoria per salvare i valori aggiornate delle
     * valute e riutilizzarli per mostrarli all'utente.
     */
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

    /**
     * Aggiorna i valori delle valute del dataset locale.
     */
    public static void updateDataSet() {
        // Scarica i dati aggiornati
        String liveResponse = webClient.requestData().body().toString();
        lastUpdate = Calendar.getInstance();

        // Effettua il parsing dei dati scaricati
        TypeReference<HashMap<String, Double>> mapTypeRef = new TypeReference<HashMap<String, Double>>() {
        };
        HashMap<String, Double> updatedList = JsonParser.deserialize(liveResponse, "price", mapTypeRef);

        // Aggiorna ogni oggetto presente nell'arraylist Dataset con i nuovi valori
        for (LiveQuote liveQuote : liveDataSet) {
            liveQuote.updateQuote(updatedList.get(liveQuote.getCurrencyPair()));
        }
    }

    /**
     * Restituisce un riepilogo di tutti i tassi di cambio salvati nel dataset
     * locale, con relative statistiche.
     * 
     * @return oggetto <code>LiveSeries</code>
     */
    public static LiveSeries getOverview() {
        LiveSeries liveSeries = new LiveSeries();
        liveSeries.setDataSeries(liveDataSet);
        return liveSeries;
    }

    /**
     * Restituisce un riepilogo di tutti i tassi di cambio di una specifica valuta,
     * con relative statistiche.
     * 
     * @param baseCurrency valuta di base dei dati da selezionare.
     * @return oggetto <code>LiveSeries</code>
     * @throws CurrencyNotFoundException eccezione generata in caso di mancata
     *                                   disponibilità della valuta scelta.
     */
    public static LiveSeries getLiveQuoteSeries(String baseCurrency) throws CurrencyNotFoundException {
        CurrencyFilter currencyFilter = new CurrencyFilter(baseCurrency);
        LiveSeries liveSeries = new LiveSeries(baseCurrency);
        liveSeries.setDataSeries(FilterService.filterLiveQuotes(currencyFilter, liveDataSet));
        return liveSeries;
    }

    /**
     * Restituisce un riepilogo di tutti i tassi di cambio di una specifiche valute
     * di base e di quotazione, con relativi dati statistici.
     * 
     * @param baseCurrency    valuta di base dei dati da selezionare.
     * @param quoteCurrencies elenco di valute di quotazione.
     * @return oggetto <code>LiveSeries</code>
     * @throws CurrencyNotFoundException eccezione generata in caso di mancata
     *                                   disponibilità delle valute scelte.
     */
    public static LiveSeries getLiveQuoteSeries(String baseCurrency, Set<String> quotesCurrencies)
            throws CurrencyNotFoundException {
        CurrencyFilter currencyFilter = new CurrencyFilter(baseCurrency, quotesCurrencies);
        LiveSeries liveSeries = new LiveSeries(baseCurrency);
        liveSeries.setDataSeries(FilterService.filterLiveQuotes(currencyFilter, liveDataSet));
        return liveSeries;
    }

    /**
     * Restituisce un riepilogo di un determinato perido, di tutti i tassi di cambio
     * di una specifica valuta, con relative statistiche.
     * 
     * @param baseCurrency valuta di base dei dati da selezionare.
     * @param bodyRequest  body del filtro del periodo.
     * @return oggetto <code>TimeSeries</code>
     * @throws CurrencyNotFoundException  eccezione generata in caso di mancata
     *                                    disponibilità delle valute scelte.
     * @throws IllegaDatePatternException eccezione generata in caso di caso di date
     *                                    mal formattate.
     * @throws InvalidPeriodException     eccezione generata in caso di un periodo
     *                                    non valido.
     */
    public static TimeSeries getHistoricalSeries(String baseCurrency, String bodyRequest)
            throws InvalidPeriodException, IllegalDatePatternException, CurrencyNotFoundException {
        CurrencyFilter currencyFilter = new CurrencyFilter(baseCurrency);
        PeriodFilter periodFilter = new PeriodFilter(bodyRequest);
        String response = webClient.requestData(periodFilter, FilterService.buildCurrenciesQuery(currencyFilter)).body()
                .toString();
        HistoricalDataAdapter hsDataAdapter = new HistoricalDataAdapter(response);
        TimeSeries timeSeries = new TimeSeries(periodFilter.getParam(), baseCurrency);
        timeSeries.setDataSeries(hsDataAdapter.createList());
        return timeSeries;
    }

    /**
     * Restituisce un riepilogo dei valori di tutti i tassi di cambio di una
     * specifica valuta di base e di quotazione, di un determinato periodo con
     * relative statistiche.
     * 
     * @param baseCurrency    valuta di base dei dati da selezionare.
     * @param quoteCurrencies elenco delle valute di quotazione.
     * @param bodyRequest     body del filtro del periodo.
     * @return oggetto <code>TimeSeries</code>
     * @throws CurrencyNotFoundException  eccezione generata in caso di mancata
     *                                    disponibilità delle valute scelte.
     * @throws IllegaDatePatternException eccezione generata in caso di caso di date
     *                                    mal formattate.
     * @throws InvalidPeriodException     eccezione generata in caso di un periodo
     *                                    non valido.
     */
    public static TimeSeries getHistoricalSeries(String baseCurrency, Set<String> quoteCurrencies, String bodyRequest)
            throws CurrencyNotFoundException, InvalidPeriodException, IllegalDatePatternException {
        CurrencyFilter currencyFilter = new CurrencyFilter(baseCurrency, quoteCurrencies);
        PeriodFilter periodFilter = new PeriodFilter(bodyRequest);
        String response = webClient.requestData(periodFilter, FilterService.buildCurrenciesQuery(currencyFilter)).body()
                .toString();
        HistoricalDataAdapter hsDataAdapter = new HistoricalDataAdapter(response);
        TimeSeries timeSeries = new TimeSeries(periodFilter.getParam(), baseCurrency);
        timeSeries.setDataSeries(hsDataAdapter.createList());
        return timeSeries;
    }

    /**
     * Restituisce un riepilogo dei valori di tutti i tassi di cambio appartenenti
     * al periodo selezionato.
     * 
     * @param period periodo dei dati da selezionare
     * @return oggetto <code>TimeSeries</code>
     */
    public static TimeSeries getHistoricalSeries(Period period) {
        PeriodFilter periodFilter = new PeriodFilter(period);
        String response = webClient
                .requestData(periodFilter, AssetsManager.getCurrenciesList().getCurrenciesPairsQuery()).body()
                .toString();
        HistoricalDataAdapter hsDataAdapter = new HistoricalDataAdapter(response);
        TimeSeries timeSeries = new TimeSeries(periodFilter.getParam());
        timeSeries.setDataSeries(hsDataAdapter.createList());
        return timeSeries;
    }
}