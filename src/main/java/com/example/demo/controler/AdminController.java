package com.example.demo.controler;

import com.example.AppUtils.AdminInform;
import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.User.User;
import com.example.User.UserDatabase;
import com.example.demo.Scenes;
import com.example.wallet.BasicWallet;
import com.example.wallet.VIPWallet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.demo.controler.LogInControler.currUser;

public class AdminController implements Notify {

    public static ArrayList<Stage> activeAuc = new ArrayList<>();

    public static boolean removeAdmin = false;
    public static boolean addAdmin = false;

    private User searched;

    private SetScene scene = new Scenes();
    private final AdminInform inform = new AdminInform();


    @FXML
    TextField costOfItem;

    @FXML
    Button addAuc;

    @FXML
    Button remAuc;

    @FXML
    Button addthis;

    @FXML
    Label showMessage;

    @FXML
    Button removeVip;

    @FXML
    Button ban;

    @FXML
    Button giveVIP;

    @FXML
    Button returnB;

    @FXML
    Button findUser;

    @FXML
    Button search;

    @FXML
    TextField userName;


    @FXML
    protected void onAddThisClick(){
        addThis();
    }



    @FXML
    protected void onAddAucClick() {
        remAuc.setVisible(true);
        userName.setVisible(true);
        userName.setPromptText("add or remove Item");
        costOfItem.setVisible(true);
        addthis.setVisible(true);
    }

    @FXML
    protected void onRemoveAucClick() throws IOException {
        remove();
    }

    @FXML
    protected void onFindUserClick() {
        userName.setVisible(true);
        costOfItem.setVisible(false);
        userName.setPromptText("Find User");
    }

    @FXML
    protected void onSearchClick() {
        search();
    }

    @FXML
    protected void onBanClick() {
        ban();
    }

    @FXML
    protected void onRemoveVIPClick() {
        removeVIP();
    }

    @FXML
    protected void onAddVIPClick() {
        addVIP();
    }


    @FXML
    protected void onReturnButtonClick() {
        returnB();
    }

    @Override
    public void notifyPls() throws IOException {
        System.out.println("som v notify pls pri addadmin a addadmin je: " + addAdmin);
        if (addAdmin) {
            scene.getScene("noLongerVIP2", "Good for you");
        }
            else
            scene.getScene("noLongerVIP", "Not sorry");
    }


    void ban() {
        searched.ban = true;
        showMessage.setText(userName.getText() + " has ben banned");
        showMessage.setVisible(true);
    }

    // odhlasenie sa
    void returnB() {
        UserDatabase.currActiveUsers.remove(currUser);
        currUser.online = false;
        showMessage.setVisible(false);
        SetScene.forAdmin.close();
    }

    // odoberanie VIP

    void removeVIP() {
        if (searched.myID == IDs.VIPs) {
            searched.VIP = false;
            searched.myID = IDs.Bacis;
            float value = searched.wallet.value;
            searched.wallet = new BasicWallet();
            searched.wallet.value += value;
            showMessage.setText(userName.getText() + " has no longer VIP");
            removeAdmin = true;
        } else
            showMessage.setText(userName.getText() + " has no VIP");

        showMessage.setVisible(true);
    }

    void search() {
        userName.setPromptText("Enter user name");
        boolean found = false;
        for (User u : UserDatabase.users) {
            if (Objects.equals(u.name, userName.getText())) {
                searched = u;
                removeVip.setVisible(true);
                ban.setVisible(true);
                giveVIP.setVisible(true);
                showMessage.setText("Found: " + userName.getText());
                showMessage.setVisible(true);
                found = true;
            }
        }
        if (!found) {
            showMessage.setText("Not found: " + userName.getText());
            showMessage.setVisible(true);
            removeVip.setVisible(false);
            ban.setVisible(false);
        }
    }

    void addVIP() {
        if (searched.myID == IDs.Bacis) {
            searched.VIP = true;
            searched.myID = IDs.VIPs;
            float value = searched.wallet.value;
            searched.wallet = new VIPWallet();
            searched.wallet.value += value;
            showMessage.setText(userName.getText() + " promoted to VIP");
            addAdmin = true;
        } else
            showMessage.setText(userName.getText() + " already has VIP");

        showMessage.setVisible(true);
    }

    void addThis(){
        ItemDatabase.itemData.add(new Items(userName.getText(), Float.parseFloat(costOfItem.getText())));
        inform.addAuction(userName.getText());
    }

    void remove(){
        inform.removeAuction(userName.getText());
        for(Items a : ItemDatabase.itemData){
            if(a.name.equals(userName.getText())){
                ItemDatabase.itemData.remove(a);
                break;
            }
        }
    }
}
