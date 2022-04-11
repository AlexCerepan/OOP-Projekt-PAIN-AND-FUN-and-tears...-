package com.example.demo.controler;

import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
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

import static com.example.demo.controler.AdminController.activeAuc;

public class AuctionMenuController implements Notify{

public static AuctionMenuController currC;

 SetScene scene = new Scenes();
 AucItem getItem = new AuctionController();

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
    protected void onShowButtonClick(){
        System.out.println("mali by sa pridat itemy");
        comboBox.getItems().add("prasa");
    }

    @FXML
    protected void onReturnButtonClick() throws IOException {
        currC.returnB();
        Notify.menuControllers.remove(currC);
    }


    @FXML
    protected void onStartClick() throws IOException {
        currC.startAuction();
    }

    @FXML
    protected void onBoxClick(){
        System.out.println("I choose " + comboBox.getValue());
    }




    public void startAuction() throws IOException {
        if(Objects.equals(comboBox.getValue(), null)) {
            show2.setVisible(true);
            show2.setText("You have to choose an auction first");
        }
        else {
            FXMLLoader load = new FXMLLoader(Scenes.class.getResource("auction.fxml"));
            Stage start = new Auction(load, "Welcome!");
            activeAuc.add(start);
            for (AuctionController a : Notify.controlers) {
                //   System.out.println("Kontrolery :" + a);
                System.out.println(getItem);
                AuctionController.currC = a;
                getItem.notifypls(comboBox.getValue(), a);
                //  System.out.println("mal by som notifinut kontrolery: " + a);
            }
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
        System.out.println(currC);
        menuControllers.add(this);
    }
}
