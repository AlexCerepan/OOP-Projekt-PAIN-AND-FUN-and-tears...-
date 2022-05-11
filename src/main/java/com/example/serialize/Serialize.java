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

    /**
     * tato funkcia sluzi na inicializovaníe zapisovacov do danych suborov
     * **/

   public void init_files(){
        try {
            fl = new FileWriter("C:\\Users\\cerko\\OneDrive\\Desktop\\2 rocnik\\LS\\DSA\\demo\\src\\main\\java\\com\\example\\serialize\\UsersSave.json");
            f2 = new FileWriter("C:\\Users\\cerko\\OneDrive\\Desktop\\2 rocnik\\LS\\DSA\\demo\\src\\main\\java\\com\\example\\serialize\\ItemsSave.json");
        }
        catch(IOException a){
            System.out.println("zla file cesta");
       }
    }

    /**
     * v tejto finkcii pouzivam reflexiu na zistovanie typu serializovaneho objektu
     * nasledne sa dany bjekt prida do arrayListu podla typu objektu
     *
     *
     * @param object genericka premmena pomocou ktorej serializujem
     *
     * **/
  public void  serialize_databaseInList(T object){
        if(object.getClass() == User.class)
            listUser.add(object);
        if(object.getClass() == Items.class)
          listTovar.add(object);
    }

    /**
     * tato metoda sluzi na zapisanie do suborov pre jednotlive objekty predtym este pretvorim uz dane stringy
     * jednotlivych objektov ktore maju byt zaserializovane do tvaru ktory dokaze vhodne gson zapisat a nasledne
     * sa zapisu
     *
     * **/
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
