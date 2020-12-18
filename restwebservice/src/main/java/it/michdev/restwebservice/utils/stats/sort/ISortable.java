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

    public BigDecimal getChangeValue();

    public BigDecimal getPctChangeValue();

    public String getCurrencyPair();
}
