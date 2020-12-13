package it.michdev.restwebservice.core;

import javax.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.michdev.restwebservice.service.DataService;

/** 
La classe <b>ScheduledTask</b>  
*/
@Component
public final class TaskManager {
    
    @PostConstruct
    public static void doLaunchTask() {
        AssetsManager.initAssets();
        DataService.createDataSet();
        System.out.println("$--> Dataset created");
    }

    @Scheduled(fixedRate = 600000)
    public static void doUpdateDataTask() {
        DataService.updateDataSet();
        System.out.println("$--> Updated live quote");
    }
}
