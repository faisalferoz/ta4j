
package org.ta4j.core.indicators;

import static org.junit.Assert.*;
import static org.ta4j.core.TestUtils.*;

import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.ExternalIndicatorTest;
import org.ta4j.core.Indicator;
import org.ta4j.core.TestUtils;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.mocks.MockTimeSeries;
import org.ta4j.core.num.Num;

public class TrendStrengthIndicatorTest extends AbstractIndicatorTest<Indicator<Num>, Num> {

    private TimeSeries data;

    private final ExternalIndicatorTest xls;

    public TrendStrengthIndicatorTest(final Function<Number, Num> numFunction) {
        super((data, params) -> new TrendStrengthIndicator(data), numFunction);
        xls = new XLSIndicatorTest(this.getClass(), "TrendStrength.xls", 26, numFunction);
    }

    @Before
    public void setUp() throws Exception {
        data = new MockTimeSeries(numFunction,
                50.45, 50.30, 50.20,
                50.15, 50.05, 50.06,
                50.10, 50.08, 50.03,
                50.07, 50.01, 50.14,
                50.22, 50.43, 50.50,
                50.56, 50.52, 50.70,
                50.55, 50.62, 50.90,
                50.82, 50.86, 51.20,
                51.30, 51.10);
    }

    @Test
    public void firstTwoSeventyValueShouldBeNone() throws Exception {
        final Indicator<Num> indicator = getIndicator(new ClosePriceIndicator(data), 14);
        for (int i=0 ;i< 270; i++) {
            assertEquals(data.numOf(TrendStrength.NONE.getValue()), indicator.getValue(i));
        }
    }

    @Test
    public void xlsTest() throws Exception {
        final Indicator<Num> xlsClose = new ClosePriceIndicator(xls.getSeries());
        Indicator<Num> indicator;

        indicator = getIndicator(xlsClose);
        assertIndicatorEquals(xls.getIndicator(), indicator);
        assertEquals(TrendStrength.UPTREND_SIDEWAYS.getValue(), indicator.getValue(indicator.getTimeSeries().getEndIndex()).doubleValue(), TestUtils.GENERAL_OFFSET);
    }

}
