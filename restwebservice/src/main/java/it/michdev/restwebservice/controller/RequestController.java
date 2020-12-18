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
 * Questa classe rappresenta un <i>REST Controller</i> per l'applicazione Spring
 * per gestire le chiamate HTTP inoltrate dall'utente su una specifica rotta.
 * Invia un oggetto <code>ResponseEntity</code> con i dati richiesti in formato
 * <code>Json</code> e il codice di stato <code>HTTP</code>.
 * 
 * @version 1.1.0;
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.RestWebServiceApplication
 * @see it.michdev.restwebservice.service.DataService
 * @see it.michdev.restwebservice.exception.GlobalExceptionHandler
 */
@RestController
public final class RequestController {

    /**
     * Risponde alle chiamate inoltrate sulla rotta <code>/metadata</code>
     * restituendo al <code>CLIENT</code> i metadati richiesti.
     * 
     * @return <code>ResponseEntity</code> di <code>JsonNode</code>
     */
    @RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JsonNode> requestMetadata() {
        return new ResponseEntity<>(AssetsManager.getMetadata(), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate inoltrate sulla rotta <code>/available</code>
     * restituendo al <code>CLIENT</code> la lista delle valute disponibili.
     * 
     * @return <code>ResponseEntity</code> di <code>CurrenciesList</code>
     */
    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CurrenciesList> requestAvailableCurrenciesList() {
        return new ResponseEntity<>(AssetsManager.getCurrenciesList(), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate inoltrate sulla rotta <code>/live/overview</code>
     * restituendo al <code>CLIENT</code> una serie di quotazioni di tutte le coppie
     * di valute aggiornate con relative statistiche.
     * 
     * @return <code>ResponseEntity</code> di <code>LiveSeries</code>
     */
    @RequestMapping(value = "/live/overview", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveOverview() {
        return new ResponseEntity<>(DataService.getOverview(), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate inoltrate sulla rotta <code>/live/currency</code>
     * restituendo al <code>CLIENT</code> una serie di quotazioni aggiornate di
     * tutte le valute disponibili rispetto ad una valuta base, con relative
     * statistiche
     * 
     * @param baseCurrency valuta di base
     * @return <code>ResponseEntity</code> di <code>LiveSeries</code>
     */
    @RequestMapping(value = "/live/currency", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveCurrency(@RequestParam("base") String baseCurrency)
            throws CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getLiveQuoteSeries(baseCurrency), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate inoltrate sulla rotta <code>/live/quotes</code>
     * restituendo al <code>CLIENT</code> una serie di quotazioni aggiornate delle
     * valute scelte come parametro <code>quotes</code> rispetto ad una valuta di
     * base scelta.
     * 
     * @param baseCurrency    valuta di base
     * @param quoteCurrencies elenco di valute quotate.
     * @throws CurrencyNotFoundException eccezione generata in caso di valute non
     *                                   disponibili.
     * @return <code>ResponseEnitity</code> di <code>LiveSeries</code>
     */
    @RequestMapping(value = "/live/quotes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveQuotes(@RequestParam("base") String baseCurrency,
            @RequestParam("quotes") Set<String> quoteCurrencies) throws CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getLiveQuoteSeries(baseCurrency, quoteCurrencies), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate inoltrate sulla rotta
     * <code>/historical/currency</code> restituendo al <code>CLIENT</code> una
     * serie storica di quotazioni di tutte le valute disponibili rispetto alla
     * valuta base.
     * 
     * @param baseCurrency valuta di base
     * @param bodyRequest  body della chiamata HTTP
     * @throws InvalidPeriodException      eccezione generata in caso di periodo
     *                                     inserito non valido.
     * @throws IllegalDatePatternException eccezione generata in caso di date mal
     *                                     formattate.
     * @throws CurrencyNotFoundException   eccezione generata in caso di valute non
     *                                     disponibili.
     * @return <code>ResponseEntity</code> di <code>TimeSeries</code>.
     */
    @RequestMapping(value = "/historical/currency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TimeSeries> requestHistoricalCurrency(@RequestParam("base") String baseCurrency,
            @RequestBody String bodyRequest)
            throws InvalidPeriodException, IllegalDatePatternException, CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getHistoricalSeries(baseCurrency, bodyRequest), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate sulla rotta <code>/historical/quotes</code>
     * restituendo al <code>CLIENT</code> una serie storica di quotazioni delle
     * valute scelte come parametro <code>quotes</code> rispetto ad una valuta di
     * base selezionata.
     * 
     * @param baseCurrency    valuta di base
     * @param quoteCurrencies elenco di valute quotate
     * @param bodyRequest     body della richiesta HTTP
     * @return <code>ResponseEntity</code> di <code>TimeSeries</code>
     * 
     */
    @RequestMapping(value = "/historical/quotes", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TimeSeries> requestHistoricalQuotes(@RequestParam("base") String baseCurrency,
            @RequestParam("quotes") Set<String> quoteCurrencies, @RequestBody String bodyRequest)
            throws CurrencyNotFoundException, InvalidPeriodException, IllegalDatePatternException {
        return new ResponseEntity<>(DataService.getHistoricalSeries(baseCurrency, quoteCurrencies, bodyRequest),
                HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate sulla rotta <code>/statistics/lastweeks</code>
     * restituendo al <code>CLIENT</code> una serie di statistiche calcolate su una
     * serie storica di un periodo corrispondente a due settimane precedenti alla
     * data attuale.
     * 
     * @return <code>ResponseEntity</code> di <code>StatsSeries</code>
     * @throws DataNotFoundException      eccezione generata in assenza di dati da
     *                                    elaborare
     * @throws InvalidStatsFieldException eccezione generata in caso di campo
     *                                    selezionato non disponibile.
     */
    @RequestMapping(value = "/statistics/lastweeks", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StatsSeries> requestLastWeekStats() throws InvalidStatsFieldException, DataNotFoundException {
        Calendar now = Calendar.getInstance();
        Calendar twoWeeksAgo = Calendar.getInstance();
        twoWeeksAgo.add(Calendar.WEEK_OF_YEAR, -2);
        Period selectedPeriod = new Period(twoWeeksAgo, now);
        TimeSeries dataToCompute = DataService.getHistoricalSeries(selectedPeriod);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(null, dataToCompute), HttpStatus.OK);
    }

    /**
     * Risponde alle chiamate sulla rotta <code>/statistics/lastmonth</code>
     * restituendo al <code>CLIENT</code> una serie di statistiche calcolate su una
     * serie storica di quotazioni del periodo corrispondente al mese precedente
     * alla data attuale.
     * 
     * @return <code>ResponseEntity</code> di <code>StatsSeries</code>
     * @throws InvalidStatsFieldException eccezione generata in caso di campo scelto
     *                                    non valido
     * @throws DataNotFoundException      eccezione generata in assenza di dati da
     *                                    elaborare
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
     * Risponde alle chiamate sulla rotta <code>/statistics/currency</code>
     * restituendo al <code>CLIENT</code> con una serie di dati statistici elaborati
     * per coppie di valute selezionate tramite filtri su un dato campo : high,
     * open, low e close.
     * 
     * @param baseCurrency valuta di base
     * @param fieldName    valore della quotazione su cui si vuole elaborare le
     *                     statistiche
     * @param bodyRequest  filtro del periodo.
     * @return <code>ResponseEntity</code> di <code>StatsSeries</code>
     * @throws InvalidPeriodException      eccezione generata in caso di errori nel
     *                                     filtro del periodo.
     * @throws IllegalDatePatternException eccezione generata in caso di date mal
     *                                     formattate.
     * @throws CurrencyNotFoundException   eccezione generata in caso di valute non
     *                                     disponibili.
     * @throws InvalidStatsFieldException  eccezione generata in caso di campo non
     *                                     disponibile.
     * @throws DataNotFoundException       eccezione generata in assenza di dati da
     *                                     elaborare
     */
    @RequestMapping(value = "/statistics/currency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<StatsSeries> requestCurrencyStats(@RequestParam("base") String baseCurrency,
            @RequestParam("field") String fieldName, @RequestBody String bodyRequest) throws InvalidPeriodException,
            IllegalDatePatternException, CurrencyNotFoundException, InvalidStatsFieldException, DataNotFoundException {
        TimeSeries dataToCompute = DataService.getHistoricalSeries(baseCurrency, bodyRequest);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(fieldName, dataToCompute), HttpStatus.OK);
    }
}
