/*******************************************************************************
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2014-2017 Marc de Verdelhan, 2017-2018 Ta4j Organization
 *   & respective authors (see AUTHORS)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy of
 *   this software and associated documentation files (the "Software"), to deal in
 *   the Software without restriction, including without limitation the rights to
 *   use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *   the Software, and to permit persons to whom the Software is furnished to do so,
 *   subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *   FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *   COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *   IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/

package org.ta4j.core.indicators.candles;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

/**
 * Candle coverage indicator.
 * </p>
 * Provides the coverage of candle body by dividing the difference between the open price
 * and the close price of a bar with difference between high price and close price of a
 * bar. I.e.: ( close price - open price ) / ( high price - low price )
 */
public class CoverageIndicator extends CachedIndicator<Num> {

    private static final long serialVersionUID = 6836150635774499558L;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public CoverageIndicator(final TimeSeries series) {
        super(series);
    }

    @Override
    protected Num calculate(final int index) {
        final Bar bar = getTimeSeries().getBar(index);
        return (bar.getClosePrice().minus(bar.getOpenPrice())) //
                .dividedBy(bar.getMaxPrice().minus(bar.getMinPrice()));
    }
}
