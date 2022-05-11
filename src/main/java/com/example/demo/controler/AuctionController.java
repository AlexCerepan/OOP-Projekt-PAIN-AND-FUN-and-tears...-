package com.example.demo.controler;

import com.example.AppUtils.SetScene;
import com.example.Items.Items;
import com.example.demo.Auction.Auction;
import com.example.demo.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.*;

import static com.example.demo.controler.LogInControler.currUser;


public class AuctionController {

    public Items item;
    boolean endAuction;
    public SetScene scene = new Scenes();

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



    /**
     * po stlaceni buttonu sa mi dane okno zavrie
     * */
    @FXML
    protected void onReturnButtonClick() throws IOException {
        returnB();
    }

    /**
     * dana funkcia mi zohladnuje prihadzovanie na tovar
     * */
    @FXML
    protected void onBidButtonClick(){
        addMoney();
    }

    /**
     * metoda ktora okazuje vo view aky predmet sa predava a aka je jeho cena
     * */
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

    void addMoney(){
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
                currUser.wallet.value -= Integer.parseInt(value.getText());
                AuctionMenuController.currC.notifyPls();
            }

        }

            }


/**
 *
 * tato funkcia ovlada a vatvara botov ktory prihadzuju proti pouzivatelovi
 *
 * @param auc akcia ktora sa prave vykonava
 *
 * */
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
