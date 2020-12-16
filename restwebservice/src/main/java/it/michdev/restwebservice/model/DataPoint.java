package it.michdev.restwebservice.model;

import java.util.ArrayList;
import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.utils.parser.DateParser;

public class DataPoint {

    private Calendar date;
    private ArrayList<HistoricalQuote> historicalQuote;

    public DataPoint(String date) throws IllegalDatePatternException {
        this.date = DateParser.parseDate(date);
        this.historicalQuote = new ArrayList<>();
    }

    public Calendar getDate() {
        return this.date;
    }
    
    @JsonProperty("date")
    public String getDateAsString() {
        return DateParser.getDateAsString(this.date, DateParser.YYYYMMDD);
    }

    public void setDate(Calendar datePeriod) {
        this.date = datePeriod;
    }
    
    @JsonProperty("quotes")
    public ArrayList<HistoricalQuote> getHistoricalQuote() {
        return this.historicalQuote;
    }

    public void setHistoricalQuote(ArrayList<HistoricalQuote> historicalQuote) {
        this.historicalQuote = historicalQuote;
    }
}
