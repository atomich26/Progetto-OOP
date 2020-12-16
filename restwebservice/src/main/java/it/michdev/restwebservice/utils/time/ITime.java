package it.michdev.restwebservice.utils.time;

import java.util.Calendar;

/**
 * @author Michele Bevilacqua
 */
public interface ITime {
    
    public abstract Calendar getStartDate();

    public abstract Calendar getEndDate();

    public abstract Integer compareTo(Calendar dateToCompare);
}
