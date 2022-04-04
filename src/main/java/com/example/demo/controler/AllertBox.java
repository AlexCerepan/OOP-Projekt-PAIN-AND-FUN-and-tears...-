package com.example.demo.controler;

import com.example.AppUtils.SetScene;
import com.example.demo.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AllertBox  {
    SetScene usage = new Scenes();
    @FXML
    Label message = new Label("message");

    @FXML
    Label failed = new Label("failed");

    @FXML
    Label banned;

    @FXML
    Button close;

    @FXML
    protected void onCloseButton(){
        usage.forAlert.close();
    }

}
