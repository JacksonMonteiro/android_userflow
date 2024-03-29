package space.jacksonmonteiro.users.utils;
/*
Created By Jackson Monteiro on 12/01/2024
*/


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static long convertDateToTimestamp(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = sdf.parse(dateString);
            if (date != null) {
                return date.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isOver18(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        Date birthDate = new Date(timestamp);
        long differenceInMillis = calendar.getTimeInMillis() - birthDate.getTime();
        long ageInYears = TimeUnit.MILLISECONDS.toDays(differenceInMillis) / 365;
        return ageInYears >= 18;
    }

    public static String convertTimestampToDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }
}
