package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.utils.parser.DateParser;
import it.michdev.restwebservice.utils.time.Period;

/**
 * La classe <code>TimeSeries</code> rappresenta una serie storica di valori di
 * quotazioni associato ad un determinato periodo di tempo.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.model.DataPoint
 */
@JsonPropertyOrder({ "code", "name", "start_date", "end_date", "time_series" })
public class TimeSeries extends DataSeries<DataPoint> {

    private Period timeSeriesPeriod;
    private ArrayList<DataPoint> dataPointList;

    /**
     * Costruttore per la classe <code>TimeSeries</code>.
     * 
     * @param timeSeriesPeriod perido associato alla serie storica di dati.
     */
    public TimeSeries(Period timeSeriesPeriod) {
        this.timeSeriesPeriod = timeSeriesPeriod;
    }

    /**
     * Costruttore per la classe <code>TiemSeries</code>
     * 
     * @param timeSeriesPeriod periodo associato alla serie storica di dati
     * @param baseCurrencyCode codice della valuta associata alla serie storica.
     */
    public TimeSeries(Period timeSeriesPeriod, String baseCurrencyCode) {
        super(baseCurrencyCode);
        this.timeSeriesPeriod = timeSeriesPeriod;
    }

    /**
     * Restituisce la data iniziale del periodo associato alla serie storica
     * @return stringa della data
     */
    @JsonProperty("start_date")
    public String getStartDate() {
        return DateParser.getDateAsString(timeSeriesPeriod.getStartDate(), DateParser.YYYYMMDD);
    }

     /**
     * Restituisce la data finale del periodo associato alla serie storica.
     * @return stringa della data
     */
    @JsonProperty("end_date")
    public String getEndDate() {
        return DateParser.getDateAsString(timeSeriesPeriod.getEndDate(), DateParser.YYYYMMDD);
    }

     /**
     * Restituisce il periodo associato alla serie storica
     * @return <code>Period</code> del periodo associato alla serie storica.
     */
    @JsonIgnore
    public Period getPeriod() {
        return this.timeSeriesPeriod;
    }

    /**
     * Restituisce il dataset associato alla serie storica.
     */
    @Override
    @JsonProperty("time_series")
    public ArrayList<DataPoint> getDataSeries() {
        return this.dataPointList;
    }

    /**
     * Imposta il dataset associato alla serie storica.
     * @param dataList dataset
     */
    @Override
    public void setDataSeries(ArrayList<DataPoint> dataList) {
        this.dataPointList = dataList;
    }
}
