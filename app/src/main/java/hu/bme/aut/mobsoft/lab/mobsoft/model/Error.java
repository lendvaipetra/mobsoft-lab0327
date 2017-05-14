package hu.bme.aut.mobsoft.lab.mobsoft.model;

import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("fields")
    private String fields;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getFields() {
        return fields;
    }
    public void setFields(String fields) {
        this.fields = fields;
    }
}
