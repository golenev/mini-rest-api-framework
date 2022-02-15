package ApiUtils;

public class StringUtils {
    public static StringBuilder createString (StringBuilder one, StringBuilder two){
        StringBuilder string = new StringBuilder(one);
        string.append(two);
        return string;
    }

    public static StringBuilder createString (StringBuilder one, StringBuilder two, StringBuilder three){
        StringBuilder string = new StringBuilder(one);
        string.append(two);
        string.append(three);
        return string;
    }
    public static StringBuilder createString (StringBuilder one, StringBuilder two, StringBuilder three, StringBuilder four){
        StringBuilder string = new StringBuilder(one);
        string.append(two);
        string.append(three);
        string.append(four);
        return string;
    }

}