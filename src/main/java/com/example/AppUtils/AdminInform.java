package com.example.AppUtils;

import com.example.demo.controler.AuctionMenuController;


public class AdminInform implements Com {


    public void addAuction(String nameOfAuc){
        AuctionMenuController.currC.comboBox.getItems().add(nameOfAuc);

    }

    public void removeAuction(String nameOfAuc){
        AuctionMenuController.currC.comboBox.getItems().remove(nameOfAuc);
    }


    @Override
    public void update(AuctionMenuController auc) {

    }


}
