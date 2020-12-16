package it.michdev.restwebservice.utils.time;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.exception.IllegalDatePatternException;
import it.michdev.restwebservice.exception.InvalidPeriodException;
import it.michdev.restwebservice.utils.parser.DateParser;

/**
 * La classe <b>Period</code> rappresenta un periodo storico, compreso tra due
 * date ben distinte. Implementa l'interfaccia <code>ITime</code>.
 * 
 * @author Michele Bevilacqua
 */
public class Period implements ITime{

    Calendar periodStartDate, periodEndDate;

    /**
     * Costruttore per la classe <code>Period</code>.
     * 
     * @param startDate data iniziale del periodo.
     * @param endDate   data finale del periodo.
     * @throws InvalidPeriodException      eccezione generata quando la data finale
     *                                     è precedente alla data d'inizio.
     * @throws IllegalDatePatternException eccezione generata quando il formato
     *                                     delle date inserite è errato.
     */
    public Period(@JsonProperty("start_date") String startDate, @JsonProperty("end_date") String endDate)
            throws InvalidPeriodException, IllegalDatePatternException {
        this.periodStartDate = DateParser.parseDate(startDate);
        this.periodEndDate = DateParser.parseDate(endDate);
        if (periodStartDate.compareTo(this.periodEndDate) > 0)
            throw new InvalidPeriodException("La data finale non può essere precedente alla data d'inizio.");
    }

    /**
     * Restituisce la data iniziale del periodo.
     * 
     * @return <code>Calendar</code> della data iniziale.
     */
    @Override
    public Calendar getStartDate() {
        return this.periodStartDate;
    }

    /**
     * Restituisce la data finale del periodo.
     * 
     * @return Calendar della data finale del periodo.
     */
    @Override
    public Calendar getEndDate() {
        return this.periodEndDate;
    }

    /**
     * Confronta l'ordine cronologico tra il periodo rappresentato dall'oggetto
     * stesso e la data passata come Calendar. Restitusce un numero intero relativo
     * alla posizione cronologica:
     * <ul>
     * <li><code>-1</code> se data è precedente al periodo;</li>
     * <li><code>0</code> se la data è inclusa nel periodo(inclusi estremi);</li>
     * <li><code>1</code> se la data è successiva al periodo;</li>
     * </ul>
     * 
     * @param dateToCompare oggetto <code>Calendar</code> da confrontare.
     * @return valore intero rappresentante la posizione cronologica della data.
     */
    @Override
    public Integer compareTo(Calendar dateToCompare) {
        if (dateToCompare.compareTo(periodStartDate) < 0)
            return -1;
        else if (dateToCompare.compareTo(periodEndDate) > 0)
            return 1;
        else
            return 0;
    }
}