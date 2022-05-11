package com.example.demo.controler;

import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.demo.Auction.Auction;
import com.example.demo.Scenes;
import com.example.demo.myExeption.MyException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class AuctionMenuController implements Notify {

    public static AuctionMenuController currC;
    Items selingItem;
    SetScene scene = new Scenes();


    @FXML
    Button start;

    @FXML
    Button returnB;

    @FXML
    Label show2;

    @FXML
    public ComboBox<String> comboBox;


    /**
     * v tejto funkcii sa vraciam po stlaceni tlacidla do predchadzajuceho view teda pracovneho pre
     * pouzivatela
     *
     * @throws IOException ak sa nenajde fxml subor
     * */
    @FXML
    protected void onReturnButtonClick() throws IOException {
        currC.returnB();
    }

    /**
     * v tejto funkcii sa po vybrati tovaru o ktory sa chcem uchadzat nastavy okno aukcie kde mozem
     * na dany tovar prihadzovat ak si nevyberiem tovar zobrazi sa mi okno ze si najprv musim vybrat tovar
     * az potom mozem pokracovat
     *
     * @throws IOException ak sa nenajde fxml subor
     *
     * */

    @FXML
    protected void onStartClick() throws IOException {
        startAuction();
    }

    @FXML
    protected void onBoxClick() {
        System.out.println("I choose " + comboBox.getValue());
    }


    public void startAuction() throws IOException {

        if (Objects.equals(comboBox.getValue(), null)) {
            try{
                throw new MyException();
            }catch(MyException e){
                e.throwThat();
            }

        }
        else {

            for(Items i : ItemDatabase.itemData){
                if(i.name.equals(comboBox.getValue())) {
                    selingItem = i;
                    break;
                }
            }
            FXMLLoader load = new FXMLLoader(Scenes.class.getResource("auction.fxml"));
            Stage start = new Auction(load, "Welcome!", selingItem);
            ControlerManagment.auctionDatabase.add((Auction) start);
            ControlerManagment.auctionControllerDatabase.add((((Auction) start).loader.getController()));
            ControlerManagment.auctionDatabase.get(ControlerManagment.auctionDatabase.size()-1).items = selingItem;
            ControlerManagment.auctionControllerDatabase.get(ControlerManagment.auctionControllerDatabase.size()-1).item = selingItem;
            ControlerManagment.auctionControllerDatabase.get(ControlerManagment.auctionControllerDatabase.size()-1).showWhatIsSelling(selingItem.name, selingItem.cost);
        }
        //   System.out.println("---------------------------------------");

    }


    public void returnB() throws IOException {
        if (LogInControler.currUser.myID == IDs.Bacis) {
            scene.stage.setScene(scene.getScene("workScene"));
        } else if (LogInControler.currUser.myID == IDs.VIPs) {
            scene.stage.setScene(scene.getScene("workSceneForVIP"));
        }
    }

/**
 * metoda ktora zachyti ci sa dany tovar predal a ak ano vyradi sa z listu tovarov na drazenie ako aj z databazy
 * */
    public void notifyPls() {
        currC.comboBox.getItems().remove(selingItem.name);
        ItemDatabase.itemData.remove(selingItem);
    }
}
