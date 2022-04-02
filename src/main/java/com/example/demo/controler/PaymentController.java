package com.example.demo.controler;

import com.example.AppUtils.SetScene;
import com.example.User.User;
import com.example.demo.Scenes;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PaymentController  {

    Notify notify = new LogInControler();
    SetScene scene = new Scenes();

    @FXML
    TextField cardID;

    @FXML
    TextField cardDate;

    @FXML
    TextField cardCode;

    @FXML
    Button returnButton;

    @FXML
    Button payButton;

    @FXML
    protected void onPayButtonClick() throws IOException {

     if(controlSymbols(cardCode.getText()) && controlSymbols(cardID.getText())) {
         if ((cardID.getText().length() < 5)) {

             LogInControler.paying = false;
             scene.getScene("payment fail", "TF DID U DO");

         } else if ((cardCode.getText().length() > 3) && (cardCode.getText().length() <= 4)) {
             LogInControler.paying = false;
             scene.getScene("payment fail", "YOU DIDnt IT");

         } else {
             LogInControler.paying = true;
             scene.getScene("payment suc", "YOU DID IT");
             notify.notifyPls();
             System.out.println("Notifypls");

         }
     }
     else scene.getScene("payment fail", "EPIC FAIL");
    }

    @FXML
    protected void onReturnButtonClick(){

            LoginView.actualStage.setScene(IFrame.scene1);


    }

    boolean controlSymbols(String arr){
        for(int i = 0 ; i < arr.length(); i++){
            char a = arr.charAt(i);
            if((a < '0') || (a > '9'))
                return false;
        }
        return true;
    }



}
