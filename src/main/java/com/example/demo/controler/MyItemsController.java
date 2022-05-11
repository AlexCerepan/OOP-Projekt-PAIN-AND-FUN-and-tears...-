package com.example.demo.controler;
import com.example.Items.Items;
import com.example.demo.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static com.example.demo.controler.LogInControler.currUser;
public class MyItemsController {

    Scenes usage = new Scenes();

    @FXML
    TextArea area;

    @FXML
    Button show;

    @FXML
    Button close;
/**
 * pri stlaceni buttonu show sa ukaze textarea ktora obsahuje vypis predmetov ktore vlastni dany
 * pouzivatel a ich cenu
 * */
    @FXML
    protected void onShowButtonClick(){
        showItems();
    }
 /***
  * pri stlaceni return sa zavrie dane okno
  * */
    @FXML
    protected void onCloseButtonClick(){
        usage.forAlert.close();
    }

    public void showItems() {
        for(Items a : currUser.myItems){
            area.appendText("Name: " + a.name + " value: " + a.cost + "\n");
        }
        area.setVisible(true);
    }

}
