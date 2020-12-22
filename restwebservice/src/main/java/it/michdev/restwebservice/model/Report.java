package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.michdev.restwebservice.utils.stats.sort.ISortable;

/**
 * La classe <code>Report</code> rappresenta un riepilogo di dati statistici
 * calcolati sui valori delle quotazioni(periodiche e non) di una specifica
 * coppia di valute.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.model.CurrencyPair
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "currency", "average", "variance", "change", "ptc_change", })
public class Report extends CurrencyPair implements ISortable {

    private BigDecimal change, ptcChange, average, variance;

    /**
     * Costruttore per la classe <code>Report</code>.
     * 
     * @param currencyPairCode codice ISO 4217 della coppia di valute asscociata.
     */
    public Report(String currencyPairCode) {
        super(currencyPairCode);
    }

    /**
     * Restituisce la differenza tra il valore inziale e quello finale di un
     * determinato periodo.
     * 
     * @return oggetto <code>BigDecimal</code>.
     */
    @JsonProperty("change")
    public BigDecimal getChangeValue() {
        return this.change;
    }

    /**
     * Imposta la differenza tra il valore inziale e quello finale della quotazione
     * di un determinato periodo
     * 
     * @param change valore della differenza calcolata tra due valori.
     */
    public void setChange(BigDecimal change) {
        this.change = change;
    }

    /**
     * Restituisce la differenza tra il valore inziale e quello finale della
     * quotazione di un determinato periodo.
     * 
     * @return oggetto <code>BigDecimal</code>.
     */
    @Override
    @JsonProperty("ptc_change")
    public BigDecimal getPctChangeValue() {
        return this.ptcChange;
    }

    /**
     * Imposta la variazione percentuale tra il valore finale e quello iniziale
     * della quotazione di un determinato periodo
     * 
     * @param ptcChange oggetto <code>BigDecimal</code>
     */
    public void setPtcChange(BigDecimal ptcChange) {
        this.ptcChange = ptcChange;
    }

    /**
     * Restituisce la varianza dei valori della quotazione di un determinato
     * periodo.
     * 
     * @return oggetto <code>BigDecimal</code>.
     */
    @JsonProperty("variance")
    public BigDecimal getVariance() {
        return this.variance;
    }

    /**
     * Imposta la varianza dei valori della quotazione di un determinato periodo
     * 
     * @param variance oggetto <code>BigDecimal</code>
     */
    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

    /**
     * Restituisce la media dei valori della quotazione di un determinato periodo.
     * 
     * @return oggetto <code>BigDecimal</code>.
     */
    @JsonProperty("average")
    public BigDecimal getAverage() {
        return this.average;
    }

    /**
     * Imposta la media dei valori della quotazione di un determinato periodo
     * 
     * @param average oggetto <code>BigDecimal</code>
     */
    public void setAverage(BigDecimal average) {
        this.average = average;
    }
}
