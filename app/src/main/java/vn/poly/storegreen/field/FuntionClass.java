package vn.poly.storegreen.field;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FuntionClass {

    public static String getNows() {
        String now;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        now = simpleDateFormat.format(new Date());
        return now;
    }

    public static String checkHSD() {
        String date;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;//cộng thêm vào 1 ngày
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        calendar.set(year, month, day);
        date = simpleDateFormat.format(calendar.getTime());
        return date;
    }


}
