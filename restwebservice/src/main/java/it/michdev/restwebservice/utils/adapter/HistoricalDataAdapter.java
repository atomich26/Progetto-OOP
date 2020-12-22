package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.model.HistoricalQuote;
import it.michdev.restwebservice.utils.parser.JsonParser;

/**
 * La classe <b>HistoricalDataAdapter</b> è utilizzata per creare
 * <code>ArrayList</code> di oggetti <code>DataPoint</code> effettuando il parsing di dati
 * grezzi, scaricati dal <code>webclient</code>. Implementa l'interfaccia
 * <code>IDataAdapter</code>.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.adapter.IDataAdapter
 * @see it.michdev.restwebservice.utils.adapter.HistoricalDataAdapter
 * @see it.michdev.restwebservice.model.DataPoint
 */
public class HistoricalDataAdapter implements IDataAdapter<DataPoint> {

    private String historicalResponse;
    private TypeReference<LinkedHashMap<String, LinkedHashMap<String, JsonNode>>> mapTypeRef;

    /**
     * Costruttore per la classe <code>HistoricalDataAdapter</code>.
     * 
     * @param historicalResponse stringa <code>json</code> dei dati storici ottenuta
     *                           dal webclient.
     */
    public HistoricalDataAdapter(String historicalResponse) {
        this.historicalResponse = historicalResponse;
        this.mapTypeRef = new TypeReference<LinkedHashMap<String, LinkedHashMap<String, JsonNode>>>() {
        };
    }

    /**
     * Il metodo <code>createList</code> crea oggetti <code>DataPoint</code>
     * analizzando la stringa json ottenuta dal webclient e li aggiunge ad un
     * <code>ArrayList</code>.
     * 
     * @return <code>ArrayList</code> di <code>DataPoint</code>
     */

    @Override
    public ArrayList<DataPoint> createList() {
        ArrayList<DataPoint> dataPointList = new ArrayList<>();
        LinkedHashMap<String, LinkedHashMap<String, JsonNode>> parsedList;
        parsedList = JsonParser.deserialize(historicalResponse, "price", mapTypeRef);

        for (Map.Entry<String, LinkedHashMap<String, JsonNode>> dataReport : parsedList.entrySet()) {
            DataPoint dataPoint;
            try {
                dataPoint = new DataPoint(dataReport.getKey().toString());
                for (Map.Entry<String, JsonNode> currency : dataReport.getValue().entrySet()) {
                    HistoricalQuote historicalQuote = new HistoricalQuote(currency.getKey());
                    historicalQuote.setHighValue(currency.getValue().get("high").asDouble());
                    historicalQuote.setLowValue(currency.getValue().get("low").asDouble());
                    historicalQuote.setCloseValue(currency.getValue().get("close").asDouble());
                    historicalQuote.setOpenValue(currency.getValue().get("open").asDouble());
                    dataPoint.getHistoricalQuotes().add(historicalQuote);
                }
                dataPointList.add(dataPoint);
            } catch (IllegalDatePatternException e) {
                e.printStackTrace();
            }
        }
        return dataPointList;
    }
}
