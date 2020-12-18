package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.utils.parser.DateParser;
import it.michdev.restwebservice.utils.time.Period;

/**
 * Ja
 */
@JsonPropertyOrder({"code", "name","start_date", "end_date", "time_series"})
public class TimeSeries extends DataSeries<DataPoint> {
    
    private Period timeSeriesPeriod;
    private ArrayList<DataPoint> dataPointList;
    
    public TimeSeries(Period timeSeriesPeriod) {
        this.timeSeriesPeriod = timeSeriesPeriod;
    }

    public TimeSeries(Period timeSeriesPeriod, String baseCurrencyCode) {
        super(baseCurrencyCode);
        this.timeSeriesPeriod = timeSeriesPeriod;
    }
    
    @JsonProperty("start_date")
    public String getStartDate() {
        return DateParser.getDateAsString(timeSeriesPeriod.getStartDate(), DateParser.YYYYMMDD);
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return DateParser.getDateAsString(timeSeriesPeriod.getEndDate(), DateParser.YYYYMMDD);
    }

    @JsonIgnore
    public Period getPeriod() {
        return this.timeSeriesPeriod;
    }

    @Override
    @JsonProperty("time_series")
    public ArrayList<DataPoint> getDataSeries() {
        return this.dataPointList;
    }

    @Override
    public void setDataSeries(ArrayList<DataPoint> dataList) {
        this.dataPointList = dataList;
    }
}
