package it.michdev.restwebservice.utils.filter;

import java.util.Calendar;
import it.michdev.restwebservice.exception.InvalidPeriodException;
import it.michdev.restwebservice.utils.parser.JsonParser;
import it.michdev.restwebservice.utils.time.Period;

public class PeriodFilter implements IFilter<Period> {

    private Period selectedPeriod;

    public PeriodFilter(String bodyRequest) throws InvalidPeriodException {
        selectedPeriod = JsonParser.readNode(bodyRequest, Period.class);
        checkParams();
    }

    private void checkParams() throws InvalidPeriodException {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -1);
        Calendar limitDate = Calendar.getInstance();
        limitDate.set(2015, 0, 1, 0, 0);

        if(selectedPeriod.compareTo(now) <= 0 && selectedPeriod.compareTo(limitDate) > 0)
            throw new InvalidPeriodException(
                    "Il periodo deve essere incluso tra 2015-01-01 e il giorno precedente alla data corrente.");
    }
    
    @Override
    public Period getParam() {
        return this.selectedPeriod;
    }
    
}