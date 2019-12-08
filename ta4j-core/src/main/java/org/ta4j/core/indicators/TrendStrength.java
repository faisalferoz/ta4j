
package org.ta4j.core.indicators;

import java.util.stream.Stream;

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

    /**
     * Returns the TrendStrength constant which matches the given <code>value</code>
     *
     * @param value - value to find
     * @return the TrendStrength constant which matches the given <code>value</code>
     */
    public static TrendStrength fromValue(final Number value) {
        return Stream.of(values()) //
                .filter(e -> e.value.equals(value.intValue())) //
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException(String.format("No TrendStrength with value [%s]", value)));
    }

}
