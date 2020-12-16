package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.model.HistoricalQuote;
import it.michdev.restwebservice.utils.parser.JsonParser;

public class HistoricalDataAdapter implements IDataAdapter<DataPoint> {

    private String historicalResponse;
    private TypeReference<HashMap<String, HashMap<String, JsonNode>>> mapTypeRef;

    public HistoricalDataAdapter(String historicalResponse) {
        this.historicalResponse = historicalResponse;
        this.mapTypeRef = new TypeReference<HashMap<String, HashMap<String, JsonNode>>>() {
        };
    }

    @Override
    public ArrayList<DataPoint> createList() {
        ArrayList<DataPoint> dataPointList = new ArrayList<>();
        HashMap<String, HashMap<String, JsonNode>> responseList;
        responseList = JsonParser.deserialize(historicalResponse, "price", mapTypeRef);
        try {
            Iterator<Map.Entry<String, HashMap<String, JsonNode>>> iterator = responseList.entrySet().iterator();
            while (iterator.hasNext()) {
                DataPoint dataPoint = new DataPoint(iterator.next().getKey().toString());
                for (Map.Entry<String, JsonNode> currency : iterator.next().getValue().entrySet()) {
                    HistoricalQuote historicalQuote = new HistoricalQuote(currency.getKey());
                    historicalQuote.setHighValue(currency.getValue().get("high").asDouble());
                    historicalQuote.setLowValue(currency.getValue().get("low").asDouble());
                    historicalQuote.setCloseValue(currency.getValue().get("close").asDouble());
                    historicalQuote.setOpenValue(currency.getValue().get("open").asDouble());
                    dataPoint.getHistoricalQuote().add(historicalQuote);
                }
                dataPointList.add(dataPoint);
            }
            return dataPointList;
        } catch (IllegalDatePatternException e) {
            return null;
        }
    }
}
