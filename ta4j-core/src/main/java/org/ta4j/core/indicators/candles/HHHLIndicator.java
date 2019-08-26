
package org.ta4j.core.indicators.candles;

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

    private final HHIndicator hh;

    private final HLIndicator hl;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public HHHLIndicator(final TimeSeries series) {
        super(series);
        this.hh = new HHIndicator(series);
        this.hl = new HLIndicator(series);
    }

    @Override
    protected Boolean calculate(final int index) {
        return hh.getValue(index) && hl.getValue(index);
    }
}
