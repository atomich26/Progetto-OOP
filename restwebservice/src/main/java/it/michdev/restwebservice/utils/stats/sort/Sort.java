package it.michdev.restwebservice.utils.stats.sort;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.model.CurrencyPair;
import it.michdev.restwebservice.model.Report;

/**
 * La classe <b>Sort</b> rappresenta l'operatore che svolge le operazioni di
 * ordinamento di oggetti <code>CurrencyPair</code> in base alla normale
 * differenza e alla differenza percentuale, restituendo un report sulle coppie
 * di valute che rispettivamente hanno guadagnato/perso di più.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.stats.sort.ISortable
 */
public class Sort<T extends CurrencyPair> {

    private Report worst;
    private Report best;
    ArrayList<T> dataList;

    /**
     * Costruttore per la classe <code>Sort</code>.
     * @param dataListToSort set di dati da ordinare.
     */
    public Sort(ArrayList<T> dataListToSort) {
        this.dataList = new ArrayList<T>();
        this.dataList.addAll(dataListToSort);
        sortList();
    }

    /**
     * Ordina il set di dati che riceve come argomento del costruttore, ordina in modo crescente e seleziona 
     * gli estremi assegnandoli ad un oggetto <code>Report</code>.
     */
    private void sortList() {
        // Ordina la lista
        this.dataList.sort((c1, c2) -> c1.getChangeValue().compareTo(c2.getChangeValue()));

        // trova la valuta peggiore
        CurrencyPair worstCurrency = dataList.get(0);
        worst = new Report(worstCurrency.getCurrencyPair());
        worst.setChange(worstCurrency.getChangeValue());
        worst.setPtcChange(worstCurrency.getPctChangeValue());

        // trova la valuta migliore
        CurrencyPair bestCurrency = dataList.get(dataList.size() - 1);
        best = new Report(bestCurrency.getCurrencyPair());
        best.setChange(bestCurrency.getChangeValue());
        best.setPtcChange(bestCurrency.getPctChangeValue());
    }

    /**
     * Restituisce il report della valuta peggiore
     * @return <code>Report</code> della valuta peggiore.
     */
    @JsonProperty("worst")
    public Report getWorst() {
        return this.worst;
    }

    /**
     * Restituisce il report della valuta migliore.
     * @return <code>Report</code> della valuta migliore.
     */
    @JsonProperty("best")
    public Report getBest() {
        return this.best;
    }
}
