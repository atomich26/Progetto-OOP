package it.michdev.restwebservice.controller;

import java.util.Calendar;
import java.util.Set;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.michdev.restwebservice.core.AssetsManager;
import it.michdev.restwebservice.exception.CurrencyNotFoundException;
import it.michdev.restwebservice.exception.DataNotFoundException;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.exception.InvalidPeriodException;
import it.michdev.restwebservice.exception.InvalidStatsFieldException;
import it.michdev.restwebservice.model.CurrenciesList;
import it.michdev.restwebservice.model.dataseries.LiveSeries;
import it.michdev.restwebservice.model.dataseries.StatsSeries;
import it.michdev.restwebservice.model.dataseries.TimeSeries;
import it.michdev.restwebservice.service.DataService;
import it.michdev.restwebservice.service.StatisticsService;
import it.michdev.restwebservice.utils.time.Period;

/**
 * ** Questa classe rappresenta un <i>REST Controller</i>. Viene utilizzata da
 * Spring per gestire le chiamate HTTP inoltrate dall'utente su una specifica
 * rotta. Invia un oggetto <code>ResponseEntity<></code> con i dati richiesti in
 * formato <code>Json</code> e l'<i>header HTTP</i>.
 * 
 * @version 1.0.0;
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.RestWebServiceApplication
 */
@RestController
public final class RequestController {

    /**
     * Risponde alla rotta <code>/available</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con la lista delle
     * valute disponibili.
     * 
     * @return lista delle valute disponibili.
     */
    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CurrenciesList> CurrenciesList() {
        return new ResponseEntity<>(AssetsManager.getCurrenciesList(), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/metadata</code> restituendo al <code>CLIENT</code>
     * un oggetto <code>ResponseEntity</code> con i metadati.
     * 
     * @return stringa json dei metadati.
     */
    @RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JsonNode> requestLiveMetadata() {
        return new ResponseEntity<>(AssetsManager.getMetadata(), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/live/overview</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con una lista dei
     * valori aggiornati dei i tassi di scambio e relative statistiche.
     * 
     * @return oggetto <code>LiveSeries</code> contenente i valori dei tassi di
     *         scambio.
     */
    @RequestMapping(value = "/live/overview", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveOverview() {
        return new ResponseEntity<>(DataService.getOverview(), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/live/currency</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con una lista dei
     * valori aggiornati di tutti i tassi di scambio con la valuta di base passata
     * come parametro.
     * 
     * @return oggetto <code>LiveSeries</code> contenente la lista di coppie di
     *         valute con dati statistici.
     */
    @RequestMapping(value = "/live/currency", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveCurrency(@RequestParam("base") String baseCurrency)
            throws CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getLiveQuoteSeries(baseCurrency), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/live/quotes</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con una lista dei
     * valori aggiornati di tutti i tassi di scambio con la valuta di base e le
     * valute quotate passate come parametri.
     * 
     * @return oggetto <code>LiveSeries</code> contenente la lista di coppie di
     *         valute con dati statistici.
     */
    @RequestMapping(value = "/live/quotes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveQuotes(@RequestParam("base") String baseCurrency,
            @RequestParam("quotes") Set<String> quoteCurrencies) throws CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getLiveQuoteSeries(baseCurrency, quoteCurrencies), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/historical/currency</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con una lista dei
     * valori aggiornati di tutti i tassi di scambio con la valuta di base passata
     * come parametro.
     * 
     * @return oggetto <code>LiveSeries</code> contenente la lista di coppie di
     *         valute con dati statistici.
     */
    @RequestMapping(value = "/historical/currency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TimeSeries> requestHistoricalCurrency(@RequestParam("base") String baseCurrency,
            @RequestBody String bodyRequest)
            throws InvalidPeriodException, IllegalDatePatternException, CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getHistoricalSeries(baseCurrency, bodyRequest), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/historical/quotes</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con una lista dei
     * valori aggiornati di tutti i valori dei tassi di scambio con la valuta di
     * base e le valute quotate passate come parametri.
     * 
     * @return oggetto <code>LiveSeries</code> contenente la lista di coppie di
     *         valute con dati statistici.
     */
    @RequestMapping(value = "/historical/quotes", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TimeSeries> requestHistoricalQuotes(@RequestParam("base") String baseCurrency,
            @RequestParam("quotes") Set<String> quoteCurrencies, @RequestBody String bodyRequest)
            throws CurrencyNotFoundException, InvalidPeriodException, IllegalDatePatternException {
        return new ResponseEntity<>(DataService.getHistoricalSeries(baseCurrency, quoteCurrencies, bodyRequest),
                HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/statistics/lastweeks</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con i dati
     * statistici di tutti i tassi di cambio delle ultime due settimane.
     * 
     * @return oggetto <code>StatsSeries</code> con i dati statistici.
     * @throws DataNotFoundException eccezione generata in caso di dataset non
     *                               valido per l'elaborazione.
     */
    @RequestMapping(value = "/statistics/lastweeks", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StatsSeries> requestLastWeekStats() throws InvalidStatsFieldException, DataNotFoundException {
        Calendar now = Calendar.getInstance();
        Calendar twoWeeksAgo = Calendar.getInstance();
        twoWeeksAgo.add(Calendar.WEEK_OF_YEAR, -2);
        Period period = new Period(twoWeeksAgo, now);
        TimeSeries dataToCompute = DataService.getHistoricalSeries(period);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(null, dataToCompute), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/statistics/lastmonth</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con i dati
     * statistici di tutti i tassi di cambio dell'ultimo mese.
     * 
     * @return oggetto <code>StatsSeries</code> con i dati statistici.
     * @throws DataNotFoundException eccezione generata in caso di dataset non
     *                               valido per l'elaborazione.
     */
    @RequestMapping(value = "/statistics/lastmonth", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StatsSeries> requestLastMonthStats()
            throws InvalidStatsFieldException, DataNotFoundException {
        Calendar now = Calendar.getInstance();
        Calendar monthAgo = Calendar.getInstance();
        monthAgo.add(Calendar.MONTH, -1);
        Period period = new Period(monthAgo, now);
        TimeSeries dataToCompute = DataService.getHistoricalSeries(period);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(null, dataToCompute), HttpStatus.OK);
    }

    /**
     * Risponde alla rotta <code>/statistics/currency</code> restituendo al
     * <code>CLIENT</code> un oggetto <code>ResponseEntity</code> con una lista dei
     * dati statistici elaborati. I dati sono scelti in base ai parametri passati
     * come argomenti tra cui la valuta di base di un tasso di scambio, il campo del
     * report storico di cui si vogliono calcolare le statistiche e il periodo dei
     * dati storici.
     * 
     * @param baseCurrency valuta di base dei tassi di cambio scelti.
     * @param fieldName    valore del report storico di un tasso di cambio.
     * @param bodyRequest  filtro del periodo.
     * @return oggetto <code>StatsSeries</code> con i dati statistici.
     * @throws InvalidPeriodException      eccezione generata in caso di errori nel
     *                                     filtro del periodo.
     * @throws IllegalDatePatternException eccezione generata in caso di date mal
     *                                     formattate.
     * @throws CurrencyNotFoundException   eccezione generata in caso di valute non
     *                                     disponibili.
     * @throws InvalidStatsFieldException  eccezione generata in caso di campo non
     *                                     disponibile.
     * @throws DataNotFoundException       eccezione generata in caso di un dataset
     *                                     valido da elaborare.
     */
    @RequestMapping(value = "/statistics/currency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<StatsSeries> requestCurrencyStats(@RequestParam("base") String baseCurrency,
            @RequestParam("field") String fieldName, @RequestBody String bodyRequest) throws InvalidPeriodException,
            IllegalDatePatternException, CurrencyNotFoundException, InvalidStatsFieldException, DataNotFoundException {
        TimeSeries dataToCompute = DataService.getHistoricalSeries(baseCurrency, bodyRequest);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(fieldName, dataToCompute), HttpStatus.OK);
    }
}
