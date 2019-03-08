package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ikramshire on 2019-03-04.
 */

public class User implements Serializable {
    public String email;
    public  String prefList;

    public String getPrefList() {
        return prefList;
    }

    public void setPrefList(String prefList) {
        this.prefList = prefList;
    }


    public User(){}



    public User(String email, String prefList ){
        this.email = email;
        this.prefList = prefList;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
