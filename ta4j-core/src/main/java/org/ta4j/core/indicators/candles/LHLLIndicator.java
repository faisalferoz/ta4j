
package org.ta4j.core.indicators.candles;

import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;

/**
 * Lower High, Low Low indicator.
 * </p>
 *
 * @see <a href="https://www.investopedia.com/articles/technical/04/080404.asp">
 *      https://www.investopedia.com/articles/technical/04/080404.asp</a>
 */
public class LHLLIndicator extends CachedIndicator<Boolean> {

    private static final long serialVersionUID = 5029587222777743555L;

    private final LHIndicator lh;

    private final LLIndicator ll;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public LHLLIndicator(final TimeSeries series) {
        super(series);
        this.lh = new LHIndicator(series);
        this.ll = new LLIndicator(series);
    }

    @Override
    protected Boolean calculate(final int index) {
        return lh.getValue(index) && ll.getValue(index);
    }
}
