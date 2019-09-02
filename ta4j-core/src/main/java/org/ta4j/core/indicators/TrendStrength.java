
package org.ta4j.core.indicators;

/**
 * Represents different Trend Strength Levels
 *
 * @author Faisal Feroz
 *
 */
public enum TrendStrength {

    NONE(0), //
    STRONG_UPTREND(100), //
    UPTREND_SIDEWAYS(70), //
    WEAK_UPTREND(30), //
    WEAK_UPTREND_REVERSING(-30), //
    STRONG_DOWN_TREND(-100), //
    DOWN_TREND(-70), //
    WEAK_DOWN_TREND(-30), //
    DOWN_TREND_REVERSING(30);

    private final Integer value;

    private TrendStrength(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
