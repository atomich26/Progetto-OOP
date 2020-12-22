package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.model.Report;
import it.michdev.restwebservice.utils.parser.DateParser;
import it.michdev.restwebservice.utils.stats.sort.Sort;
import it.michdev.restwebservice.utils.time.Period;

/**
 * La classe <code>StatsSeries</code> rappresenta una serie di dati contenente
 * le statistiche calcolate per ogni coppia di valute.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.model.dataseries.DataSeries
 */
@JsonPropertyOrder({ "start_date", "end_date", "trend", "data_series" })
public class StatsSeries extends DataSeries<Report> {

    private Period period;
    private Sort<Report> currencyTrend;
    private ArrayList<Report> reportList;

    /**
     * Costruttore di default per la classe <code>StatsSeries</code>.
     * 
     * @param period periodo da associare alla serie.
     */
    public StatsSeries(Period period) {
        super();
        this.period = period;
        reportList = new ArrayList<Report>();
    }

    /**
     * Restituisce il dataset dei valori statistici calcolati per ogni coppia di
     * valute.
     *
     * @return <code>ArrayList</code>.
     */
    @Override
    @JsonProperty("data_series")
    public ArrayList<Report> getDataSeries() {
        return this.reportList;
    }

    /**
     * Imposta il dataset dei valori statistici calcolati per ogni coppia di valute.
     * 
     * @param reportList dataset da associare.
     */
    @Override
    public void setDataSeries(ArrayList<Report> reportList) {
        this.reportList = reportList;
        if (reportList.size() > 1)
            currencyTrend = new Sort<>(reportList);
    }

    /**
     * Restituisce la data iniziale del periodo associato alla serie di dati.
     * 
     * @return stringa della data iniziale.
     */
    @JsonProperty("start_date")
    public String getStartDate() {
        return DateParser.getDateAsString(period.getStartDate(), DateParser.YYYYMMDD);
    }

    /**
     * Restituisce la data finale del periodo associato alla serie di dati.
     * 
     * @return stringa della data iniziale.
     */
    @JsonProperty("end_date")
    public String getEndDate() {
        return DateParser.getDateAsString(period.getEndDate(), DateParser.YYYYMMDD);
    }

    /**
     * Imposta il periodo associato alla serie di dati.
     * 
     * @param period oggetto <code>Period</code> da associare.
     */
    public void setPeriod(Period period) {
        this.period = period;
    }

    /**
     * Restituisce un oggetto <code>Sort</code> contenente i dati statistici
     * calcolati.
     * 
     * @return <code>Sort</code>
     */
    @JsonProperty("trend")
    public Sort<Report> getCurrencyTrend() {
        return this.currencyTrend;
    }
}
