/*
 * #region
 * Dowbits
 * %%
 * Copyright (C) 2018 AInstien
 * %%
 * NOTICE: All information contained herein is, and remains the property of CRYPTO.
 * The intellectual and technical concepts contained herein are proprietary to
 * CRYPTO and may be covered by U.S. and Foreign Patents, patents in process, and
 * are protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from CRYPTO. Access to the source code contained herein
 * is hereby forbidden to anyone except current CRYPTO employees, managers or
 * contractors who have executed Confidentiality and Non-disclosure agreements
 * explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended publication
 * or disclosure of this source code, which includes information that is confidential
 * and/or proprietary, and is a trade secret, of CRYPTO. ANY REPRODUCTION, MODIFICATION,
 * DISTRIBUTION, PUBLIC PERFORMANCE, OR PUBLIC DISPLAY OF OR THROUGH USE OF THIS
 * SOURCE CODE WITHOUT THE EXPRESS WRITTEN CONSENT OF CRYPTO IS STRICTLY PROHIBITED,
 * AND IN VIOLATION OF APPLICABLE LAWS AND INTERNATIONAL TREATIES. THE RECEIPT
 * OR POSSESSION OF THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR
 * IMPLY ANY RIGHTS TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO
 * MANUFACTURE, USE, OR SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 * #endregion
 */

package org.ta4j.core.indicators;

import org.ta4j.core.Indicator;
import org.ta4j.core.num.Num;

/**
 * Trend Strength Indicator.
 *
 * @author Faisal Feroz
 *
 */
public class TrendStrengthIndicator extends CachedIndicator<Num> {

    private static final long serialVersionUID = 5170986630605088042L;

    private final Indicator<Num> price;

    private final EMAIndicator ema30;

    private final EMAIndicator ema50;

    private final EMAIndicator ema90;

    private final TripleEMAIndicator tema90;

    /**
     * Constructor
     *
     * @param price the indicator to get the price
     */
    public TrendStrengthIndicator(final Indicator<Num> price) {
        super(price);
        this.price = price;
        ema30 = new EMAIndicator(price, 30);
        ema50 = new EMAIndicator(price, 50);
        ema90 = new EMAIndicator(price, 90);
        tema90 = new TripleEMAIndicator(price, 90);
    }

    @Override
    protected Num calculate(final int index) {
        if (index < 270) {
            // because of tema 90 we can't calculate value reliably till 270th index
            return numOf(TrendStrength.NONE.getValue());
        }

        final Num close = price.getValue(index);
        if (ema30.getValue(index).isGreaterThan(ema90.getValue(index))
                || ema50.getValue(index).isGreaterThan(ema90.getValue(index))) {
            if (close.isGreaterThan(tema90.getValue(index)) && close.isGreaterThan(ema90.getValue(index))
                    && (close.isGreaterThan(ema50.getValue(index)) || close.isGreaterThan(ema30.getValue(index)))) {
                return numOf(TrendStrength.STRONG_UPTREND.getValue());
            }
            if (close.isGreaterThan(ema90.getValue(index))
                    && (close.isGreaterThan(ema50.getValue(index)) || close.isGreaterThan(ema30.getValue(index)))) {
                return numOf(TrendStrength.UPTREND_SIDEWAYS.getValue());
            }
            if (close.isGreaterThan(ema90.getValue(index))) {
                return numOf(TrendStrength.WEAK_UPTREND.getValue());
            }
            if (close.isLessThan(ema90.getValue(index))) {
                return numOf(TrendStrength.WEAK_UPTREND_REVERSING.getValue());
            }
        } else if (close.isLessThan(tema90.getValue(index)) && close.isLessThan(ema90.getValue(index))
                && (close.isLessThan(ema30.getValue(index)) || close.isLessThan(ema50.getValue(index)))) {
            return numOf(TrendStrength.STRONG_DOWN_TREND.getValue());
        } else if (close.isLessThan(ema90.getValue(index))
                && (close.isLessThan(ema30.getValue(index)) || close.isLessThan(ema50.getValue(index)))) {
            return numOf(TrendStrength.DOWN_TREND.getValue());
        } else if (close.isLessThan(ema90.getValue(index))) {
            return numOf(TrendStrength.WEAK_DOWN_TREND.getValue());
        }

        return numOf(TrendStrength.DOWN_TREND_REVERSING.getValue());
    }
}
