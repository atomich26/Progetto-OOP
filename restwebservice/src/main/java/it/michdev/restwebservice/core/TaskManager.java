package it.michdev.restwebservice.core;

import javax.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import it.michdev.restwebservice.service.DataService;

/**
 * La classe <b>TaskManager</b> rappresenta un componente gestito per
 * l'applicazione Spring. Contiene tutti i metodi, la cui chiamata è programmata
 * a intervalli per mezzo di annotazioni Spring <code>PostContruct</code> o
 * <code>Scheduled</code>.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.core.AssetsManager
 */
@Component
public final class TaskManager {

    /**
     * Questo metodo viene eseguito all'avvio dell'applicazione Spring. Richiama i
     * metodi che necessitano di essere eseguiti all'avvio, in particolare quelli
     * per la creazione di un dataset locale.
     */
    @PostConstruct
    public static void doLaunchTask() {
        AssetsManager.initAssets();
        DataService.createDataSet();
        System.out.println("$--> Dataset created");
    }

    /**
     * Questo metodo viene eseguito ogni 10 minuti. Richiama il metodo
     * <code>updateDataSet</code> di <code>DataService</code> per aggiornare il
     * dataset.
     */
    @Scheduled(initialDelay = 600000, fixedRate = 600000)
    public static void doUpdateDataTask() {
        DataService.updateDataSet();
        System.out.println("$--> Updated live quote");
    }
}
