package com.example.serialize;

import com.example.demo.thread.MyThread;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Deserialize {

    public Deserialize(){
        MyThread mt = new MyThread(10000);
        new Thread(mt::create_Items).start();
    }

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
