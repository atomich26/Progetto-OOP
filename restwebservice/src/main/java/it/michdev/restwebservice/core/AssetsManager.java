package it.michdev.restwebservice.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import it.michdev.restwebservice.model.CurrenciesList;
import it.michdev.restwebservice.utils.parser.JsonParser;

/**
 * <b>AssetsManager</b> costituisce un componente gestito per l'applicazione
 * Spring. Questa classe s'interfaccia con i dati contenuti nei file di risorse,
 * quali: <i>currencies.json</i>, <i>config.json</i>, <i>metadata.json</i>.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.core.TaskManager
 */
@Component
public final class AssetsManager {

    private static String accessKey;
    private static JsonNode metadata;
    private static CurrenciesList currenciesList;

    /**
     * Inizializza i dati contenuti nei file della cartella Resources e li salva in
     * memoria per un uso successivo da parte di altre classi.
     */
    public static void initAssets() {
        try {
            accessKey = JsonParser.readNode(readResourcesFile("config.json")).get("access_key").asText();
            metadata = JsonParser.readNode(readResourcesFile("metadata.json"));
            currenciesList = JsonParser.deserialize(readResourcesFile("currencies.json"),CurrenciesList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce l'access key salvato in c<i>config.json</i>, utile per effettuare
     * le chiamate HTTP al servizio fxmarketapi.com.
     * 
     * @return stringa dell'access key
     */
    public static String getAccessKey() {
        return accessKey;
    }

    /**
     * Restituisce il contenuto presente nel file <i>metadata.json</i> come ogetto
     * <code>JsonNode</code>
     * 
     * @return <code>JsonNode</code>
     */
    public static JsonNode getMetadata() {
        return metadata;
    }

    /**
     * Ottiene la lista delle valute disponibili contenute nel file
     * <i>currencies.json</i>
     * 
     * @return oggetto <code>CurrenciesList</code>.
     */
    public static CurrenciesList getCurrenciesList() {
        return currenciesList;
    }

    /**
     * Legge i file di risorse utili per l'esecuzione del software.
     * @param path percorso del file da leggere
     * @return stringa del contenuto del file
     */
    private static String readResourcesFile(String path) {
        try {
            String fileContent = "", line;
            InputStreamReader inputStreamReader = new InputStreamReader(AssetsManager.class.getClassLoader().getResourceAsStream(path));
            BufferedReader buffer = new BufferedReader(inputStreamReader);

            while ((line = buffer.readLine()) != null) {
                fileContent += line;
            }
            buffer.close();
            return fileContent;
              
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
