package com.example.demo.controler;

import com.example.AppUtils.SetScene;
import com.example.Items.ItemDatabase;
import com.example.Items.Items;
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

    SetScene scene = new Scenes();

    @FXML
    Button myItems;

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

/**
 * tato funkcia sa zavola po stlaceni tlacidla addMoney
 *
 * @throws IOException ak sa nedostanem k fxml suboru
 * **/
    @FXML
    protected void onaddMoneyClick() throws IOException {
        pay();
    }

    /**
     * po stlaceni tlacidla my items otvori okno s predmetmi ktore dany pouzivatel vlastni
     *
     * @throws IOException ak sa nedostanem k fxml suboru
     * */
    @FXML
    protected void onMyItemsClick() throws IOException {
        myItemsShow();
    }


    /**
     * tato metoda zobrazi vo view hodnotu ktoru ma dany pouzivatel prave na ucte
     * */
    @FXML
    protected void onShowButtonClick (){
        urMoney.setVisible(true);
        urMoney.setText("Wallet blance: " + currUser.wallet.value);
    }

    /**
     * v tejto metode mozem stav mojich financii znova skryt
     * */
    @FXML
    protected void onHideButtonClick (){
        urMoney.setVisible(false);
    }

    /**
     * funkcia ktora vracia pouzivatela naspat na login
     * */
    @FXML
    protected void onReturnButtonClick () {
        returnBc();
    }

    /**
     * pri stlaceni tlacidla start sa vykona dana funkcia ktora nas dostane na scenu kde si mozeme vyberat
     * co chceme ist kupovat
     *
     * @throws IOException ak sa nenajde fxml subor
     * */
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
        ControlerManagment.auctionMenuControllerDatabase.add(Scenes.ldr.getController());
        currC = Scenes.ldr.getController();
        int size = currC.comboBox.getItems().size();
        System.out.println(size);
    for(Items a : ItemDatabase.itemData) {
            if(size > 8)
                break;
            currC.comboBox.getItems().add(a.name);
            size++;
        }
        System.out.println(currC);

    }
    public void myItemsShow() throws IOException {
      scene.getScene("Items", "My Items");
    }

}
