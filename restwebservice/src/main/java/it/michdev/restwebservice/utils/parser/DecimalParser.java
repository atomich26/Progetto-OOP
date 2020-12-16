package it.michdev.restwebservice.utils.parser;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Michele Bevilacqua
 */
public class DecimalParser {
    
    public static BigDecimal parseDouble(Double value) {
        BigDecimal parsedDouble = new BigDecimal(value);
        parsedDouble = parsedDouble.setScale(5, RoundingMode.HALF_EVEN);
        return parsedDouble;
    }
}
