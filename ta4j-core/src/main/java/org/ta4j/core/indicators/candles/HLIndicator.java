
package org.ta4j.core.indicators.candles;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;

/**
 * Higher Low indicator.
 * </p>
 *
 * @see <a href="https://www.investopedia.com/articles/technical/04/080404.asp">
 *      https://www.investopedia.com/articles/technical/04/080404.asp</a>
 */
public class HLIndicator extends CachedIndicator<Boolean> {

    private static final long serialVersionUID = -6656574575739130421L;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public HLIndicator(final TimeSeries series) {
        super(series);
    }

    @Override
    protected Boolean calculate(final int index) {
        if (index < 1) {
            return true;
        }
        final Bar prevBar = getTimeSeries().getBar(index - 1);
        final Bar currBar = getTimeSeries().getBar(index);
        return currBar.getMinPrice().isGreaterThan(prevBar.getMinPrice());
    }
}
