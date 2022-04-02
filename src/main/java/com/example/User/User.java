package com.example.User;


import com.example.AppUtils.IDs;

import java.util.ArrayList;

public class User  {

    public String Email;
    public String name;
    public String password;
    public boolean online = false;
    public boolean VIP = false;
    public boolean admin = false;
    public IDs myID;

    public User(String name, String password, String Email, IDs id){
        this.name = name;
        this.Email = Email;
        this.password = password;
        this.myID = id;
    }

    public void setOnline(){};
    public void setVIP(){};
    public void setAdmin(){};


}

