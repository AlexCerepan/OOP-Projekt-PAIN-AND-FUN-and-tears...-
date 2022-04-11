package com.example.demo.controler;

import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.demo.Auction.Auction;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.controler.LogInControler.currUser;


public class AuctionController implements AucItem, Notify {


   public static AuctionController currC;

    Items item;
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
    protected void onBidButtonClick(){
        addMoney();
    }


   public void returnB() throws IOException {
       for(Auction auction : Auction.auctions){
           if(auction.loader.getController() == this ){
               auction.close();
           }

       }
       Notify.controlers.clear();
       for(AuctionController a : controlers){
           System.out.println(a);
       }
   }

    void addMoney(){
        // prerobene
       // System.out.println("tento je vraj aktualny " + currC);
        if(Integer.parseInt(value.getText()) > currUser.wallet.value)
            noMoney.setText("You dont have this much money on your account");
              //  System.out.println("Name of Item " + currC.item.name);


                if (currC.item.cost < Integer.parseInt(value.getText())) {

                    currUser.myItems.add(currC.item);
                   noMoney.setText("you bought: " + currC.item.name);
                   //System.out.println("User items: " + currUser.myItems.get(0).name + " cost " + currUser.myItems.get(0).cost + " User name: " + currUser.name);

                }
            }




    @Override
    // prerobit
    public void notifypls(String name, AuctionController cur) {
        //System.out.println("aktualny controller " + this);
            for (Items i : ItemDatabase.itemData) {
                if (Objects.equals(name, i.name)) {
                //    System.out.println("Toto chcem ako controller " + currC);
                    currC.item = i;
                    System.out.println();
                    System.out.println("Name: " + currC.item.name + " cost: " + currC.item.cost);
                }
        }
    }


    @Override
    public void notifyPls() {
        currC.theItem.setText(currC.item.name);
        System.out.println(currC.item.name);
    }

}
