package com.example.demo.controler;

import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
import com.example.User.User;
import com.example.demo.Scenes;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.demo.Scenes.pay;

public class PaymentController {

    Notify notify;
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
    TextField addMoney;

    /**
     * v tejto metode sa zachytava platba overuje sa ci boli dodrzane stanovene podienky
     * priraduje sa penazna suma danemu pozívatelovi do penazenky a pri uspesnej ale aj neuspesnej platbe
     * sa vytvori male alert okno ktore informuje o úspesnosti danej uperacie
     *
     * @throws IOException ak sa nedostanem k fxml suboru
     *
     * **/

    @FXML
    protected void onPayButtonClick() throws IOException {
        pay();
    }


    /**
     * pomocou danej funkcie sa vraciam naspat do workspace pre pouzivatela
     * @throws IOException ak sa nenájde fxml subor
     * */
    @FXML
    protected void onReturnButtonClick() throws IOException {
     returnB();
    }

    /***
     * dana metoda dostane array hodnot ktore uzivatel vyplnoval pri platbe povolene su lenn cislice od
     * 1 - 9
     * @return vracia true ak bolo vypisavanie udajov uspesne a false ak nie
     * */
    boolean controlSymbols(String arr) {
        for (int i = 0; i < arr.length(); i++) {
            char a = arr.charAt(i);
            if ((a < '0') || (a > '9'))
                return false;
        }
        return true;
    }

    void add_Money() {
        if(controlSymbols(addMoney.getText())) {
            LogInControler.currUser.wallet.addToWallet(Integer.parseInt(addMoney.getText()));
            LogInControler.currUser.onWallet = Integer.parseInt((addMoney.getText()));
            System.out.println(LogInControler.currUser.wallet.value);
        }
    }

    void returnB() throws IOException {
        if (pay) {
            if (LogInControler.currUser.myID == IDs.Bacis) {
                scene.stage.setScene(scene.getScene("workScene"));
            } else if (LogInControler.currUser.myID == IDs.VIPs) {
                scene.stage.setScene(scene.getScene("workSceneForVIP"));
            }
        } else
            LoginView.actualStage.setScene(IFrame.scene1);

        pay = false;

    }

    void pay() throws IOException {
        if (controlSymbols(cardCode.getText()) && controlSymbols(cardID.getText())) {
            if ((cardID.getText().length() < 5)) {

                LogInControler.paying = false;
                scene.getScene("payment fail", "TF DID U DO");

            } else if ((cardCode.getText().length() > 3) && (cardCode.getText().length() <= 4)) {
                LogInControler.paying = false;
                scene.getScene("payment fail", "YOU DIDnt IT");

            } else {
                if (pay) {
                    System.out.println("Najprv peniaze: " + LogInControler.currUser.wallet.value);
                    System.out.println("Pridali sa peniaze");
                    add_Money();
                } else {
                    LogInControler.paying = true;
                    notify = new LogInControler();
                    notify.notifyPls();
                    System.out.println("Notifypls");
                }
                scene.getScene("payment suc", "YOU DID IT");
            }
        } else scene.getScene("payment fail", "EPIC FAIL");
    }

}
