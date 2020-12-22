package it.michdev.restwebservice.model;

import java.util.ArrayList;
import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.utils.parser.DateParser;

/**
 * La classe <code>DataPoint</code> rappresenta un modello contenente una serie
 * di valori storici relativi a quotazioni tra due valute.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 */
public class DataPoint {

    private Calendar date;
    private ArrayList<HistoricalQuote> historicalQuotes;

    /**
     * Costruttore per la classe <code>DataPoint</code>.
     * 
     * @param date data relativa alla serie di quotazioni.
     * @throws IllegalDatePatternException eccezione generata in caso di date mal
     *                                     formattate.
     */
    public DataPoint(String date) throws IllegalDatePatternException {
        this.date = DateParser.parseDate(date);
        this.historicalQuotes = new ArrayList<>();
    }

    /**
     * Ottiene la data associata al modello.
     * 
     * @return <code>Calendar</code>
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Ottiene la data associata al periodo come stringa
     * 
     * @return stringa della data
     */
    @JsonProperty("date")
    public String getDateAsString() {
        return DateParser.getDateAsString(this.date, DateParser.YYYYMMDD);
    }

    /**
     * Imposta la data da associare ai dati contenuti.
     * 
     * @param datePeriod data da associare
     */
    public void setDate(Calendar datePeriod) {
        this.date = datePeriod;
    }

    /**
     * Restituisce il dataset dei valori delle quotazioni storiche associate a
     * questo modello
     * 
     * @return <code>ArrayList</code> delle quotazioni storiche
     */
    @JsonProperty("quotes")
    public ArrayList<HistoricalQuote> getHistoricalQuotes() {
        return this.historicalQuotes;
    }

    /**
     * Imposta il dataset dei valori storici delle quotazioni.
     * 
     * @param historicalQuotes dataset delle quotazioni storiche
     */
    public void setHistoricalQuote(ArrayList<HistoricalQuote> historicalQuotes) {
        this.historicalQuotes = historicalQuotes;
    }
}
