package com.example.foodorder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

public  class RandomIDGen {

    public static String UserId()
    {
        Random rand = new Random();
        return  String.valueOf(rand.nextInt(50000-20000) +20000);
    }
    public  static String orderID()
    {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(20000-10000) +10000);
    }
    public static String getTime()
    {
        LocalDate date = LocalDate.now();
        String ye = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
        return ye;
    }

}
