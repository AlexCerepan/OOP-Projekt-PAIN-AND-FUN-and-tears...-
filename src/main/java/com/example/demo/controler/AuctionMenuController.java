package com.example.demo.controler;

import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.demo.Auction.Auction;
import com.example.demo.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class AuctionMenuController implements Notify {

    public static AuctionMenuController currC;
    Items selingItem;
    SetScene scene = new Scenes();

    @FXML
    Button show;

    @FXML
    Button start;

    @FXML
    Button returnB;

    @FXML
    Label show2;

    @FXML
    public ComboBox<String> comboBox;

    @FXML
    protected void onShowButtonClick() {
        System.out.println("mali by sa pridat itemy");
        comboBox.getItems().add("prasa");
    }

    @FXML
    protected void onReturnButtonClick() throws IOException {
        currC.returnB();


    }


    @FXML
    protected void onStartClick() throws IOException {
        startAuction();
    }

    @FXML
    protected void onBoxClick() {
        System.out.println("I choose " + comboBox.getValue());
    }


    public void startAuction() throws IOException {
        if (Objects.equals(comboBox.getValue(), null)) {
            show2.setVisible(true);
            show2.setText("You have to choose an auction first");
        } else {

            for(Items i : ItemDatabase.itemData){
                if(i.name.equals(comboBox.getValue())) {
                    selingItem = i;
                    break;
                }
            }
            FXMLLoader load = new FXMLLoader(Scenes.class.getResource("auction.fxml"));
            Stage start = new Auction(load, "Welcome!", selingItem);
            ControlerManagment.auctionDatabase.add((Auction) start);
            ControlerManagment.auctionControllerDatabase.add((((Auction) start).loader.getController()));
            ControlerManagment.auctionDatabase.get(ControlerManagment.auctionDatabase.size()-1).items = selingItem;
            ControlerManagment.auctionControllerDatabase.get(ControlerManagment.auctionControllerDatabase.size()-1).item = selingItem;
            ControlerManagment.auctionControllerDatabase.get(ControlerManagment.auctionControllerDatabase.size()-1).showWhatIsSelling(selingItem.name, selingItem.cost);
        }
        //   System.out.println("---------------------------------------");

    }


    public void returnB() throws IOException {
        if (LogInControler.currUser.myID == IDs.Bacis) {
            scene.stage.setScene(scene.getScene("workScene"));
        } else if (LogInControler.currUser.myID == IDs.VIPs) {
            scene.stage.setScene(scene.getScene("workSceneForVIP"));
        }
    }


    public void notifyPls() throws IOException {
        currC.comboBox.getItems().remove(selingItem.name);
        ItemDatabase.itemData.remove(selingItem);
    }
}
