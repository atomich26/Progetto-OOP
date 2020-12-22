package it.michdev.restwebservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <code>CurrenciesList</code> contiene la lista delle valute disponibili ed in
 * eventuali casi crea una stringa di coppie di valute usate per effettuare
 * chiamate all'endpoint fxmarketapi.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.core.AssetsManager
 */
public class CurrenciesList {

    private String currenciesPairsQuery;
    private HashMap<String, String> currenciesMap;

    /**
     * Costruttore per la classe <code>CurrenciesList</code>
     * 
     * @param currenciesMap <code>HashMap</code> ottenuto dal parsing del file
     *                      currencies.json
     */
    public CurrenciesList(@JsonProperty("currencies") HashMap<String, String> currenciesMap) {
        this.currenciesMap = currenciesMap;
        createCurrenciesPairs();
    }

    /**
     * Restituisce un HashMap contenente le valute disponibili.
     * 
     * @return lista di valute disponibili.
     */
    @JsonProperty("currencies")
    public HashMap<String, String> getCurrenciesMap() {
        return this.currenciesMap;
    }

    /**
     * Restituisce una stringa contenente tutte le possibili coppie di valute, usata
     * per effettuare richieste all'endpoint fxmarketapi.com
     * 
     * @return stringa delle coppie di valute.
     */
    @JsonIgnore
    public String getCurrenciesPairsQuery() {
        return this.currenciesPairsQuery;
    }

    /**
     * Crea la stringa per le coppie di valute utilizzata per effettuare richieste
     * all'endpoin fxmarketapi.com.
     */
    @JsonIgnore
    private void createCurrenciesPairs() {
        ArrayList<String> currenciesPairsList = new ArrayList<String>();
        for (String baseCurrencyString : currenciesMap.keySet()) {
            for (String quoteCurrencyString : currenciesMap.keySet()) {
                if (baseCurrencyString != quoteCurrencyString)
                    currenciesPairsList.add(baseCurrencyString + quoteCurrencyString);
            }
        }
        this.currenciesPairsQuery = currenciesPairsList.stream().collect(Collectors.joining(","));
    }
}
