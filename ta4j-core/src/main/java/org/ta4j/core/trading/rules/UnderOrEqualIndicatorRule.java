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
package org.ta4j.core.trading.rules;

import org.ta4j.core.Indicator;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.helpers.ConstantIndicator;
import org.ta4j.core.num.Num;

/**
 * Indicator-under-indicator rule.
 * </p>
 * Satisfied when the value of the first {@link Indicator indicator} is strictly lesser than the value of the second one.
 */
public class UnderOrEqualIndicatorRule extends AbstractRule {

    /** The first indicator */
    private Indicator<Num> first;
    /** The second indicator */
    private Indicator<Num> second;

    /**
     * Constructor.
     * @param indicator the indicator
     * @param threshold a threshold
     */
    public UnderOrEqualIndicatorRule(Indicator<Num> indicator, Number threshold) {
        this(indicator, new ConstantIndicator<Num>(indicator.getTimeSeries(), indicator.numOf(threshold)));
    }

    /**
     * Constructor.
     * @param indicator the indicator
     * @param threshold a threshold
     */
    public UnderOrEqualIndicatorRule(Indicator<Num> indicator, Num threshold) {
        this(indicator, new ConstantIndicator<Num>(indicator.getTimeSeries(), threshold));
    }

    /**
     * Constructor.
     * @param first the first indicator
     * @param second the second indicator
     */
    public UnderOrEqualIndicatorRule(Indicator<Num> first, Indicator<Num> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        final boolean satisfied = first.getValue(index).isLessThanOrEqual(second.getValue(index));
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}
