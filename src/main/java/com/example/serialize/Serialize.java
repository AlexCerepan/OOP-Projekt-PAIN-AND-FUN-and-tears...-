package com.example.serialize;

import com.example.Items.Items;
import com.example.User.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Serialize <T>{
    ArrayList<T> listTovar = new ArrayList<>();
    ArrayList<T> listUser = new ArrayList<>();
   public Gson g = new Gson();
    public FileWriter fl ;
    public FileWriter f2 ;
    public Serialize () {
        init_files();
    }

   public void init_files(){
        try {
            fl = new FileWriter("C:\\Users\\cerko\\OneDrive\\Desktop\\2 rocnik\\LS\\DSA\\demo\\src\\main\\java\\com\\example\\serialize\\UsersSave.json");
            f2 = new FileWriter("C:\\Users\\cerko\\OneDrive\\Desktop\\2 rocnik\\LS\\DSA\\demo\\src\\main\\java\\com\\example\\serialize\\ItemsSave.json");
        }
        catch(IOException a){
            System.out.println("zla file cesta");
       }
    }
  public void  serialize_databaseInList(T object){
        if(object.getClass() == User.class)
            listUser.add(object);
        if(object.getClass() == Items.class)
          listTovar.add(object);
    }

    public void serializeUsers() {
        ArrayList<String> listUsers = new ArrayList<>();
        ArrayList<String> listTovars = new ArrayList<>();
        for(T user : listUser){
            listUsers.add(g.toJson(user));
        }
        for(T items: listTovar){
            listTovars.add(g.toJson(items));
        }
        try {
            f2.write(listTovars.toString());
            f2.close();
            fl.write(listUsers.toString());
            fl.close();
        }catch(IOException e) {
            System.out.println("WTF nezapisalo");
        }


    }

}
