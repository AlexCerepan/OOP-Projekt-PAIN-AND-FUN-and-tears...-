package com.example.demo.controler;

import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
import com.example.User.User;
import com.example.User.UserDatabase;
import com.example.demo.Scenes;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import com.example.wallet.BasicWallet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.controler.LogInControler.currUser;

public class AdminController implements Notify{

    public static boolean removeAdmin = false;

    private  User searched;

    private SetScene scene = new Scenes();


    @FXML
    Label showMessage;

    @FXML
    Button removeVip;

    @FXML
    Button ban;

    @FXML
    Button returnB;

    @FXML
    Button findUser;

    @FXML
    Button search;

    @FXML
    TextField userName;

    @FXML
    protected void onFindUserClick(){
        userName.setVisible(true);
    }

    @FXML
    protected void onSearchClick(){
        boolean found = false;
        for(User u : UserDatabase.users){
            if(Objects.equals(u.name, userName.getText())) {
                searched = u;
                removeVip.setVisible(true);
                ban.setVisible(true);
                showMessage.setText("Found: "+ userName.getText());
                showMessage.setVisible(true);
                found = true;
            }
            }
        if(!found) {
            showMessage.setText("Not found: "+ userName.getText());
            showMessage.setVisible(true);
            removeVip.setVisible(false);
            ban.setVisible(false);
        }
    }

    @FXML
    protected void onBanClick(){
        searched.ban = true;
        showMessage.setText(userName.getText() + " has ben banned");
        showMessage.setVisible(true);
    }

    @FXML
    protected void onRemoveVIPClick(){
        searched.VIP = false;
        searched.myID = IDs.Bacis;
        float value = searched.wallet.value;
        searched.wallet = new BasicWallet();
        searched.wallet.value += value;
        showMessage.setText(userName.getText() + " has no longer VIP");
        showMessage.setVisible(true);
        removeAdmin = true;
    }


    @FXML
    protected void onReturnButtonClick () {
        UserDatabase.currActiveUsers.remove(currUser);
        currUser.online = false;
        showMessage.setVisible(false);
        LoginView.actualStage.setScene(IFrame.scene1);
    }

    @Override
    public void notifyPls() throws IOException {
        scene.getScene("noLongerVIP", "Not sorry");
    }
}
