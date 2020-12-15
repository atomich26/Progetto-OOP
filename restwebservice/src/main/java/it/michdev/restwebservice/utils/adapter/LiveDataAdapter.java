package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.model.QuoteSeries;
import it.michdev.restwebservice.utils.parser.JsonParser;

public class LiveDataAdapter implements IDataAdapter<LiveQuote>{

    private String liveQuoteResponse, previousQuoteResponse;

    public LiveDataAdapter(String liveQuoteResponse, String previousQuoteResponse) {
        this.liveQuoteResponse = liveQuoteResponse;
        this.previousQuoteResponse = previousQuoteResponse;
    }

    @Override
    public ArrayList<LiveQuote> createList() {
        ArrayList<LiveQuote> liveQuoteArrayList = new ArrayList<>();
        TypeReference<HashMap<String, Double>> mapTypeRef = new TypeReference<HashMap<String, Double>>() {
        };
        HashMap<String, Double> liveQuoteMap = new HashMap<>();
        liveQuoteMap = JsonParser.deserialize(liveQuoteResponse, "price", mapTypeRef);

        HashMap<String, Double> previousQuoteMap = new HashMap<>();
        previousQuoteMap = JsonParser.deserialize(previousQuoteResponse,"price", mapTypeRef);

        for (Map.Entry<String, Double> entry : liveQuoteMap.entrySet()) {
            LiveQuote newLiveQuote = new LiveQuote(entry.getKey(), entry.getValue(),
                    previousQuoteMap.get(entry.getKey()));
            liveQuoteArrayList.add(newLiveQuote);
        } 
        return liveQuoteArrayList;
    }
}
