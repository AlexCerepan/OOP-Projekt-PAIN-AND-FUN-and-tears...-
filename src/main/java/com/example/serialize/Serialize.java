package com.example.serialize;

import com.example.AppUtils.IDs;
import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.User.User;
import com.example.User.UserDatabase;
import com.example.wallet.BasicWallet;
import com.example.wallet.VIPWallet;
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

        System.out.println(listUsers);
        System.out.println(listTovars);
        System.out.println();
      /*  for(T item : listTovar){
            listTovars.add(g.toJson(item));
        }*/
    }

    public static void main(String[] args) {
        //UserDatabase.users.add(new User("jozo", "papa", "kokoooooot", IDs.Bacis));
       // UserDatabase.users.get(0).onWallet = 500;

        ItemDatabase.itemData.add(new Items("papuca", 555));
        ItemDatabase.itemData.add(new Items("papagaj", 5555));
        ItemDatabase.itemData.add(new Items("pamapa", 55555));
       // UserDatabase.users.get(0).myItems.add(new Items("kravata", 500));
        //UserDatabase.users.get(0).myItems.add(new Items("krava", 700));
        Deserialize d = new Deserialize();
        String k;
        k = d.readFileAsString("C:\\Users\\cerko\\OneDrive\\Desktop\\2 rocnik\\LS\\DSA\\demo\\src\\main\\java\\com\\example\\serialize\\UsersSave.json");
        System.out.println(k.length());
        String l;
        Serialize s = new Serialize();
        for(User u : UserDatabase.users)
                s.serialize_databaseInList(u);
        for(Items i : ItemDatabase.itemData)
                s.serialize_databaseInList(i);

        s.serializeUsers();
        l = d.readFileAsString("C:\\Users\\cerko\\OneDrive\\Desktop\\2 rocnik\\LS\\DSA\\demo\\src\\main\\java\\com\\example\\serialize\\ItemsSave.json");
        User[] arr = s.g.fromJson(k,User[].class);
        Items[] arr2 = s.g.fromJson(l,Items[].class);
        for(User a : arr){
            if(a.VIP) {
                a.wallet = new VIPWallet();
                a.wallet.addToWallet(a.onWallet);
            }

            else{
                a.wallet = new BasicWallet();
                a.wallet.addToWallet(a.onWallet);
            }
        }
        System.out.println(arr[0].wallet.value);
        System.out.println(arr2[0].cost);

    }
}
