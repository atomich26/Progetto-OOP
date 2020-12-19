package it.michdev.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * La classe <code>CurrencyPair</code> rappresenta un modello generico per
 * contenere dati di vari generi(quotazioni aggiornate, report ecc.) in relazione a due valute differenti. Le classi
 * <code>HistoricalQuote</code>,<code>LiveQuote</code> e <code>Report</code>
 * estendono CurrencyPair.
 * 
 * @version 1.1.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.model.LiveQuote
 * @see it.michdev.restwebservice.model.HistoricalQuote
 * @see it.michdev.restwebservice.model.Report
 */
public class CurrencyPair {

    private String baseCurrency, quoteCurrency;

    /**
     * Costruttore per la classe <code>CurrencyPair</code>.
     * @param currencyPairCode codice ISO 4217 della coppia di valute.
     */
    public CurrencyPair(String currencyPairCode) {
        this.baseCurrency = currencyPairCode.substring(0, 3);
        this.quoteCurrency = currencyPairCode.substring(3);
    }

    /**
     * Restituisce il codice della valuta di base.
     * @return stringa del codice.
     */
    @JsonIgnore
    public String getBaseCurrency() {
        return this.baseCurrency;
    }

    /**
     * Imposta il codice della valuta quotata.
     * @param baseCurrency codice ISO 4217
     */
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    /**
     * Restituisce il codice ISO 4217 della valuta di base.
     * @return stringa del codice.
     */
    @JsonIgnore
    public String getQuoteCurrency() {
        return this.quoteCurrency;
    }

    /**
     * Imposta il codice della valuta quotata.
     * @param quoteCurrency codice ISO 4217
     */
    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

      /**
     * Restituisce il codice ISO 4217 della coppia di valute.
     * @return stringa del codice.
     */
    @JsonProperty("currency")
    public String getCurrencyPair() {
        return this.baseCurrency + this.quoteCurrency;
    }
}
