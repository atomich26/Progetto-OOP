package it.michdev.restwebservice.core;

import java.io.FileNotFoundException;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import it.michdev.restwebservice.model.CurrenciesList;
import it.michdev.restwebservice.utils.parser.JsonParser;

/**
 * <b>ApplicationAssets</b> costituisce un componente gestito per l'applicazione
 * Spring. Questa classe s'interfaccia con i dati contenuti nei file di risorse, quali:
 * <i>currencies.json</i>, <i>config.json</i>, <i>metadata.json</i>.
 * 
 * @version 0.2.5
 * @since 0.2.5
 * @author Michele Bevilacqua
 */
@Component
public final class AssetsManager {

    private static String accessKey;
    private static JsonNode metadata;
    private static CurrenciesList currenciesList;
               
    public static void initAssets() {
        try {
            accessKey = JsonParser.readNode(ResourceUtils.getFile("classpath:config.json")).get("access_key").asText();
            metadata = JsonParser.readNode(ResourceUtils.getFile("classpath:metadata.json"));
            currenciesList = JsonParser.readNode(ResourceUtils.getFile("classpath:currencies.json"), CurrenciesList.class);
        } catch (FileNotFoundException e) {        
            e.printStackTrace();
        }
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static JsonNode getMetadata() {
        return metadata;
    }

    public static CurrenciesList getCurrenciesList() {
        return currenciesList;
    }
}
