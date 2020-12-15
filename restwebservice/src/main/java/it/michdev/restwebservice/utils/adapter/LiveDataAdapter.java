package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.model.QuoteSeries;

public class LiveDataAdapter implements IDataAdapter<LiveQuote>{

    private String liveQuoteResponse, previousQuoteResponse;

    public LiveDataAdapter(String liveQuoteResponse, String previousQuoteResponse) {
        this.liveQuoteResponse = liveQuoteResponse;
        this.previousQuoteResponse = previousQuoteResponse;
    }

    @Override
    public ArrayList<LiveQuote> createList() {
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
}
