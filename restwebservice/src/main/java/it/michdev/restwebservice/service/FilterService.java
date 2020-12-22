package it.michdev.restwebservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.utils.filter.CurrencyFilter;

/**
 * La classe <code>FilterService</code> rappresenta un servizio per
 * l'applicazione Spring. Gestisce i filtri che l'utente invia tramie le
 * chiamate <code>HTTP</code> restituendo i parametri necessari per selezionare
 * i dati, dopo essere stati controllati.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.service.DataService
 * @see it.michdev.restwebservice.service.StatisticsService
 * @see it.michdev.restwebservice.utils.filter.IFilter
 */
public final class FilterService {

    /**
     * Convalida il filtro delle valute e successivamente filtra i dati ottenuti.
     * 
     * @param currencyFilter filtro delle valute
     * @param dataToFilter   dati da filtrare
     * @return dati filtrati
     */
    public static ArrayList<LiveQuote> filterLiveQuotes(CurrencyFilter currencyFilter,
            ArrayList<LiveQuote> dataToFilter) {
        List<LiveQuote> filteredList = new ArrayList<>();
        filteredList.addAll(dataToFilter);
        filteredList = filteredList.stream()
                .filter(o -> currencyFilter.checkCondition(o.getBaseCurrency(), o.getQuoteCurrency()))
                .collect(Collectors.toList());
        return (ArrayList<LiveQuote>) filteredList;
    }

    /**
     * Controlla il filtro del periodo inviato tramite <code>body</code> della
     * richiesta POST e crea una stringa di coppie di valute da usare per richiedere
     * i dati alle fxmarketapi.com.
     * 
     * @param currencyFilter filtro delle valute.
     * @return stringa da usare per le richieste HTTP.
     */
    public static String buildCurrenciesQuery(CurrencyFilter currencyFilter) {
        ArrayList<String> allCurrencies = new ArrayList<>();

        for (String currencyParam : currencyFilter.getQuoteCurrenciesQuery()) {
            if (!currencyParam.equals(currencyFilter.getParam()))
                allCurrencies.add(currencyFilter.getParam() + currencyParam);
        }
        return allCurrencies.stream().collect(Collectors.joining(","));
    }
}
