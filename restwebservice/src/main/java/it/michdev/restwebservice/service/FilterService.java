package it.michdev.restwebservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.utils.filter.CurrencyFilter;

public final class FilterService {

    public static ArrayList<LiveQuote> filterLiveQuotes(CurrencyFilter currencyFilter, ArrayList<LiveQuote> dataToFilter) {
        List<LiveQuote> filteredList = new ArrayList<>();
        filteredList.addAll(dataToFilter);
        filteredList = filteredList
                .stream().filter(o -> currencyFilter.checkCondition(o.getBaseCurrency(), o.getQuoteCurrency())).collect(Collectors.toList());
        return (ArrayList<LiveQuote>) filteredList;
    }

    public static String buildCurrenciesQuery(CurrencyFilter currencyFilter) {
        ArrayList<String> allCurrencies = new ArrayList<>();

        for (String currencyParam : currencyFilter.getQuoteCurrenciesQuery()) {
            if (!currencyParam.equals(currencyFilter.getParam()))
                allCurrencies.add(currencyFilter.getParam() + currencyParam);
        }
        return allCurrencies.stream().collect(Collectors.joining(","));
    }
}
