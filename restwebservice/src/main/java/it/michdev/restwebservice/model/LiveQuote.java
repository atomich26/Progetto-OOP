package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.utils.parser.DecimalParser;
import it.michdev.restwebservice.utils.stats.StatisticalIndex;

/**
 * @version 0.3.0
 * @since 0.3.0
 * @author Michele Bevilacqua
 */
@JsonPropertyOrder({"currency", "last", "previous", "change", "ptc_change" })
public class LiveQuote extends CurrencyPair {

    private BigDecimal updatedValue, previousValue, changeValue, pctChange;

    public LiveQuote(String currencyPairCode, Double updatedValue, Double previousValue) {
        super(currencyPairCode);
        this.updatedValue = DecimalParser.parseDouble(updatedValue);
        this.previousValue = DecimalParser.parseDouble(previousValue);
        this.pctChange = StatisticalIndex.percentageChange(this.previousValue, this.updatedValue);
        this.changeValue = StatisticalIndex.change(this.previousValue, this.updatedValue);
    }
    
    @JsonProperty("last")
    public BigDecimal getUpdatedValue() {
        return this.updatedValue;
    }
    
    @JsonProperty("previous")
    public BigDecimal getPreviousValue() {
        return this.previousValue;
    }

    @JsonProperty("change")
    public BigDecimal getChangeValue() {
        return this.changeValue;
    }

    @JsonProperty("pct_change")
    public BigDecimal getPctChange() {
        return this.pctChange;
    }

    public void updateQuote(Double newValue) {
        this.previousValue = this.updatedValue;
        this.updatedValue = DecimalParser.parseDouble(newValue);
        this.pctChange = StatisticalIndex.percentageChange(previousValue, updatedValue);
        this.changeValue = StatisticalIndex.change(previousValue, updatedValue);
    }
}
