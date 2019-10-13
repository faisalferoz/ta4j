
package org.ta4j.core.indicators.candles;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;

/**
 * Lower low indicator.
 * </p>
 *
 * @see <a href="https://www.investopedia.com/articles/technical/04/080404.asp">
 *      https://www.investopedia.com/articles/technical/04/080404.asp</a>
 */
public class LLIndicator extends CachedIndicator<Boolean> {

    private static final long serialVersionUID = 7816936595211326335L;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public LLIndicator(final TimeSeries series) {
        super(series);
    }

    @Override
    protected Boolean calculate(final int index) {
        if (index < 1) {
            return false;
        }
        final Bar prevBar = getTimeSeries().getBar(index - 1);
        final Bar currBar = getTimeSeries().getBar(index);
        return currBar.getMinPrice().isLessThan(prevBar.getMinPrice());
    }
}
