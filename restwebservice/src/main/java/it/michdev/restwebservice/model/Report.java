package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.michdev.restwebservice.utils.stats.sort.ISortable;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"currency", "average", "variance", "change", "ptc_change",})
public class Report extends CurrencyPair implements ISortable{
    
    private BigDecimal change, ptcChange, average, variance;

    public Report(String currencyPairCode) {
        super(currencyPairCode);
    }

    public BigDecimal getChangeValue() {
        return this.change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    @Override
    public BigDecimal getPctChangeValue() {
       
        return null;
    }

    public void setPtcChange(BigDecimal ptcChange) {
        this.ptcChange = ptcChange;
    }

    public BigDecimal getVariance() {
        return this.variance;
    }

    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

    public BigDecimal getAverage() {
        return this.average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }
}
