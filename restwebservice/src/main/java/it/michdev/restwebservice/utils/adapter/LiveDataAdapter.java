package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.utils.parser.JsonParser;

public class LiveDataAdapter implements IDataAdapter<LiveQuote> {

    private String lastQuoteResponse, previousQuoteResponse;
    private TypeReference<HashMap<String, Double>> mapTypeRef;

    public LiveDataAdapter(String lastQuoteResponse, String previousQuoteResponse) {
        this.lastQuoteResponse = lastQuoteResponse;
        this.previousQuoteResponse = previousQuoteResponse;
        mapTypeRef = new TypeReference<HashMap<String, Double>>() {};
    }

    @Override
    public ArrayList<LiveQuote> createList() {
        ArrayList<LiveQuote> liveQuoteArrayList = new ArrayList<>();
        HashMap<String, Double> liveQuoteMap = new HashMap<>();
        HashMap<String, Double> previousQuoteMap = new HashMap<>();

        liveQuoteMap = JsonParser.deserialize(lastQuoteResponse, "price", mapTypeRef);
        previousQuoteMap = JsonParser.deserialize(previousQuoteResponse, "price", mapTypeRef);

        for (Map.Entry<String, Double> entry : liveQuoteMap.entrySet()) {
            LiveQuote newLiveQuote = new LiveQuote(entry.getKey(), entry.getValue(),
                    previousQuoteMap.get(entry.getKey()));
            liveQuoteArrayList.add(newLiveQuote);
        }
        return liveQuoteArrayList;
    }
}
