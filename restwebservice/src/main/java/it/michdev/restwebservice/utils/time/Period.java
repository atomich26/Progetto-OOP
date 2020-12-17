package it.michdev.restwebservice.utils.time;

import java.util.Calendar;

/**
 * La classe <b>Period</b> rappresenta un periodo storico, compreso tra due
 * date ben distinte. Implementa l'interfaccia <code>ITime</code>.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.time.ITime;
 */
public class Period implements ITime {

    Calendar periodStartDate, periodEndDate;

    /**
     * Costruttore per la classe <code>Period</code>.
     * 
     * @param startDate data iniziale del periodo.
     * @param endDate   data finale del periodo.
     */
    public Period(Calendar startDate, Calendar endDate){
        this.periodStartDate = startDate;
        this.periodEndDate = endDate;
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
     * @return <code>Calendar</code> della data finale del periodo.
     */
    @Override
    public Calendar getEndDate() {
        return this.periodEndDate;
    }
}