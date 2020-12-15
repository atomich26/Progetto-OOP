package it.michdev.restwebservice.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.utils.parser.DecimalParser;

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
    public Double getLowValue() {
        return this.lowValue.doubleValue();
	}

    public void setLowValue(Double lowValue) {
        this.lowValue = DecimalParser.parseDouble(lowValue);
    }
    
    @JsonProperty("open")
    public Double getOpenValue() {
		return this.openValue.doubleValue();
	}

    public void setOpenValue(Double openValue) {
        this.openValue = DecimalParser.parseDouble(openValue);
    }
    
    @JsonProperty("close")
    public Double getCloseValue() {
		return this.closeValue.doubleValue();
	}

	public void setCloseValue(Double closeValue) {
        this.closeValue = DecimalParser.parseDouble(closeValue);
    } 
}
