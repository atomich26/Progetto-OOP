package it.michdev.restwebservice.model.dataseries;

import java.util.ArrayList;
import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.michdev.restwebservice.model.LiveQuote;
import it.michdev.restwebservice.service.DataService;
import it.michdev.restwebservice.utils.parser.DateParser;

@JsonPropertyOrder({"code", "name","last_refreshed", "quotes" })
@JsonInclude(Include.NON_NULL)
public class LiveSeries extends DataSeries<LiveQuote>{

    private Calendar timeStamp;
    private ArrayList<LiveQuote> liveQuoteList;

    public LiveSeries(String baseCurrencyCode) {
        super(baseCurrencyCode);
        this.timeStamp = DataService.lastUpdate;
    }

    public LiveSeries() {
        super();
        this.timeStamp = DataService.lastUpdate;
    }

    @Override
    @JsonProperty("quotes")
    public ArrayList<LiveQuote> getDataSeries() {
        return this.liveQuoteList;
    }

    @Override
    public void setDataSeries(ArrayList<LiveQuote> dataList) {
        this.liveQuoteList = dataList;

    }

    @JsonProperty("last_refreshed")
    public String getTimeStampAsString() {
        return DateParser.getDateAsString(timeStamp,"yyyy-MM-dd-HH:mm");
    }
}
