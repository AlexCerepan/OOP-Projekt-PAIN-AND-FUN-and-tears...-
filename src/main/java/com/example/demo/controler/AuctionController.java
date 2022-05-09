package com.example.demo.controler;

import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.demo.Auction.Auction;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.example.demo.controler.LogInControler.currUser;


public class AuctionController implements AucItem {

    public static AuctionController currC;
    public Items item;
    boolean endAuction;


    @FXML
    Button show;

     @FXML
    TextField value;

    @FXML
    Button returnB;

    @FXML
    Label noMoney;

    @FXML
    Label theItem;


    @FXML
    protected void onReturnButtonClick() throws IOException {
        returnB();
    }

    @FXML
    protected void onBidButtonClick() throws IOException {
        addMoney();
    }

    public void showWhatIsSelling(String name, float value){
        this.theItem.setText(name + "\n" + "Starting price: " + value);
    }


   public void returnB() throws IOException {
       for(Auction auction : ControlerManagment.auctionDatabase){
           if(auction.loader.getController() == this ){
               ControlerManagment.auctionControllerDatabase.remove(this);
               auction.close();
           }

       }
   }

    void addMoney() throws IOException {
        // prerobene
        Auction currA = null;
        for (Auction a : ControlerManagment.auctionDatabase) {
            if (a.loader.getController() == this) {
                currA = a;
            }
        }
        if(Objects.equals(value.getText(), "")) {
            noMoney.setText("You didnt bid anything");
            return;
        }
        if(Integer.parseInt(value.getText()) > currUser.wallet.value){
           noMoney.setText("You dont have this much money on your account");
        }

        else {
            noMoney.setText("You bid: " + Integer.parseInt(value.getText()));
            assert currA != null;
            botsTurn(currA);
            if(endAuction){
                noMoney.setText("You bought " + item.name);
                show.setVisible(false);
                currUser.myItems.add(item);
                AuctionMenuController.currC.notifyPls();
            }

        }


             /*   if (this.item.cost < Integer.parseInt(value.getText())) {

                    currUser.myItems.add(this.item);
                    AuctionMenuController.currC.notifyPls();
                   noMoney.setText("you bought: " + this.item.name);

                }*/
            }




    @Override
    // prerobit
    public void notifypls(String name, AuctionController cur) {
        //System.out.println("aktualny controller " + this);
            for (Items i : ItemDatabase.itemData) {
                if (Objects.equals(name, i.name)) {
                //    System.out.println("Toto chcem ako controller " + currC);
                    item = i;
                    System.out.println(currC);
                    System.out.println("Name: " + currC.item.name + " cost: " + currC.item.cost);
                }
        }
    }


public void botsTurn(Auction auc){
    Random random = new Random();
    ArrayList<String> botsNames =new ArrayList<>();
    botsNames.add("Matus");
    botsNames.add("Peter");
    botsNames.add("Danka");
    botsNames.add("Robo");
    botsNames.add("Tatiana");
    for(int i = 0; i < 10; i++) {
        int theBid = auc.botsBid(Integer.parseInt(value.getText()));
        if (theBid > Integer.parseInt(value.getText())) {
            noMoney.setText(botsNames.get(random.nextInt(4)) + " bid: " + theBid);
            break;
        }
        if(i == 9 )
            endAuction = true;
    }
}


}
