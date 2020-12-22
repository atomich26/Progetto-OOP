package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.utils.parser.DecimalParser;
import it.michdev.restwebservice.utils.stats.StatisticalIndex;
import it.michdev.restwebservice.utils.stats.sort.ISortable;

/**
 * La classe <code>LiveQuote</code> rappresenta il valore della quotazione tra
 * due valute. Contiene il valore aggiornato, quello precendente, differenza e
 * variazione percentuale dei valori.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.model.CurrencyPair
 */
@JsonPropertyOrder({ "currency", "last", "previous", "change", "ptc_change" })
public class LiveQuote extends CurrencyPair implements ISortable {

    private BigDecimal updatedValue, previousValue, changeValue, pctChange;

    /**
     * Costruttore per la classe <code>LiveQuote</code>.
     * 
     * @param currencyPairCode codice ISO 4217 associato alla quotazione.
     * @param updatedValue     valore della quotazione aggiornato.
     * @param previousValue    valore precedente della quotazione.
     */
    public LiveQuote(String currencyPairCode, Double updatedValue, Double previousValue) {
        super(currencyPairCode);
        this.updatedValue = DecimalParser.parseDouble(updatedValue);
        this.previousValue = DecimalParser.parseDouble(previousValue);
        this.pctChange = StatisticalIndex.percentageChange(this.previousValue, this.updatedValue);
        this.changeValue = StatisticalIndex.change(this.previousValue, this.updatedValue);
    }

    /**
     * Restituisce il valore aggiornato della quotazione.
     * 
     * @return oggetto <code>BigDecimal</code>
     */
    @JsonProperty("last")
    public BigDecimal getUpdatedValue() {
        return this.updatedValue;
    }

    /**
     * Restituisce il valore precedente della quotazione.
     * 
     * @return oggetto <code>BigDecimal</code>
     */
    @JsonProperty("previous")
    public BigDecimal getPreviousValue() {
        return this.previousValue;
    }

    /**
     * Restituisce la differenza tra il valore aggiornato e quello precedente.
     * 
     * @return oggetto <code>BigDecimal</code>
     */
    @Override
    @JsonProperty("change")
    public BigDecimal getChangeValue() {
        return this.changeValue;
    }

    /**
     * Restituisce la variazione percentuale trai il valore aggiornato e quello
     * precedente.
     * 
     * @return oggetto <code>BigDecimal</code>
     *
     */
    @Override
    @JsonProperty("pct_change")
    public BigDecimal getPctChangeValue() {
        return this.pctChange;
    }

    /**
     * Aggiorna tutti i valori della quotazione.
     * 
     * @param newValue valore aggiornato.
     */
    public void updateQuote(Double newValue) {
        this.previousValue = this.updatedValue;
        this.updatedValue = DecimalParser.parseDouble(newValue);
        this.pctChange = StatisticalIndex.percentageChange(previousValue, updatedValue);
        this.changeValue = StatisticalIndex.change(previousValue, updatedValue);
    }
}
