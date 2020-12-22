package it.michdev.restwebservice.utils.parser;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * La classe <code>JsonParser</code> mette a disposizione metodi per la
 * de/serializzazione dei dati formattati in file/stringhe <code>JSON</code>.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.parser.DecimalParser
 * @see it.michdev.restwebservice.utils.parser.DateParser
 */
public class JsonParser {

    private static ObjectMapper objMapper = new ObjectMapper();

    /**
     * Legge il contenuto di un file e lo converte in <code>JsonNode</code>.
     * 
     * @param file contenuto da leggere e convertire.
     * @return oggetto <code>JsonNode</code>
     */
    public static JsonNode readNode(File file) {
        try {
            return objMapper.readTree(file);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Legge il contenuto di una stringa <code>json</code> e lo converte in oggetto
     * <code>JsonNode</code>
     * 
     * @param source stringa <code>json</code> da convertire.
     * @return oggetto <code>JsonNode</code>
     * @throws JsonMappingException    eccezione generata in presenza di errori
     *                                 durante il mapping della stringa
     * @throws JsonProcessingException eccezione generata in presenza di errori
     *                                 durante il parsing della stringa
     */
    public static JsonNode readNode(String source) throws JsonMappingException, JsonProcessingException {
        return objMapper.readTree(source);
    }

    /**
     * Legge il contenuto di un file <code>json</code> e lo deserializza
     * nell'oggetto definito dall'argomento <code>valueType</code>.
     * 
     * @param fileSource      file <code>json</code> da deserializzare.
     * @param valueType tipo dell'oggetto che si vuole deserializzare.
     * @param <T> parametro del tipo dell'oggetto da deserializzare 
     * @return un oggetto di tipo generico <code>T</code> passato come argomento.*/
    public static <T> T deserialize(File fileSource, Class<T> valueType) {
        try {
            return objMapper.readValue(fileSource, valueType);
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * Legge il contenuto di un stringa <code>json</code> e la converte nell'oggetto
     * definito dall'argomento <code>valueType</code>.
     * 
     * @param source    stringa <code>json</code> da deserializzare.
     * @param valueType tipo dell'oggetto che si vuole deserializzare.
     * @param <T> parametro del tipo dell'oggetto da deserializzare.
     * @return un oggetto di tipo generico <code>T</code> passato come argomento.
     * @throws JsonProcessingException eccezione generata in presenza di errori
     *                                 durante il parsing.
     */
    public static <T> T deserialize(String source, Class<T> valueType) throws JsonProcessingException {
        return objMapper.readValue(source, valueType);
    }

    /**
     * Legge il contenuto di un stringa <code>json</code> e deserializza il
     * contenuto di un campo nell'oggetto definito dall'argomento
     * <code>valueType</code>.
     * 
     * @param source    stringa <code>json</code> del contenuto da deserialzzare.
     * @param fieldName nome del campo di cui si vuole deserializzare il contenuto.
     * @param valueType tipo dell'oggetto che si vuole deserializzare.
     * @param <T> tipo dell'oggetto da deserializzare.
     * @return un oggetto di tipo generico <code>T</code> passato come argomento.
     */
    public static <T> T deserialize(String source, String fieldName, TypeReference<T> valueType) {
        JsonNode node;
        try {
            node = objMapper.readTree(source);
            return objMapper.readValue(node.get(fieldName).toString(), valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
