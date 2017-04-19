package hu.bme.aut.mobsoft.lab.mobsoft.model;

import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.List;

@Table
public class User {
    private int id = -1;
    private String name;
    private String password;

    public User(){}

    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
