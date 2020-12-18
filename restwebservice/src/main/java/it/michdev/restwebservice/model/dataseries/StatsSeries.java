package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.michdev.restwebservice.model.Report;
import it.michdev.restwebservice.utils.parser.DateParser;
import it.michdev.restwebservice.utils.stats.sort.Sort;
import it.michdev.restwebservice.utils.time.Period;

@JsonPropertyOrder({"start_date", "end_date", "trend", "data_series"})
public class StatsSeries extends DataSeries<Report> {

    private Period period;
    private Sort<Report> currencyTrend;
    private ArrayList<Report> reportList;

    public StatsSeries(Period period) {
        super();
        this.period = period;
        reportList = new ArrayList<Report>();
    }
    
    @Override
    @JsonProperty("data_series")
    public ArrayList<Report> getDataSeries() {
        return this.reportList;
    }

	@Override
    public void setDataSeries(ArrayList<Report> reportList) {
        this.reportList = reportList;
        if (reportList.size() > 1) 
            currencyTrend = new Sort<>(reportList);
    }
    
    @JsonProperty("start_date")
    public String getStartDate() {
        return DateParser.getDateAsString(period.getStartDate(), DateParser.YYYYMMDD);
    }
    
    @JsonProperty("end_date")
    public String getEndDate() {
        return DateParser.getDateAsString(period.getEndDate(), DateParser.YYYYMMDD);
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @JsonProperty("trend")
    public Sort<Report> getCurrencyTrend() {
        return this.currencyTrend;
    }
}
