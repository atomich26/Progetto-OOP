package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.michdev.restwebservice.core.AssetsManager;

@JsonInclude(Include.NON_NULL)
public abstract class DataSeries<T> {
    
    private String baseCurrencyCode, baseCurrencyName;
    
    public DataSeries() {
        this.baseCurrencyCode = null;
        this.baseCurrencyName = null;
    }

    public DataSeries(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.baseCurrencyName = AssetsManager.getCurrenciesList().getCurrenciesMap().get(baseCurrencyCode);
    }
    
    @JsonProperty("code")
    public String getBaseCurrencyCode() {
        return this.baseCurrencyCode;
    }

    @JsonProperty("name")
    public String getBaseCurrencyName() {
        return this.baseCurrencyName;
    }

    public abstract ArrayList<T> getDataSeries();

    public abstract void setDataSeries(ArrayList<T> dataList);
}
