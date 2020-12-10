package it.michdev.restwebservice.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import it.michdev.restwebservice.utils.json.JsonHelper;

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
public class AssetsManager {

    private static String accessKey;
    private static JsonNode metadata;
    private static HashMap<String, String> currenciesList = new HashMap<>();

    public static void initAssets() {
        try {
            accessKey = JsonHelper.readFieldValue(ResourceUtils.getFile("classpath:config.json"), "access_key");
            metadata = JsonHelper.readNode(ResourceUtils.getFile("metadata.json"));
            TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
            currenciesList = JsonHelper.deserialize(ResourceUtils.getFile("classpath:currencies.json"), typeRef);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static String getMetadata(String pathName) {
        return metadata.get(pathName).toString();
    }

    public static HashMap<String, String> getCurrenciesList() {
        return currenciesList;
    }
}
