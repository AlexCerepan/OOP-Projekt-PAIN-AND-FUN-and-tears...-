package com.example.AppUtils;

import com.example.demo.controler.AuctionMenuController;
import com.example.demo.controler.ControlerManagment;

import static com.example.demo.controler.AuctionMenuController.currC;


public class AdminInform implements Com {


    public void addAuction(String nameOfAuc){
        currC = ControlerManagment.auctionMenuControllerDatabase.get(ControlerManagment.auctionMenuControllerDatabase.size()-1);
        System.out.println(currC);
        currC.comboBox.getItems().add(nameOfAuc);

    }

    public void removeAuction(String nameOfAuc){
        currC.comboBox.getItems().remove(nameOfAuc);
    }


    @Override
    public void update(AuctionMenuController auc) {

    }


}
