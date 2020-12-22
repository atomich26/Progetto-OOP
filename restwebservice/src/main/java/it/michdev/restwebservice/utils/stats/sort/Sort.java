package it.michdev.restwebservice.utils.stats.sort;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.michdev.restwebservice.model.Report;

/**
 * La classe <code>Sort</code> rappresenta l'operatore che svolge le operazioni
 * di ordinamento di oggetti <code>ISortable</code> in base al valore statistico
 * <i>differenza</i> e al valore della <i>differenza percentuale</i>,
 * restituendo un report sulle coppie di valute che rispettivamente hanno
 * guadagnato/perso di più.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.stats.sort.ISortable
 */
public class Sort<T extends ISortable> {

    private Report worst;
    private Report best;
    private ArrayList<T> sortedDataList;

    /**
     * Costruttore per la classe <code>Sort</code>.
     * 
     * @param dataListToSort set di dati da ordinare.
     */
    public Sort(ArrayList<T> dataListToSort) {
        this.sortedDataList = new ArrayList<T>();
        this.sortedDataList.addAll(dataListToSort);
        sortList();
    }

    /**
     * Ordina il set di dati che riceve come argomento del costruttore, ordina in
     * modo crescente e seleziona gli estremi assegnandoli ad un oggetto
     * <code>Report</code>.
     */
    private void sortList() {
        // Ordina la lista
        this.sortedDataList.sort((c1, c2) -> c1.getPctChangeValue().compareTo(c2.getPctChangeValue()));

        // trova la valuta peggiore
        T worstCurrency = sortedDataList.get(0);
        worst = new Report(worstCurrency.getCurrencyPair());
        worst.setChange(worstCurrency.getChangeValue());
        worst.setPtcChange(worstCurrency.getPctChangeValue());

        // trova la valuta migliore
        T bestCurrency = sortedDataList.get(sortedDataList.size() - 1);
        best = new Report(bestCurrency.getCurrencyPair());
        best.setChange(bestCurrency.getChangeValue());
        best.setPtcChange(bestCurrency.getPctChangeValue());
    }

    /**
     * Restituisce il report della valuta peggiore
     * 
     * @return <code>Report</code> della valuta peggiore.
     */
    @JsonProperty("worst")
    public Report getWorst() {
        return this.worst;
    }

    /**
     * Restituisce il report della valuta migliore.
     * 
     * @return <code>Report</code> della valuta migliore.
     */
    @JsonProperty("best")
    public Report getBest() {
        return this.best;
    }
}
