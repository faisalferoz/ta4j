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
package org.ta4j.core.indicators.helpers;

import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.CachedIndicator;
/**
 * Returns the previous (n-th) value of an indicator
 * </p>
 */
public class PreviousValueIndicator<T> extends CachedIndicator<T> {

    private static final long serialVersionUID = -5155308631346745382L;

    private int n;
    private Indicator<T> indicator;

    /**
     * Constructor.
     * @param indicator the indicator of which the previous value should be calculated
     */
    public PreviousValueIndicator(Indicator<T> indicator) {
        this(indicator,1);
    }

    /**
     * Constructor.
     * @param indicator the indicator of which the previous value should be calculated
     * @param n parameter defines the previous n-th value
     */
    public PreviousValueIndicator(Indicator<T> indicator, int n){
        super(indicator);
        this.n = n;
        this.indicator = indicator;
    }

    protected T calculate(int index) {
        int previousValue = Math.max(0, (index-n));
        return this.indicator.getValue(previousValue);
    }
}
