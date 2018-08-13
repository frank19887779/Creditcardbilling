package com.example.user.creditcardbilling;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Pet  {

    private int id;
    private String date;
    private String amount;
    private String rserved;
    private String description;






    public Pet(String date, String amount, String rserved, String description) {
        this(0 , date, amount, rserved, description);

    }

    public Pet(int id, String date, String amount, String rserved, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.rserved = rserved;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setRserved(String rserved) {
        this.rserved = rserved;
    }

    public String getRserved() {
        return rserved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
