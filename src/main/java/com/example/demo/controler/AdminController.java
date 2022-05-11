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

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.controler.LogInControler.currUser;

public class AdminController implements Notify {


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

    /**
     * v tejto metode moze admin pridavat predmety na drazenie predmet sa prida do vyberu pouzivatela
     * a zaroven aj do databazy
     * */
    @FXML
    protected void onAddThisClick(){
        addThis();
    }


/**
 * funkcia zobrazi polia do ktorych mozno vpisat naázov a cenu tovaru
 * **/
    @FXML
    protected void onAddAucClick() {
        remAuc.setVisible(true);
        userName.setVisible(true);
        userName.setPromptText("add or remove Item");
        costOfItem.setVisible(true);
        addthis.setVisible(true);
    }

    /**
     *
     * v danej funkcii admin moze odstránit predmet z drazby ako aj z databazy takze dany predmet uz nebude
     * mozne zakupit
     *
     **/
    @FXML
    protected void onRemoveAucClick(){
        remove();
    }

    /**
     *
     * metoda sluzi na spristupnenie fieldu na pisanie pre najdenie pouzivatela
     * */
    @FXML
    protected void onFindUserClick() {
        userName.setVisible(true);
        costOfItem.setVisible(false);
        userName.setPromptText("Find User");
    }


    /**
     * po stlaceni tlacidla search vyhodnoti dana metada ci dany pouzivatel existuje
     * */
    @FXML
    protected void onSearchClick() {
        search();
    }
    /**
     * ak sa pouzivatel existuje dana metada umoznuje adminovy ho zabanovat takze sa uz nebude moct prihlásit
     * */
    @FXML
    protected void onBanClick() {
        ban();
    }
    /**
     * ak sa pouzivatel existuje dana metoda umoznuje adminovy mu odobrat VIP status
     * */
    @FXML
    protected void onRemoveVIPClick() {
        removeVIP();
    }

    /**
     * ak sa pouzivatel existuje dana metada umoznuje adminovy mu dat VIP status
     * */
    @FXML
    protected void onAddVIPClick() {
        addVIP();
    }

    /**
     * dana funkcia zatvori okno pre admina po stlaceni tlacidla return
     * **/
    @FXML
    protected void onReturnButtonClick() {
        returnB();
    }

    /**
     * ak admin niekomu odoberie alebo da VIP status pri naslednom prihláseni mu zobrazi okno v ktorom
     * je pouzivatel informovany o danej akcii
     *
     * @throws IOException ak sa nenajde fxml subor
     *
     * */
    @Override
    public void notifyPls() throws IOException {
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
