package com.example.demo.controler;

import com.example.AppUtils.SetScene;
import com.example.demo.Scenes;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WorkSpaceController
{
    SetScene scene = new Scenes();
    @FXML
    Button show ;

    @FXML
    Button hide ;

    @FXML
    TextField writeNShow;

    @FXML
    Label showText;

    @FXML
    Button alert;

    @FXML
    Button returnB;

    @FXML
    protected void onShowButtonClick (){
        showText.setText(writeNShow.getText());
        showText.setVisible(true);
    }
    @FXML
    protected void onHideButtonClick (){
        showText.setVisible(false);
    }

    @FXML

    protected void onAlertButtonClick () throws IOException {
        scene.getScene("payment suc", "TF");
    }

    @FXML
    protected void onAlertButtonClickVIP () throws IOException {
        scene.getScene("alertBoxForVIP", "TF");
    }

    @FXML
    protected void onReturnButtonClick () throws IOException {
        LoginView.actualStage.setScene(IFrame.scene1);
    }

}
