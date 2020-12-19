package it.michdev.restwebservice.utils.filter;

import java.util.Calendar;
import com.fasterxml.jackson.core.JsonProcessingException;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.exception.InvalidPeriodException;
import it.michdev.restwebservice.utils.parser.DateParser;
import it.michdev.restwebservice.utils.parser.JsonParser;
import it.michdev.restwebservice.utils.time.Period;

/**
 * Un <code>PeriodFilter</code> è un oggetto filtro che implementa l'interfaccia
 * <code>IFilter</code>. Verifica la validità dei parametri inseriti dall'utente
 * e li restituisce per filtrare i dati in base al periodo.
 * 
 * @version 1.1.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.filter.IFilter
 * @see it.michdev.restwebservice.utils.filterCurrencyFilter
 */
public class PeriodFilter implements IFilter<Period> {

    private Period selectedPeriod;

    /**
     * Costruttore per la classe <code>Period</code>.
     * 
     * @param period periodo filtro.
     */
    public PeriodFilter(Period period) {
        this.selectedPeriod = period;
    }

    /**
     * Costruttore per la classe <code>PeriodFilter</code> che ha come argomento il
     * body della riciesta HTTP da parsare.
     * 
     * @param bodyRequest stringa del body inoltrato con le chiamate HTTP.
     * @throws InvalidPeriodException      eccezione generata in caso di periodi non
     *                                     validi.
     * @throws IllegalDatePatternException eccezione generata in caso di date mal
     *                                     formattate.
     */
    public PeriodFilter(String bodyRequest) throws InvalidPeriodException, IllegalDatePatternException {
        initParams(bodyRequest);
    }

    /**
     * Restituisce il parametro di base del filtro.
     * 
     * @return stringa della valuta di base.
     */
    @Override
    public Period getParam() {
        return this.selectedPeriod;
    }

    /**
     * Inizializza i parametri del filtro dopo aver verificato la validità delle
     * date inserite dall'utente e quindi la validità del periodoc creato. In
     * presenza di errori o parametri non accetabili genera eccezioni.
     * 
     * @param bodyRequest stringa del body con cui effettuare il parsing.
     * @throws InvalidPeriodException      eccezione generata in caso di periodi non
     *                                     validi
     * @throws IllegalDatePatternException eccezione generata in caso di date mal
     *                                     formattate.
     */
    private void initParams(String bodyRequest) throws InvalidPeriodException, IllegalDatePatternException {
        // Controlla il formato delle date e inizializza un oggetto di tipo Period.
        try {
            Calendar startDate = DateParser.parseDate(JsonParser.readNode(bodyRequest).get("start_date").asText());
            Calendar endDate = DateParser.parseDate(JsonParser.readNode(bodyRequest).get("end_date").asText());
            this.selectedPeriod = new Period(startDate, endDate);
        } catch (IllegalDatePatternException e) {
            throw new IllegalDatePatternException("Formato della data non valido. Formato accettato {yyyy-MM-dd}");
        } catch (NullPointerException | JsonProcessingException e) {
            throw new InvalidPeriodException("Il body deve contenere i campi [end_date] e [start_date]");
        }

        // Controlla se la data iniziale è succeesiva a quella finale e che entrambe non
        // corrispondano ad un giorno di weekend.
        if (selectedPeriod.getStartDate().after(selectedPeriod.getEndDate()))
            throw new InvalidPeriodException("La data finale non può essere precedente a quella iniziale.");
        else if (selectedPeriod.getStartDate().get(Calendar.DAY_OF_WEEK) == 7
                || selectedPeriod.getStartDate().get(Calendar.DAY_OF_WEEK) == 1)
            throw new InvalidPeriodException("La data iniziale corrisponde ad un weekend");
        else if (selectedPeriod.getEndDate().get(Calendar.DAY_OF_WEEK) == 7
                || selectedPeriod.getEndDate().get(Calendar.DAY_OF_WEEK) == 1)
            throw new InvalidPeriodException("La data finale corrisponde ad un weekend");

        // Controlla che il periodo inserito è incluso tra il limiti stabiliti.
        Calendar now = Calendar.getInstance();
        Calendar limitDate = Calendar.getInstance();
        limitDate.set(2014, 11, 31, 0, 0);

        if (selectedPeriod.getEndDate().after(now) || selectedPeriod.getStartDate().before(limitDate))
            throw new InvalidPeriodException("Il periodo deve essere incluso tra 2015-01-01 e il giorno corrente.");
    }
}