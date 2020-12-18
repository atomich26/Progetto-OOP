package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.service.DataService;
import it.michdev.restwebservice.utils.parser.DateParser;
import it.michdev.restwebservice.utils.stats.sort.Sort;

/**
 * La classe <code>LiveSeries</code> rappresenta una serie di dati contenente i valori
 * dei tassi di cambio aggiornati che possono essere associati ad una valuta o
 * meno.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 */
@JsonPropertyOrder({ "code", "name", "last_refreshed", "trend", "quotes" })
@JsonInclude(Include.NON_NULL)
public class LiveSeries extends DataSeries<LiveQuote> {

    private Calendar timeStamp;
    private Sort<LiveQuote> currenciesTrend;
    private ArrayList<LiveQuote> liveQuoteList;

    /**
     * Costruttore per la classe <code>LiveSeries</code>.
     * 
     * @param baseCurrencyCode stringa da associare alla serie.
     */
    public LiveSeries(String baseCurrencyCode) {
        super(baseCurrencyCode);
        this.timeStamp = DataService.lastUpdate;
    }

    /**
     * Costruttore per la classe <code>LiveSeries</code>
     */
    public LiveSeries() {
        super();
        this.timeStamp = DataService.lastUpdate;
    }

    /**
     * Restituisce un ArrayList di oggetti <code>LiveQuote</code>.
     * @return <code>ArrayList</code>.
     */
    @Override
    @JsonProperty("quotes")
    public ArrayList<LiveQuote> getDataSeries() {
        return this.liveQuoteList;
    }

     /**
     * Imposta la lista associata a questa serie.
     * @param dataList <code>ArrayList</code> da associare.
     */
    @Override
    public void setDataSeries(ArrayList<LiveQuote> dataList) {
        this.liveQuoteList = dataList;
        if (dataList.size() > 1)
            currenciesTrend = new Sort<LiveQuote>(dataList);
    }

     /**
     * Restituisce una stringa della data dell'ultimo aggiornamento dei dati.
     * @return stringa della data.
     */
    @JsonProperty("last_refreshed")
    public String getTimeStampAsString() {
        return DateParser.getDateAsString(timeStamp, DateParser.YYYYMMDDHHmmss);
    }

     /**
     * Restituisce un oggetto <code>Sort</code> contenente i dati statistici calcolati.
     * @return <code>Sort</code>
     */
    @JsonProperty("trend")
    public Sort<LiveQuote> getSeriesTrend() {
        return this.currenciesTrend;
    }
}
