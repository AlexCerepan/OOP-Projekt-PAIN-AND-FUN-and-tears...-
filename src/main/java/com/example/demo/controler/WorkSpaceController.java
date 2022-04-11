package com.example.demo.controler;

import com.example.AppUtils.AdminInform;
import com.example.AppUtils.Com;
import com.example.AppUtils.SetScene;
import com.example.User.UserDatabase;
import com.example.demo.Scenes;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.example.demo.controler.AuctionMenuController.currC;
import static com.example.demo.controler.LogInControler.currUser;

public class WorkSpaceController
{

    Notify notify = new AuctionMenuController();
    SetScene scene = new Scenes();
    @FXML
    Button goToAuction;

    @FXML
    Button show ;

    @FXML
    Button hide ;

    @FXML
    Button returnB;

    @FXML
    Label urMoney;

    @FXML
    Button addMoney;


    @FXML
    protected void onaddMoneyClick() throws IOException {
        pay();
    }


    @FXML
    protected void onShowButtonClick (){
        urMoney.setVisible(true);
        urMoney.setText("Wallet blance: " + currUser.wallet.value);
    }
    @FXML
    protected void onHideButtonClick (){
        urMoney.setVisible(false);
    }


    @FXML
    protected void onReturnButtonClick () {
        returnBc();
    }

    @FXML
    protected void goToAuctionClick() throws IOException {
        startAuctions();
    }

    void pay() throws IOException {
        scene.stage.setScene(scene.getScene("addMoney"));
    }

    void returnBc(){
        UserDatabase.currActiveUsers.remove(currUser);
        currUser.online = false;
        LoginView.actualStage.setScene(IFrame.scene1);
    }

    void startAuctions() throws IOException {
        Scenes.stage.setScene(scene.getScene("auctionChoose"));
        currC = Scenes.ldr.getController();
        System.out.println(currC);
        notify.notifyPls();

    }

}
