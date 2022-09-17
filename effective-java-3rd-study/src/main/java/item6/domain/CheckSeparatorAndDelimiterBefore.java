package item6.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSeparatorAndDelimiterBefore {

    public static boolean checkSeparatorAndDelimiter(String str) {

        final Pattern PATTERN_DELIMITERS = Pattern.compile("[,:]");
        final Matcher matcher = PATTERN_DELIMITERS.matcher(str);
        return matcher.find();
    }
}