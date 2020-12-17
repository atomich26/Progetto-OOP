package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.utils.parser.JsonParser;

/**
 * La classe <b>LiveDataAdapter</b> è utilizzata per creare
 * <code>ArrayList</code> di oggetti <code>LiveQuote</code> dal parsing di dati
 * grezzi, scaricati dal <code>webclient</code>. Implementa l'interfaccia
 * <code>IDataAdapter</code>.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.adapter.IDataAdapter
 * @see it.michdev.restwebservice.utils.adapter.HistoricalDataAdapter
 */
public class LiveDataAdapter implements IDataAdapter<LiveQuote> {

    private String lastQuoteResponse, previousQuoteResponse;
    private TypeReference<LinkedHashMap<String, Double>> mapTypeRef;

    /**
     * Costruttore per la classe <code>LiveDataAdapter</code>
     * 
     * @param lastQuoteResponse     stringa <code>json</code> dei dati aggiornati
     *                              ottenuta dal webclient.
     * @param previousQuoteResponse stringa <code>json</code> dei dati risalenti a 2
     *                              ore precedenti ottenuta dal webclient.
     */
    public LiveDataAdapter(String lastQuoteResponse, String previousQuoteResponse) {
        this.lastQuoteResponse = lastQuoteResponse;
        this.previousQuoteResponse = previousQuoteResponse;
        mapTypeRef = new TypeReference<LinkedHashMap<String, Double>>() {
        };
    }

    /**
     * Il metodo <code>createList</code> crea oggetti <code>LiveQuote</code>
     * analizzando la stringa json ottenuta dal webclient e li aggiunge ad un
     * <code>ArrayList</code>.
     * 
     * @return <code>ArrayList</code> di <code>LiveQuote</code>
     */
    @Override
    public ArrayList<LiveQuote> createList() {
        ArrayList<LiveQuote> liveQuoteArrayList = new ArrayList<>();
        LinkedHashMap<String, Double> liveQuoteMap = new LinkedHashMap<>();
        LinkedHashMap<String, Double> previousQuoteMap = new LinkedHashMap<>();

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
