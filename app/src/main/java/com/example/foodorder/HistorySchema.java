package com.example.foodorder;

public class HistorySchema {
    public static class HistoryTable{
        public  static final String NAME = "history" ;
        public  static class Cols{
            public  static final String USERID = "user_id";
            public  static final String ORDERID = "order_id";
            public  static final String TIME = "time";
            public  static final String NAME = "food_id" ;
            public  static final String PRICE = "price" ;
            public  static final String FOODIMAGE = "food_image" ;
            public  static final String AMOUNT = "amount" ;
        }

    }
}
