package it.michdev.restwebservice.utils.filter;

import java.util.Set;
import java.util.function.Predicate;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.exception.CurrencyNotFoundException;

public class CurrencyFilter implements IFilter<String> {

    protected String baseCurrency;
    protected Set<String> quoteCurrenciesList;

    public CurrencyFilter(String baseCurrency) throws CurrencyNotFoundException {
        this.baseCurrency = baseCurrency;
        this.quoteCurrenciesList = AssetsManager.getCurrenciesList().getCurrenciesMap().keySet();
        checkParams(false);
    }

    public CurrencyFilter(String baseCurrency, Set<String> quoteCurrenciesList) throws CurrencyNotFoundException {
        this.baseCurrency = baseCurrency;
        this.quoteCurrenciesList = quoteCurrenciesList;
        checkParams(true);
    }

    private void checkParams(boolean checkQuoteCurrencies) throws CurrencyNotFoundException {
        if (!(baseCurrency.isEmpty())) {
            if (!AssetsManager.getCurrenciesList().getCurrenciesMap().containsKey(baseCurrency))
                throw new CurrencyNotFoundException("La valuta {" + baseCurrency + "} inserita non è disponibile.");
        } else
            throw new CurrencyNotFoundException("Il valore del parametro {base} non può essere vuoto.");

        if (quoteCurrenciesList != null && checkQuoteCurrencies)
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

    @Override
    public String getParam() {
        return this.baseCurrency;
    }

    public Set<String> getQuoteCurrenciesQuery() {
        return this.quoteCurrenciesList;
    }

    public boolean checkCondition(String baseCurrency, String quoteCurrency) {
        if(baseCurrency.equals(this.baseCurrency) && this.quoteCurrenciesList.contains(quoteCurrency))
            return true;
        else
            return false;
    }
}
