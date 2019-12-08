
package org.ta4j.core.indicators;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class TrendStrengthTest {

    @Test
    public void shouldGenerateFromIntValue() {
        assertEquals(TrendStrength.DOWN_TREND, TrendStrength.fromValue(-70));
    }

    @Test
    public void shouldGenerateFromDoubleValue() {
        assertEquals(TrendStrength.DOWN_TREND, TrendStrength.fromValue(-70d));
    }

    @Test
    public void shouldGenerateFromBigDecimalValue() {
        assertEquals(TrendStrength.DOWN_TREND, TrendStrength.fromValue(new BigDecimal("-70")));
    }

}
