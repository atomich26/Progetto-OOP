package it.michdev.restwebservice.utils.parser;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * La classe <code>JsonHelper</code> mette a disposizione metodi per la
 * de/serializzazione dei dati formattati in file <code>JSON</code>.
 * 
 * @version 0.1.3
 * @since 0.1.3
 * @author Michele Bevilacqua
 */
public class JsonParser {

    private static ObjectMapper objMapper = new ObjectMapper();

    public static JsonNode readNode(File file) {
        try {
            return objMapper.readTree(file);
        } catch (IOException e) {
            return null;
        }
    }

    public static JsonNode readNode(String source) {
        try {
            return objMapper.readTree(source);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T parseNode(String source, Class<T> valueType) throws JsonProcessingException {
        return objMapper.readValue(source, valueType);
    }

    public static <T> T readNode(File fileSource, Class<T> valueType) {
        try {
            return objMapper.readValue(fileSource, valueType);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T deserialize(String source, String fieldName, TypeReference<T> valueType){
        JsonNode node;
        try {
            node = objMapper.readTree(source);
            return objMapper.readValue(node.get(fieldName).toString(), valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
