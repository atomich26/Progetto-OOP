package it.michdev.restwebservice.utils.stats.sort;

import java.math.BigDecimal;

/**
 * L'interfaccia <b>ISortable</b> include tutti gli oggetti
 * <code>CurrencyPair</code> che possono essere ordinati in una rispettiva
 * <code>Collection</code> in base al valore della normale differenza tra valori
 * e la differenza percentuale.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.stats.sort.Sort
 */
public interface ISortable {

    /**
     * Restituisce il valore calcolato dell'indice statistico <i>change</i>.
     * @return oggetto <code>BigDecimal</code>
     */
    public BigDecimal getChangeValue();

    /**
     * Restituisce il valore calcolato dell'indice statistico <i>percentage change</i>.
     * @return oggetto <code>BigDecimal</code>
     */
    public BigDecimal getPctChangeValue();

    /**
     * Restituisce il codice della coppia di valute elaborata.
     * @return oggetto <code>BigDecimal</code>
     */
    public String getCurrencyPair();
}
