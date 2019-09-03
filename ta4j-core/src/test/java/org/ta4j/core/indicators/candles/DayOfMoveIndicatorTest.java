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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.mocks.MockBar;
import org.ta4j.core.mocks.MockTimeSeries;
import org.ta4j.core.num.Num;

public class DayOfMoveIndicatorTest extends AbstractIndicatorTest<Indicator<Num>, Num> {

    private TimeSeries series;

    public DayOfMoveIndicatorTest(final Function<Number, Num> numFunction) {
        super(numFunction);
    }

    @Before
    public void setUp() {
        final List<Bar> bars = new ArrayList<Bar>();
        // open, close, high, low
        bars.add(new MockBar(135.3, 134.21, 135.98, 132.1, numFunction));
        bars.add(new MockBar(134.44, 144.54, 147.49, 134, numFunction));
        bars.add(new MockBar(144, 139, 146.93, 134.05, numFunction));
        bars.add(new MockBar(139, 116.99, 139.89, 107.72, numFunction));
        bars.add(new MockBar(116.38, 105.21, 125.6, 92.28, numFunction));
        series = new MockTimeSeries(bars);
    }

    @Test
    public void getValue() {
        final DayOfMoveIndicator indicator = new DayOfMoveIndicator(series);
        assertEquals(series.numOf(0), indicator.getValue(0));
        assertEquals(series.numOf(1), indicator.getValue(1));
        assertEquals(series.numOf(0), indicator.getValue(2));
        assertEquals(series.numOf(1), indicator.getValue(3));
        assertEquals(series.numOf(2), indicator.getValue(4));
    }
}
