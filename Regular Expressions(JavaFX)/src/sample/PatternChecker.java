package sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternChecker {
    public static final String NATURAL_NUMBER_PATTERN = "([1-9])*";
    public static final String INTEGER_NUMBER_PATTERN = "-?[1-9]*";
    public static final String FLOAT_NUMBER_PATTERN = "\\-?[1-9]*(\\.\\d+)?([eE](\\-|\\+)\\d+)?";
    public static final String DATA_PATTERN = "(((0[1-9]|[12][0-9])\\.(0[1-9]|1[0-2]))|(3[01]\\.(0[13578]|1[02]))|(30\\.(0[469]|11)))\\.([0-9][0-9][0-9][0-9])";
    public static final String TIME_PATTERN = "([0-1]\\d|2[0-3])(:[0-5]\\d){2}";
    public static final String EMAIL_PATTERN = "(\\w+)(\\.\\w+)*@(\\w+)(\\.\\w+)";

    public boolean isPattern(String string,String patternToFollow) {
        Pattern pattern = Pattern.compile(patternToFollow);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public ArrayList findAllPatterns(String text, String patternToFollow) {
        ArrayList list = new ArrayList();
        Pattern pattern = Pattern.compile(patternToFollow);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

}
