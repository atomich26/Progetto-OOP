package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.michdev.restwebservice.core.AssetsManager;

/**
 * La classe <code>DataSeries</code> rappresenta una serie di
 * specifici dati e relative statistiche tra cui, oggetti
 * <code>LiveQuote</code>, <code>HistoricalQuote</code> o <code>Report</code>.
 *
 * @version 1.0.0 
 * @author Michele Bevilacqua
 */
@JsonInclude(Include.NON_NULL)
public abstract class DataSeries<T> {

    private String baseCurrencyCode, baseCurrencyName;

    /**
     * Costruttore per la classe DataSeries.
     */
    public DataSeries() {
        this.baseCurrencyCode = null;
        this.baseCurrencyName = null;
    }

    /**
     * Costruttore per la classe DataSeries rappresentante una serie di dati associata ad una valuta.
     * @param baseCurrencyCode nome della valuta associata 
     */
    public DataSeries(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.baseCurrencyName = AssetsManager.getCurrenciesList().getCurrenciesMap().get(baseCurrencyCode);
    }

    /**
     * Restituisce il codice della valuta associata.
     * @return stringa del codice
     */
    @JsonProperty("code")
    public String getBaseCurrencyCode() {
        return this.baseCurrencyCode;
    }

    /**
     * Restituisce il nome della valuta associata.
     * @return stringa del nome
     */
    @JsonProperty("name")
    public String getBaseCurrencyName() {
        return this.baseCurrencyName;
    }

    /**
     * Restituisce la lista di dati definiti dal parametro T.
     * @return <code>ArrayList</code> di dati 
     */
    public abstract ArrayList<T> getDataSeries();

    /**
     * Imposta al modello un dataset di dati definiti dal parametro T.
     * @param dataList set di dati. 
     */
    public abstract void setDataSeries(ArrayList<T> dataList);
}
