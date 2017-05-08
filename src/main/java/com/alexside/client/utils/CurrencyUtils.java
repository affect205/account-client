package com.alexside.client.utils;

import java.math.BigDecimal;

/**
 * Created by Alex on 08.05.2017.
 */
public class CurrencyUtils {
    public static BigDecimal toBigDecimal(double amount) {
        return BigDecimal.valueOf(amount).setScale(2, BigDecimal.ROUND_CEILING);
    }

    public static String toRub(double amount) {
        BigDecimal decimal = toBigDecimal(amount);
        return String.format("%s руб.", decimal.toString());
    }
}
