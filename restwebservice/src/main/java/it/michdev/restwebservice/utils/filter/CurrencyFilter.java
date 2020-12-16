package it.michdev.restwebservice.utils.filter;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.exception.CurrencyNotFoundException;

public class CurrencyFilter implements IFilter<String> {

    private String baseCurrency;
    private ArrayList<String> quoteCurrenciesList;

    public CurrencyFilter(String baseCurrency) throws CurrencyNotFoundException {
        this.baseCurrency = baseCurrency;
        checkParams();
    }

    public CurrencyFilter(String baseCurrency, ArrayList<String> quoteCurrenciesList) throws CurrencyNotFoundException {
        this.baseCurrency = baseCurrency;
        this.quoteCurrenciesList = quoteCurrenciesList;
        checkParams();
    }

    private void checkParams() throws CurrencyNotFoundException {
        if (!(baseCurrency.isEmpty())) {
            if (!AssetsManager.getCurrenciesList().getCurrenciesMap().containsKey(baseCurrency))
                throw new CurrencyNotFoundException("La valuta {" + baseCurrency + "} inserita non è disponibile.");
        } else
            throw new CurrencyNotFoundException("Il valore del parametro {base} non può essere vuoto.");

        if (quoteCurrenciesList != null) {
            if (!quoteCurrenciesList.isEmpty()) {
                Predicate<String> predicate = (o -> AssetsManager.getCurrenciesList().getCurrenciesMap()
                        .containsKey(o));
                if (!quoteCurrenciesList.stream().allMatch(predicate))
                    throw new CurrencyNotFoundException("Alcuni valori del parametro {quotes} non sono accettabili. Consulta la documentazione.");
            } else {
                throw new CurrencyNotFoundException(
                        "Il parametro {quotes} non può essere vuoto. Consulta la documentazione.");
            }
        }
    }

    @Override
    public String getParam() {
        return this.baseCurrency;
    }

    public String getQuoteCurrenciesQuery() {
        ArrayList<String> quoteCurrenciesPairs = new ArrayList<>();
        for (String quoteCurrency : quoteCurrenciesList) {
            if (baseCurrency != quoteCurrency)
                quoteCurrenciesPairs.add(baseCurrency + quoteCurrency);
        }
        return quoteCurrenciesPairs.stream().collect(Collectors.joining(","));
    }
}
