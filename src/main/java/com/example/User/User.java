package com.example.User;


import com.example.AppUtils.IDs;
import com.example.Items.Items;
import com.example.wallet.BasicWallet;
import com.example.wallet.Wallet;

import java.util.ArrayList;
/**
 *
 * class user mi predstavuje ludi ktory su na aukcii
 * kazdy user má svoje meno, heslo a mail ako aj penazenku a list itemov ktore vlastni
 * ma aj flagy ktore oznacuju vlastnosti alebo ako sa k nemu program má spravat
 * **/
public class User  {

    public String Email;
    public String name;
    public String password;
    public boolean online = false;
    public boolean VIP = false;
    public boolean admin = false;
    public boolean ban = false;
    public IDs myID;
    public transient Wallet wallet = new BasicWallet();
    public float onWallet;
    public ArrayList<Items> myItems = new ArrayList<>();

    public User(String name, String password, String Email, IDs id){
        this.name = name;
        this.Email = Email;
        this.password = password;
        this.onWallet = this.wallet.value;
        this.myID = id;
    }

}

