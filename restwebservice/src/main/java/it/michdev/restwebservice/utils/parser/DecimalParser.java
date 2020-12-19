package it.michdev.restwebservice.utils.parser;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * La classe <code>DecimalParser</code> implementa metodi per convertire i valori
 * delle valute in <code>Double</code> in oggetti <code>BigDecimal</code> per
 * una maggiore precisione.
 * 
 * @version 1.1.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.parser.DateParser
 * @see it.michdev.restwebservice.utils.parser.JsonParser
 */
public class DecimalParser {

    /**
     * Converte un numero <code>Double</code> in oggetto <code>BigDecimal</code> con
     * una precisione definita dall'enum <code>MathContext.DECIMAL64</code> e
     * arrotondato alla 5 cifra decimale.
     * 
     * @param value <code>Double</code> da convertire.
     * @return numero converito in <code>BigDecimal</code>.
     */
    public static BigDecimal parseDouble(Double value) {
        BigDecimal parsedDouble = new BigDecimal(value, MathContext.DECIMAL64);
        parsedDouble = parsedDouble.setScale(5, RoundingMode.HALF_EVEN);
        return parsedDouble;
    }
}
