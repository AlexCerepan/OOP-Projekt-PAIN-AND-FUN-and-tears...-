package com.example.demo.myExeption;

import com.example.demo.Scenes;

import java.io.IOException;

/**
 * vytvoril som exception ktora upozorni usera ze sa neprihlasil na ziadnu aukciu ale chcel pokracovat do okna
 * v ktorom sa aukcia vykonava
 * */
public class MyException extends Exception{

    Scenes scene = new Scenes();


    /**
     * metoda vytvara okno ktore informuje pouzivatela ze si musi vybrat typ tovaru ktory chce kupovat predtym nez
     * môze pokracovať
     *
     * @throws IOException ak sa nedostane k fxml suboru nahlasi sa chyba
     * **/
    public void throwThat() throws IOException {
        scene.getScene("exception", "NO GOOD");

    }
}
