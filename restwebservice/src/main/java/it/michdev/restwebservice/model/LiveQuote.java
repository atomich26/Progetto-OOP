package it.michdev.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import it.michdev.restwebservice.utils.stats.StatisticalIndex;

/**
 * @version 0.3.0
 * @since 0.3.0
 * @author Michele Bevilacqua
 */
@JsonPropertyOrder({ "currency", "last", "previous", "change", "ptc_change" })
public class LiveQuote extends CurrencyPair {

    Double updatedValue, previousValue, changeValue, pctChange;

    public LiveQuote(String currencyPairCode, Double updateValue, Double previousValue) {
        super(currencyPairCode);
        this.updatedValue = updateValue;
        this.previousValue = previousValue;
        this.pctChange = StatisticalIndex.percentageChange(previousValue, updateValue);
        this.changeValue = StatisticalIndex.change(previousValue, updateValue);
    }
    
    @JsonProperty("last")
    public Double getUpdatedValue() {
        return this.updatedValue;
    }

    public void setUpdatedValue(Double updatedValue) {
        this.updatedValue = updatedValue;
    }
    
    @JsonProperty("previous")
    public Double getpreviousValue() {
        return this.previousValue;
    }

    public void setpreviousValue(Double previousValue) {
        this.previousValue = previousValue;
    }
    
    @JsonProperty("change")
    public Double getChangeValue() {
        return this.changeValue;
    }

    @JsonProperty("pct_change")
    public Double getPctChange() {
        return this.pctChange;
    }

    public void updateQuote(Double newValue) {
        this.previousValue = this.updatedValue;
        this.updatedValue = newValue;
        this.pctChange = StatisticalIndex.percentageChange(previousValue, updatedValue);
        this.changeValue = StatisticalIndex.change(previousValue, updatedValue);
    }
}
