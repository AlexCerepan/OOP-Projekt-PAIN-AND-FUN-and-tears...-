package com.example.demo.Auction;

import com.example.Items.Items;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/**
 * class ktora zobrazuje aukcie a ich chod
 * */
public class Auction extends Stage {
    public Items items;
    public FXMLLoader loader;
    String title;
    Scene scene;
    Stage thisMe;

    public Auction(){}
    public Auction(FXMLLoader loadr, String titl, Items item) throws IOException {
        this.items = item;
        this.loader = loadr;
        this.scene = new Scene(loader.load(), 400, 400);
        this.title = titl;
       this.thisMe = this;

        init();
    }

    void init(){
        this.setTitle(title);
        this.setScene(scene);
        this.show();
    }

    /**
     * funkcia ktora botom pre danu aukciu zhodnocuje poslednu prihodenú ciastku a podla nej sa rozhoduju
     * kolko prihodia oni
     *
     * @param lastBidValue hodnota poslenej prihodenej ciastky
     *
     * @return vracia ciastku ktoru prihadzuje bot
     *
     * */
    public int botsBid(int lastBidValue){
        int newVal = 0;
        Random random = new Random();

        if(lastBidValue <= 1000){
            if(random.nextInt(100)<70){
                System.out.println("a < 1000 " + newVal);
                newVal = (lastBidValue + random.nextInt(500));
                return newVal;
            }
        }
         else if(lastBidValue > 1000 && lastBidValue <= 10000){
            if(random.nextInt(100)<40){
                System.out.println("1000 < a < 10000 " + newVal);
                newVal = (lastBidValue + random.nextInt(1000));
                return newVal;
            }
        }
         else if (lastBidValue > 10000 && lastBidValue <= 100000){
            if(random.nextInt(100)< 20){
                newVal = (lastBidValue + random.nextInt(1000));
                System.out.println("10000 < a < 100000 " + newVal);
                return newVal;
            }
        }

        else{
            if(random.nextInt(100)<5){
                newVal = (lastBidValue + random.nextInt(100));
                System.out.println("a > 100000 " + newVal);
                return newVal;
            }
        }
        return -1;
    }

}
