package solutions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    public static String convert12HoursTo24Hours(String s) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("hh:mm:ssaa");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = inputFormatter.parse(s);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
