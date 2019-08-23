
package org.ta4j.core.indicators.candles;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;

/**
 * Higher high, Higher Low indicator.
 * </p>
 *
 * @see <a href="https://www.investopedia.com/articles/technical/04/080404.asp">
 *      https://www.investopedia.com/articles/technical/04/080404.asp</a>
 */
public class HHHLIndicator extends CachedIndicator<Boolean> {

    private static final long serialVersionUID = 5029587222777743555L;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public HHHLIndicator(final TimeSeries series) {
        super(series);
    }

    @Override
    protected Boolean calculate(final int index) {
        if (index < 1) {
            // HHHL is a 2-candle pattern
            return false;
        }
        final Bar prevBar = getTimeSeries().getBar(index - 1);
        final Bar currBar = getTimeSeries().getBar(index);
        return currBar.getClosePrice().isGreaterThan(prevBar.getClosePrice()) // HH
                && currBar.getMinPrice().isGreaterThan(prevBar.getMinPrice()); // HL
    }
}
