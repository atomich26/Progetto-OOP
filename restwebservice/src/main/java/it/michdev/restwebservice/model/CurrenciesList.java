package it.michdev.restwebservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michele Bevilacqua
 */
public class CurrenciesList {

    private String currenciesPairsQuery;
    private HashMap<String, String> currenciesMap;

    public CurrenciesList(@JsonProperty("currencies") HashMap<String, String> currenciesMap) {
        this.currenciesMap = currenciesMap;
        createCurranciesPairs();
    }

    @JsonProperty("currencies")
    public HashMap<String, String> getCurrenciesMap() {
        return this.currenciesMap;
    }
    
    @JsonIgnore
    public String getCurrenciesPairsQuery() {
        return this.currenciesPairsQuery;
    }

    @JsonIgnore
    private void createCurranciesPairs() {
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
