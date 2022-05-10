package com.example.demo.myExeption;

import com.example.demo.Scenes;

import java.io.IOException;

public class MyException extends Exception{

    Scenes scene = new Scenes();

    public void throwThat() throws IOException {
        scene.getScene("exception", "NO GOOD");

    }
}
