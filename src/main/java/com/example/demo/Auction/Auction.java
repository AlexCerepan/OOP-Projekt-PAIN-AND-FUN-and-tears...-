package com.example.demo.Auction;

import com.example.Items.Items;
import com.example.demo.controler.AuctionController;
import com.example.demo.controler.Notify;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Auction extends Stage implements Notify {
    public Items items;
    public FXMLLoader loader;
    String title;
    Scene scene;
    Stage thisMe;
    AuctionController auc;


    public Auction(){}
    public Auction(FXMLLoader loadr, String titl) throws IOException {
        this.loader = loadr;
        this.scene = new Scene(loader.load(), 400, 400);
        this.title = titl;
       auc = new AuctionController();
       this.thisMe = this;

        init();
    }

    void init(){
        auctions.add(this);
        controlers.add(auc);
        this.setTitle(title);
        this.setScene(scene);
        this.show();
    }


    @Override
    public void notifyPls() {

    }
}
