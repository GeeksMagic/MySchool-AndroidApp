package com.gmt.myschool.database;

/**
 * Created by user on 6/10/2016.
 */
public class PaymentFee {

    private String date;

    private String amount;

    private String recept;

    public PaymentFee(String date, String amount, String recept) {
        this.date = date;
        this.amount = amount;
        this.recept = recept;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRecept() {
        return recept;
    }

    public void setRecept(String recept) {
        this.recept = recept;
    }
}
