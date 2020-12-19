package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.utils.parser.DecimalParser;

/**
 * La classe <code>HistoricalQuote</code> rappresenta il valore della quotazione
 * tra due valute di un determinato giorno. Contiene il valore più basse
 * raggiunto, il valore più alto, la quotazione di apertura e quella di
 * chiusura.
 * 
 * @version 1.1.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.model.CurrencyPair
 */
@JsonPropertyOrder({ "currency", "open", "high", "low", "close" })
public class HistoricalQuote extends CurrencyPair {

    private BigDecimal highValue, lowValue, openValue, closeValue;

    /**
     * Costruttore per la classe <code>HistoricalQuote</code>
     * 
     * @param currencyPairCode codice ISO 4217 della coppia di valute da associare.
     */
    public HistoricalQuote(String currencyPairCode) {
        super(currencyPairCode);
    }

    /**
     * Restituisce il valore di picco raggiunto in giornata.
     * 
     * @return numero in <code>BigDecimal</code>
     */
    @JsonProperty("high")
    public BigDecimal getHighValue() {
        return this.highValue;
    }

    /**
     * Imposta il valore di picco raggiunto in giornata.
     * 
     * @param highValue valore da impostare
     */
    public void setHighValue(Double highValue) {
        this.highValue = DecimalParser.parseDouble(highValue);
    }

    /**
     * Restituisce il valore più basso raggiunto in giornata.
     * 
     * @return numero in <code>BigDecimal</code>
     */
    @JsonProperty("low")
    public BigDecimal getLowValue() {
        return this.lowValue;
    }

    /**
     * Imposta il valore più basso raggiunto in giornata.
     * 
     * @param lowValue valore da impostare
     */
    public void setLowValue(Double lowValue) {
        this.lowValue = DecimalParser.parseDouble(lowValue);
    }

    /**
     * Restituisce il valore di apertura della quotazione nel giorno associato.
     * 
     * @return numero in <code>BigDecimal</code>
     */
    @JsonProperty("open")
    public BigDecimal getOpenValue() {
        return this.openValue;
    }

    /**
     * Imposta il valore di apertura della quotazione nel giorno associato.
     * 
     * @param openValue valore da impostare
     */
    public void setOpenValue(Double openValue) {
        this.openValue = DecimalParser.parseDouble(openValue);
    }

    /**
     * Restituisce il valore di chiusura della quotazione nel giorno associato.
     * 
     * @return numero in <code>BigDecimal</code>
     */
    @JsonProperty("close")
    public BigDecimal getCloseValue() {
        return this.closeValue;
    }

    /**
     * Imposta il valore di chiusura della quotazione nel giorno associato.
     * 
     * @param closeValue valore da impostare
     */
    public void setCloseValue(Double closeValue) {
        this.closeValue = DecimalParser.parseDouble(closeValue);
    }
}
