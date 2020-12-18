package it.michdev.restwebservice.utils.stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * La classe <code>StatisticalIndex</code> disponde di metodi per il calcolo dei
 * più importanti indici statistici come la media, la varianza, la differenza
 * percentuale e operazioni matematiche come la differenza tra due valori.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 */
public class StatisticalIndex {

    /**
     * Calcola la differenza percentuale tra due valori <code>BigDecimal</code>.
     * 
     * @param baseValue    valore iniziale
     * @param changedValue valore finale
     * @return oggetto <code>BigDecimal</code>
     */
    public static BigDecimal percentageChange(BigDecimal baseValue, BigDecimal changedValue) {
        try {
            BigDecimal percentageChange = change(baseValue, changedValue).divide(baseValue, MathContext.DECIMAL64);
            percentageChange = percentageChange.multiply(new BigDecimal(100), MathContext.DECIMAL64);
            return percentageChange.setScale(3, RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            return new BigDecimal(0);
        }
    }

    /**
     * Calcola la differenza due valori <code>BigDecimal</code>.
     * 
     * @param baseValue    minuendo
     * @param changedValue sottraendo
     * @return oggetto <code>BigDecimal</code>
     */
    public static BigDecimal change(BigDecimal baseValue, BigDecimal changedValue) {
        BigDecimal change = changedValue.subtract(baseValue, MathContext.DECIMAL64);
        return change.setScale(5, RoundingMode.HALF_EVEN);
    }

    /**
     * Calcola la media di un set di dati di tipo <code>BigDecimal</code>.
     * 
     * @param dataList set di numeri con cui calcolare la media
     * @return oggetto <code>BigDecimal</code>
     */
    public static BigDecimal average(ArrayList<BigDecimal> dataList) {
        try {
            BigDecimal average = new BigDecimal(0, MathContext.DECIMAL64);
            for (BigDecimal value : dataList) {
                average = average.add(value);
            }
            average = average.divide(new BigDecimal(dataList.size()), MathContext.DECIMAL64);
            return average.setScale(5,RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            return new BigDecimal(0);
        }
    }

    /**
     * Calcola la varianza di un set di dati di tipo <code>BigDecimal</code>.
     * 
     * @param dataList set di numeri con cui calcolare la varianza
     * @return oggetto <code>BigDecimal</code>
     */
    public static BigDecimal variance(ArrayList<BigDecimal> dataList) {
        BigDecimal average = average(dataList).setScale(5, RoundingMode.HALF_EVEN);
        BigDecimal variance = new BigDecimal(0, MathContext.DECIMAL64).setScale(5, RoundingMode.HALF_EVEN);

        try {
            for (BigDecimal value : dataList) {
                BigDecimal difference = change(average, value).setScale(5,
                        RoundingMode.HALF_EVEN);
                difference = difference.pow(2, MathContext.DECIMAL64);
                variance = variance.add(difference);
            }

            variance = variance.divide(new BigDecimal(dataList.size()), MathContext.DECIMAL64);
            return variance.setScale(7, RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            return new BigDecimal(0);
        }
    }
}
