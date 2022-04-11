package com.example.demo.controler;

import com.example.User.User;
import com.example.demo.Auction.Auction;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.ArrayList;

public interface Notify {
    ArrayList<AuctionMenuController> menuControllers = new ArrayList<>();
    ArrayList<Auction> auctions = new ArrayList<>();
    ArrayList<AuctionController> controlers = new ArrayList<>();
    void notifyPls() throws IOException;
}
