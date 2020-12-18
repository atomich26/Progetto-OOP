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
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 */
@RestController
public final class RequestController {

    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CurrenciesList> CurrenciesList() {
        return new ResponseEntity<>(AssetsManager.getCurrenciesList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JsonNode> requestLiveMetadata() {
        return new ResponseEntity<>(AssetsManager.getMetadata(), HttpStatus.OK);
    }

    @RequestMapping(value = "/live/overview", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveOverview() {
        return new ResponseEntity<>(DataService.getOverview(), HttpStatus.OK);
    }

    @RequestMapping(value = "/live/currency", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveCurrency(@RequestParam("base") String baseCurrency)
            throws CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getLiveQuoteSeries(baseCurrency), HttpStatus.OK);
    }

    @RequestMapping(value = "/live/quotes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LiveSeries> requestLiveQuotes(@RequestParam("base") String baseCurrency,
            @RequestParam("quotes") Set<String> quoteCurrencies) throws CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getLiveQuoteSeries(baseCurrency, quoteCurrencies), HttpStatus.OK);
    }

    @RequestMapping(value = "/historical/currency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TimeSeries> requestHistoricalCurrency(@RequestParam("base") String baseCurrency,
            @RequestBody String bodyRequest)
            throws InvalidPeriodException, IllegalDatePatternException, CurrencyNotFoundException {
        return new ResponseEntity<>(DataService.getHistoricalSeries(baseCurrency, bodyRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/historical/quotes", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TimeSeries> requestHistoricalQuotes(@RequestParam("base") String baseCurrency,
            @RequestParam("quotes") Set<String> quoteCurrencies, @RequestBody String bodyRequest)
            throws CurrencyNotFoundException, InvalidPeriodException, IllegalDatePatternException {
        return new ResponseEntity<>(DataService.getHistoricalSeries(baseCurrency, quoteCurrencies, bodyRequest),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/statistics/lastweeks", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StatsSeries> requestLastWeekStats() throws InvalidStatsFieldException, DataNotFoundException {
        Calendar now = Calendar.getInstance();
        Calendar twoWeeksAgo = Calendar.getInstance();
        twoWeeksAgo.add(Calendar.WEEK_OF_YEAR, -2);
        Period period = new Period(twoWeeksAgo, now);
        TimeSeries dataToCompute = DataService.getHistoricalSeries(period);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(null, dataToCompute), HttpStatus.OK);
    }

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

    @RequestMapping(value = "/statistics/currency", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<StatsSeries> requestCurrencyStats(@RequestParam("base") String baseCurrency,
            @RequestParam("field") String fieldName, @RequestBody String bodyRequest)
            throws InvalidPeriodException, IllegalDatePatternException, CurrencyNotFoundException,
            InvalidStatsFieldException, DataNotFoundException {
        TimeSeries dataToCompute = DataService.getHistoricalSeries(baseCurrency, bodyRequest);
        return new ResponseEntity<>(StatisticsService.getCurrencyStats(fieldName, dataToCompute), HttpStatus.OK);
    }
}
