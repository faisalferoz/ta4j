
package org.ta4j.core.indicators.candles;

import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;

/**
 * Day of Move indicator.
 *
 */
public class DayOfMoveIndicator extends CachedIndicator<Integer> {

    private static final long serialVersionUID = -6656574575739130421L;

    private final PriceActivityIndicator priceActivityIndicator;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public DayOfMoveIndicator(final TimeSeries series) {
        super(series);
        this.priceActivityIndicator = new PriceActivityIndicator(series);
    }

    @Override
    protected Integer calculate(final int index) {
        if (index < 1) {
            return 0;
        }

        final PriceActivity priceActivity = priceActivityIndicator.getValue(index);

        final Integer firstDay = isFirstDay(priceActivity, index);
        if (firstDay == 0) {
            return 0;
        }

        if (firstDay == 1) {
            return 1;
        }

        if ((priceActivity == PriceActivity.BULL || priceActivity == PriceActivity.BEAR)) {
            return this.getValue(index - 1) + 1;
        }

        return this.getValue(index - 1);

    }

    /**
     * Returns whether this is the first day of move or not.
     *
     * @param priceActivity current price activity
     * @param index the index to check
     * @return 1 if its is first day of move. -1 if it isn't and 0 otherwise. Note: zero
     *         is treated as neutral.
     */
    private Integer isFirstDay(final PriceActivity priceActivity, final int index) {
        if ((priceActivity == PriceActivity.BULL || priceActivity == PriceActivity.BEAR)) {
            if (priceActivity != priceActivityIndicator.getValue(index - 1)) {
                return 1;
            }
            return -1;
        }
        return 0;
    }
}
