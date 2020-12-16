package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.utils.parser.DecimalParser;

@JsonPropertyOrder({"open","high","low","close"})
public class HistoricalQuote extends CurrencyPair {
    
    private BigDecimal highValue, lowValue, openValue, closeValue;
    
    public HistoricalQuote(String currencyPairCode) {
        super(currencyPairCode);
    }

    @JsonProperty("high")
    public Double getHighValue() {
		return this.highValue.doubleValue();
	}

    public void setHighValue(Double highValue) {
        this.highValue = DecimalParser.parseDouble(highValue);
    }

    @JsonProperty("low")
    public BigDecimal getLowValue() {
        return this.lowValue;
	}

    public void setLowValue(Double lowValue) {
        this.lowValue = DecimalParser.parseDouble(lowValue);
    }
    
    @JsonProperty("open")
    public BigDecimal getOpenValue() {
        return this.openValue;
	}

    public void setOpenValue(Double openValue) {
        this.openValue = DecimalParser.parseDouble(openValue);
    }
    
    @JsonProperty("close")
    public BigDecimal getCloseValue() {
        return this.closeValue;
	}

	public void setCloseValue(Double closeValue) {
        this.closeValue = DecimalParser.parseDouble(closeValue);
    } 
}
