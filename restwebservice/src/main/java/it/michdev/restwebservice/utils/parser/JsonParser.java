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

    public static String readFieldValue(File fileSource, String fieldName) {
        try {
            return objMapper.readTree(fileSource).get(fieldName).asText();
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readFieldValue(File fileSource, String fieldName, TypeReference<T> valueType) {
        try {
            JsonNode node = objMapper.readTree(fileSource);
            return objMapper.readValue(node.get(fieldName).toString(), valueType);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readFieldValue(String source, String fieldName, TypeReference<T> valueType) {
        try {
            JsonNode node = objMapper.readTree(source);
            return objMapper.readValue(node.get(fieldName).toString(), valueType);
        } catch (JsonProcessingException e) {
            return null;
        } 
    }
}
