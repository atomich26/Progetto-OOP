package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.utils.parser.JsonParser;

public class DataAdapter {

    public static ArrayList<LiveQuote> createLiveQuoteList(String liveQuoteResponse, String previousQuoteResponse) {
        ArrayList<LiveQuote> liveQuoteArrayList = new ArrayList<>();
        TypeReference<HashMap<String, Double>> typeRef = new TypeReference<HashMap<String, Double>>() {
        };
        HashMap<String, Double> liveQuoteList = new HashMap<>();
        liveQuoteList = JsonParser.readFieldValue(liveQuoteResponse,"price", typeRef);

        HashMap<String, Double> previousQuoteList = new HashMap<>();
        previousQuoteList = JsonParser.readFieldValue(previousQuoteResponse,"price", typeRef);

        for (Map.Entry<String, Double> entry : liveQuoteList.entrySet()) {
            LiveQuote newLiveQuote = new LiveQuote(entry.getKey(), entry.getValue(),
                    previousQuoteList.get(entry.getKey()));
            liveQuoteArrayList.add(newLiveQuote);
        } 
        return liveQuoteArrayList;
    }

    public static ArrayList<DataPoint> createHistoricalQuoteList(String historicalQuoteResponse) {
        return null;
    }
}
