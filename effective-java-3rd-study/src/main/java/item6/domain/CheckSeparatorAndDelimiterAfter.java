package item6.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSeparatorAndDelimiterAfter {

    private static final Pattern PATTERN_DELIMITERS = Pattern.compile("[,:]");

    public static boolean checkSeparatorAndDelimiter(String str) {

        final Matcher matcher = PATTERN_DELIMITERS.matcher(str);
        return matcher.find();
    }
}