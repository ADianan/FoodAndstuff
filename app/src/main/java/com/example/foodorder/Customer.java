package com.example.foodorder;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String email;
    private String password;
    private String Userid;
    private List<History> history;


    public Customer(String email, String password, String userid) {
        this.email = email;
        this.password = password;
        Userid = userid;

        history = new ArrayList();

    }

    public String getUserid() {
        return Userid;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
}
