package com.example.serialize;

import com.example.demo.thread.MyThread;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Deserialize {

    public Deserialize(){
        MyThread mt = new MyThread();
        new Thread(mt::create_Items).start();
    }

    /**
     *
     * dana funkcia mi sluzi na vybratie dat zo suboru aby som ich mohol deserializovat
     *
     * @param fileName tento vstup sluzi na identifikovanie z akeho suboru chcem vyberat data
     *
     * @return vraciam string dat ktore chcem nasledne deserializovat alebo ak sa mi to nepodari tak null
     *
     * **/
   public String readFileAsString(String fileName)
    {
        try {

            //z web-u geeksforgeeks
            String data = "";
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        }catch(Exception e){
            System.out.println("nejde deserialize");
            return null;
        }
    }
}
