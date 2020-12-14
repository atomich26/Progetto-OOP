package it.michdev.restwebservice.utils.time;

import java.util.Calendar;

public interface ITime {
    
    public abstract Calendar getStartDate();

    public abstract String getStartDateAsString();

    public abstract Calendar getEndDate();

    public abstract String getEndDateAsString();

    public abstract Integer compareTo(Calendar dateToCompare);
}
