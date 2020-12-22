package it.michdev.restwebservice.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import it.michdev.restwebservice.exception.DataNotFoundException;
import it.michdev.restwebservice.exception.InvalidStatsFieldException;
import it.michdev.restwebservice.model.DataPoint;
import it.michdev.restwebservice.model.HistoricalQuote;
import it.michdev.restwebservice.model.Report;
import it.michdev.restwebservice.model.dataseries.StatsSeries;
import it.michdev.restwebservice.model.dataseries.TimeSeries;
import it.michdev.restwebservice.utils.stats.StatisticalIndex;

/**
 * <code>StatisticsService</code> rappresenta un servizio dell'applicazione
 * Spring per l'elaborazione delle statistiche sul set di dati in ingresso.
 * 
 * @version 1.1.3
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.stats.StatisticalIndex
 * @see it.michdev.restwebservice.model.dataseries.StatsSeries
 */
public final class StatisticsService {

    /**
     * Calcola gli indici statistici di un dataset(di un determinato periodo)
     * ottenuto tramite la classe <code>DataService</code>. Elabora i dati scelti
     * dall'utente tramite il parametro <code>fieldName</code> degli oggetti
     * <code>HistoricalQuote</code>.
     * 
     * @param fieldName  campo dell'oggetto <code>Historical</code> che si vuole
     *                   prendere elaborare.
     * @param timeSeries dataset su cui si vogliono elaborare statistiche.
     * @return oggetto <code>StatsSeries</code>
     * @throws InvalidStatsFieldException eccezione generata in presenza di errori
     *                                    al parametro <code>fieldName</code>.
     * @throws DataNotFoundException      eccezione generata quando il download dei
     *                                    dati non resituisce un dataset valido.
     */
    public static StatsSeries getCurrencyStats(String fieldName, TimeSeries timeSeries)
            throws InvalidStatsFieldException, DataNotFoundException {

        if (fieldName == null || fieldName.isEmpty())
            fieldName = "Close";

        LinkedHashMap<String, ArrayList<BigDecimal>> currencyValues = new LinkedHashMap<>();
        DataPoint firstDataPoint = timeSeries.getDataSeries().get(0);

        // inizializza la lista con i nomi delle valute e i rispettivi
        // ArrayList<BigDecimal>
        if (!timeSeries.getDataSeries().isEmpty()) {
            for (HistoricalQuote hsQuote : firstDataPoint.getHistoricalQuotes()) {
                currencyValues.put(hsQuote.getCurrencyPair(), new ArrayList<BigDecimal>());
            }
        } else
            throw new DataNotFoundException(
                    "La richiesta non ha prodotto nessun dato disponibile per le statistiche. Prova a cambiare parametri.");

        // Ricava i dati dagli oggetti DataPoint
        try {
            Method m = HistoricalQuote.class.getDeclaredMethod("get" + fieldName + "Value");
            for (DataPoint dataPoint : timeSeries.getDataSeries()) {
                for (HistoricalQuote hsQuote : dataPoint.getHistoricalQuotes()) {
                    BigDecimal num = (BigDecimal) m.invoke(hsQuote);
                    currencyValues.get(hsQuote.getCurrencyPair()).add(num);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new InvalidStatsFieldException(
                    "Il campo{" + fieldName + "} non è disponibile. Consulta la documentazione.");
        }

        ArrayList<Report> reportList = new ArrayList<>();
        StatsSeries statsSeries = new StatsSeries(timeSeries.getPeriod());

        // Crea gli oggetti report con i dati statistici calcolati
        for (Map.Entry<String, ArrayList<BigDecimal>> value : currencyValues.entrySet()) {
            Report currencyReport = new Report(value.getKey());
            currencyReport.setAverage(StatisticalIndex.average(value.getValue()));
            currencyReport.setVariance(StatisticalIndex.variance(value.getValue()));
            currencyReport.setChange(StatisticalIndex.change(value.getValue().get(0),
                    value.getValue().get(value.getValue().size() - 1)));
            currencyReport.setPtcChange(StatisticalIndex.percentageChange(value.getValue().get(0),
                    value.getValue().get(value.getValue().size() - 1)));
            reportList.add(currencyReport);
        }

        // crea un oggetto StatsSeries con le statistiche elaborate.
        statsSeries.setDataSeries(reportList);
        return statsSeries;
    }
}
