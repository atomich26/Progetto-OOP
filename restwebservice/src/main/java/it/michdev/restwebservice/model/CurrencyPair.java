package it.michdev.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 0.3.0
 * @since 0.3.0
 * @author Michele Bevilacqua
 */
public class CurrencyPair {

    private String baseCurrency, quoteCurrency;

    public CurrencyPair(String currencyPairCode) {
        this.baseCurrency = currencyPairCode.substring(0, 3);
        this.quoteCurrency = currencyPairCode.substring(3);
    }

    @JsonIgnore
    public String getBaseCurrency() {
        return this.baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @JsonIgnore
    public String getQuoteCurrency() {
        return this.quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    @JsonProperty("currency")
    public String getCurrencyPair() {
        return this.baseCurrency + this.quoteCurrency;
    }
}
