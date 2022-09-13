package com.example.foodorder;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String email;
    private String password;
    private String Userid;
    private List<List<History>> history;


    public Customer(String email, String password, String userid) {
        this.email = email;
        this.password = password;
        Userid = userid;

        history = new ArrayList<List<History>>();

    }
}
