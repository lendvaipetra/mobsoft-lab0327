package hu.bme.aut.mobsoft.lab.mobsoft.model;

import com.google.gson.annotations.SerializedName;

public class UserId {
    @SerializedName("value")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
