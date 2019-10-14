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
package org.ta4j.core.indicators;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Indicator;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.mocks.MockTimeSeries;
import org.ta4j.core.num.Num;

import java.util.function.Function;

import static org.ta4j.core.TestUtils.assertNumEquals;

public class AlnoorTripleEMAIndicatorTest extends AbstractIndicatorTest<Indicator<Num>, Num>{

    private ClosePriceIndicator closePrice;

    public AlnoorTripleEMAIndicatorTest(final Function<Number, Num> numFunction) {
        super(numFunction);
    }

    @Before
    public void setUp() {
        final TimeSeries data = new MockTimeSeries(numFunction,
                134.21, 144.54, 139, 116.99, 105.21,
                97.75, 112.5, 115.91, 112.3, 111.5,
                113.57, 112.67, 117.2, 115.24, 115
        );
        closePrice = new ClosePriceIndicator(data);
    }

    @Test
    public void alnoorTripleEMAUsingBarCount5UsingClosePrice() {
        final AlnoorTripleEMAIndicator alnoorTripleEma = new AlnoorTripleEMAIndicator(closePrice, 5);

        assertNumEquals(134.21, alnoorTripleEma.getValue(0));
        assertNumEquals(140.2358, alnoorTripleEma.getValue(1));
        assertNumEquals(139.94, alnoorTripleEma.getValue(2));

        assertNumEquals(116.8486, alnoorTripleEma.getValue(12));
        assertNumEquals(116.6912, alnoorTripleEma.getValue(13));
        assertNumEquals(115.9625, alnoorTripleEma.getValue(14));
    }
}
