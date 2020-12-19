package it.michdev.restwebservice.utils.time;

import java.util.Calendar;

/**
 * L'interfaccia <code>ITime</code> definisce il comportamento di tutti gli
 * oggetti che rappresentano un periodo di tempo, come la classe
 * <code>Period</code>.
 * 
 * @version 1.1.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.time.Period
 */
public interface ITime {

    /**
     * Restituisce la data iniziale del periodo di tempo.
     * 
     * @return <code>Calendar</code> della data iniziale.
     */
    public abstract Calendar getStartDate();

    /**
     * Restituisce la data finale del periodo di tempo.
     * 
     * @return <code>Calendar</code> della data finale.
     */
    public abstract Calendar getEndDate();
}
