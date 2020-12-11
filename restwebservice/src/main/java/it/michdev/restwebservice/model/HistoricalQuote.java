package it.michdev.restwebservice.model;

public class HistoricalQuote extends CurrencyPair {
    
    private Double highValue, lowValue, openValue, closeValue;
    
    public HistoricalQuote(String baseCurrency, String quoteCurrency) {
        super(baseCurrency, quoteCurrency);
    }

    public Double getHighValue() {
		return this.highValue;
	}

    public void setHighValue(Double highValue) {
        this.highValue = highValue;
    }
    
    public Double getLowValue() {
		return this.lowValue;
	}

    public void setLowValue(Double lowValue) {
        this.lowValue = lowValue;
    }
    
    public Double getOpenValue() {
		return this.openValue;
	}

    public void setOpenValue(Double openValue) {
        this.openValue = openValue;
    }
    
    public Double getCloseValue() {
		return this.closeValue;
	}

	public void setCloseValue(Double closeValue) {
		this.closeValue = closeValue;
    } 
}
