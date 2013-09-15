package edu.osu.cse.misc.graph.plotting.util;

import java.util.regex.Pattern;

public class Regex {

    private static final String FLOATING_POINT_PATTERN_NO_SIGN = "([\\d.\\.\\d]+)";
    private static final String FLOATING_POINT_PATTERN_SIGN = "[+-]?" + FLOATING_POINT_PATTERN_NO_SIGN;

    public static Pattern floatingPointWithSign = Pattern.compile(FLOATING_POINT_PATTERN_SIGN);
    public static Pattern floatingPointWithoutSign = Pattern.compile(FLOATING_POINT_PATTERN_NO_SIGN);

    /**
     * A Pattern that matches a floating point value. The sign of the number is matched dependant on the provided boolean.
     * 
     * @param term The scaler term to match. i.e. Matching "3x" will match the 3. 
     * @param withSign Whether to include the sign. If <tt>true</tt>, "-3x" would match "-3". This parses nicely with {@link Double#parseDouble(String)}
     * @return A Pattern matching the aforementioned cases.
     */
    public static Pattern patternForScaledTerm(char term, boolean withSign) {
	return Pattern.compile((withSign ? FLOATING_POINT_PATTERN_SIGN : FLOATING_POINT_PATTERN_NO_SIGN) + "(?=" + term + ")");
    }
    
    /**
     * Defaults to withSign = true
     * @see {{@link #patternForScaledTerm(char, boolean)}
     */
    public static Pattern patternForTerm(char term) {
	return patternForScaledTerm(term, true);
    }

}
