package it.michdev.restwebservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrenciesList {

    private String currenciesPairsQuery;
    private HashMap<String, String> currenciesMap;

    public CurrenciesList(@JsonProperty("currencies") HashMap<String, String> currenciesMap) {
        this.currenciesMap = currenciesMap;
     
    }

    @JsonProperty("currencies")
    public HashMap<String, String> getCurrenciesMap() {
        return this.currenciesMap;
    }

    @JsonInclude
    private void createCurranciesPairs() {
        for (String baseCurrencyString : currenciesMap.keySet()) {
            for (String quoteCurrencyString : currenciesMap.keySet()) {
                if (baseCurrencyString != quoteCurrencyString)
                    currenciesPairsQuery.concat(baseCurrencyString + )
            }
        }
        
        for (String string : currenciesPairsList) {
            
        }
    }
}
