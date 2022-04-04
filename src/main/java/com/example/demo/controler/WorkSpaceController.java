package com.example.demo.controler;

import com.example.AppUtils.SetScene;
import com.example.User.UserDatabase;
import com.example.demo.Scenes;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.example.demo.controler.LogInControler.currUser;

public class WorkSpaceController
{
    SetScene scene = new Scenes();
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
        scene.stage.setScene(scene.getScene("addMoney"));
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
        UserDatabase.currActiveUsers.remove(currUser);
        currUser.online = false;
        LoginView.actualStage.setScene(IFrame.scene1);
    }

}
