package it.michdev.restwebservice.utils.filter;

import java.util.Set;
import java.util.function.Predicate;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.exception.CurrencyNotFoundException;

/**
 * Un <code>CurrencyFilter</code> è un oggetto filtro che implementa
 * l'interfaccia <code>IFilter</code>. Verifica la validità dei parametri
 * inseriti dall'utente e li restituisce per filtrare le quotazione di coppie di
 * valute in base alle valute selezionate.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.filter.IFilter
 * @see it.michdev.restwebservice.utils.filter.PeriodFilter
 */
public class CurrencyFilter implements IFilter<String> {

    protected String baseCurrency;
    protected Set<String> quoteCurrenciesList;

    /**
     * Costruttore per la classe <code>CurrencyFilter</code>.
     * 
     * @param baseCurrency stringa per filtrare i dati in base alla valuta di
     *                     partenza.
     * @throws CurrencyNotFoundException eccezione generata quando i valori delle
     *                                   valute inseriti non sono accetabili
     */
    public CurrencyFilter(String baseCurrency) throws CurrencyNotFoundException {
        this.baseCurrency = baseCurrency;
        this.quoteCurrenciesList = AssetsManager.getCurrenciesList().getCurrenciesMap().keySet();
        checkParams(false);
    }

    /**
     * Costruttore per la classe <code>CurrencyFilter</code>.
     * 
     * @param baseCurrency        stringa per filtrare i dati in base alla valuta di
     *                            partenza.
     * @param quoteCurrenciesList set di stringhe per filtrari i dati in base alla
     *                            valuta quotata.
     * @throws CurrencyNotFoundException eccezione generata quando i valori delle
     *                                   valute inseriti non sono accetabili
     */
    public CurrencyFilter(String baseCurrency, Set<String> quoteCurrenciesList) throws CurrencyNotFoundException {
        this.baseCurrency = baseCurrency;
        this.quoteCurrenciesList = quoteCurrenciesList;
        checkParams(true);
    }

    /**
     * Controlla i parametri inseriti dall'utente, generando eccezioni in presenza
     * di errori.
     * 
     * @param checkQuoteCurrencies flag per il controllo del parametro quote
     * @throws CurrencyNotFoundException eccezione generata quando i valori delle
     *                                   valute inseriti non sono accetabili
     */
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
                    throw new CurrencyNotFoundException(
                            "Alcuni valori del parametro {quotes} non sono accettabili. Consulta la documentazione.");
            } else {
                throw new CurrencyNotFoundException(
                        "Il parametro {quotes} non può essere vuoto. Consulta la documentazione.");
            }
    }

    /**
     * Restituisce il parametro di base del filtro.
     * 
     * @return stringa della valuta di base.
     */
    @Override
    public String getParam() {
        return this.baseCurrency;
    }

    /**
     * Restituisce il <code>Set</code> di stringhe di valute per filtrare i dati.
     * 
     * @return oggetto <code>Set</code>.
     */
    public Set<String> getQuoteCurrenciesQuery() {
        return this.quoteCurrenciesList;
    }

    /**
     * Restituisce un valore booleano dipendente dal confronto fra i valori inseriti
     * come argomento. In particolare serve per filtrare i risultati utilizzandolo
     * in un <code>Predicate</code>.
     * 
     * @param baseCurrency  stringa della valuta di base
     * @param quoteCurrency stringa della valuta quotata
     * @return valore booleano
     */
    public boolean checkCondition(String baseCurrency, String quoteCurrency) {
        if (baseCurrency.equals(this.baseCurrency) && this.quoteCurrenciesList.contains(quoteCurrency))
            return true;
        else
            return false;
    }
}
