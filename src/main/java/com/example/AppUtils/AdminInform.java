package com.example.AppUtils;

import com.example.demo.controler.ControlerManagment;

import static com.example.demo.controler.AuctionMenuController.currC;

/**
 *
 * pomocna class pre informovanie pouzivatela o zmene v predmetoch ktore sa mozu drazit
 * **/
public class AdminInform {

/**
 *
 * pridava aukciu do zoznamu
 *
 * @param nameOfAuc pridava do zoznamu meno aukcie na ktoru sa bude mozne prihlasit
 * **/
    public void addAuction(String nameOfAuc){
        currC = ControlerManagment.auctionMenuControllerDatabase.get(ControlerManagment.auctionMenuControllerDatabase.size()-1);
        currC.comboBox.getItems().add(nameOfAuc);

    }
    /***
     *
     * odobera aukciu zo zoznamu
     *
     * */

    public void removeAuction(String nameOfAuc){
        currC = ControlerManagment.auctionMenuControllerDatabase.get(ControlerManagment.auctionMenuControllerDatabase.size()-1);
        currC.comboBox.getItems().remove(nameOfAuc);
    }




}
