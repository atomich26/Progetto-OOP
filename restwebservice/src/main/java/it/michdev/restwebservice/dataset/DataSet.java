package it.michdev.restwebservice.dataset;

import java.util.ArrayList;

import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.model.LiveQuote;

/**
 * @version 0.4.0
 * @since 0.4.0
 * @author Michele Bevilacqua
 */
public final class DataSet {

    private static ArrayList<LiveQuote> liveQuoteDataSet = new ArrayList<>();
    private static ArrayList<DataPoint> dataPointDataSet = new ArrayList<>();

    public ArrayList<LiveQuote> getLiveQuotes() {
        return liveQuoteDataSet;
    }

    public static void setLiveQuoteList(ArrayList<LiveQuote> liveQuoteList) {
        liveQuoteDataSet = liveQuoteList;
    }
    
    public static ArrayList<DataPoint> getHistoricalDataPoints() {
        return dataPointDataSet;
    }
    
    public static void setHistoricalQuoteList(ArrayList<DataPoint> dataPointList) {
        dataPointDataSet = dataPointList;
    }
}
