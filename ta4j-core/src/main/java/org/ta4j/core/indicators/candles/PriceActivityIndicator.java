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

import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;

/**
 * Price Activity indicator.
 * </p>
 * Provides the price activity for the current candle. The rules are as follows: <code>
 * IF (HH AND HL) THEN BULL<br>
 * ELSE IF (LH AND LL) THEN BULL<br>
 * ELSE IF (HL AND LH) THEN INSIDE<br>
 * ELSE IF (HH AND LL) THEN ENGULF<br>
 * ELSE INSIDE
 * </code>
 */
public class PriceActivityIndicator extends CachedIndicator<PriceActivity> {

    private static final long serialVersionUID = 1405882046943119276L;

    private final HHIndicator hh;

    private final HLIndicator hl;

    private final LHIndicator lh;

    private final LLIndicator ll;

    /**
     * Constructor.
     *
     * @param series a time series
     */
    public PriceActivityIndicator(final TimeSeries series) {
        super(series);
        this.hh = new HHIndicator(series);
        this.hl = new HLIndicator(series);
        this.lh = new LHIndicator(series);
        this.ll = new LLIndicator(series);
    }

    @Override
    protected PriceActivity calculate(final int index) {
        if (hh.getValue(index) && hl.getValue(index)) {
            return PriceActivity.BULL;
        }
        if (lh.getValue(index) && ll.getValue(index)) {
            return PriceActivity.BEAR;
        }
        if (hl.getValue(index) && lh.getValue(index)) {
            return PriceActivity.INSIDE;
        }
        if (hh.getValue(index) && ll.getValue(index)) {
            return PriceActivity.ENGULF;
        }

        return PriceActivity.INSIDE;
    }
}
