package com.example.demo;

import com.example.AppUtils.SetScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/***
 *
 * vytvaranie scen
 * */
public class Scenes implements SetScene {

    public static boolean pay = false;
    public static FXMLLoader loader;
    public static FXMLLoader ldr;

    /**
     * tato funkcia slzii na vyberanie scen pre hlavny stage priradzuje im kontroler, ktory najprv vytvori
     * podobna funkcia ako "void getScene"
     *
     * @param nameOfScene tento parameter sluzi na vybratie typu sceny ktora ma nasledovat
     *
     * @throws IOException ak sa nedostanem k fxml suboru vyhodi sa mi exception
     *
     * @return vracia danu scenu ktora sa potom nastavy aby bola zobrazena
     * */
    public Scene getScene(String nameOfScene) throws IOException {

        if("workScene".equals(nameOfScene)) {
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("workSpace.fxml"));
            return new Scene(loader.load(), 500, 500);
        }
        else if ("payment".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("payment.fxml"));
            return new Scene(loader.load(), 400, 400);
        }
        else if("workSceneForVIP".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("workSpaceForVIP.fxml"));
            return new Scene(loader.load(), 500, 500);
        }
        else if("addMoney".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("addMoney.fxml"));
            pay = true;
            return new Scene(loader.load(), 500,500);

        }
        else if ("auctionChoose".equals(nameOfScene)){
            ldr = new FXMLLoader(Scenes.class.getResource("auctionSpace.fxml"));
            return new Scene(ldr.load(), 500, 500);
        }

        else return null;


    }

    /**
     * Tato funkcia slzii na zistenie a nastavenie scneny ktora sa ma vytvorit primarne pre
     * alerty nema návratovu hodnotu a rovno aj zobrazi okno alertu
     *
     * @param nameOfScene tento parameter sluzi na vybratie spravneho fxml suboru ktorý mi
     *                    vytvori nasledne scenu
     *
     * @param title priradi danenu GUI title
     *
     * @throws IOException ak sa nedostane ku fxml suboru
     *
     * **/
    @Override
    public void getScene(String nameOfScene, String title) throws IOException {

        Stage window;
         window = SetScene.forAlert;
         Scene scene = null;

        if("payment fail".equals(nameOfScene)) {
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBox.fxml"));
            scene = new Scene(loader.load(), 200, 200);

        }
       else if("payment suc".equals(nameOfScene)) {
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBox2.fxml"));
            scene = new Scene(loader.load(), 200, 200);

        }
       else if ("banAlert".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBox3.fxml"));
            scene = new Scene(loader.load(), 400, 200);

        }
       else if("noLongerVIP".equals(nameOfScene)){
           FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBoxForVIP.fxml"));
           scene = new Scene(loader.load(), 400, 200);
        }

        else if("noLongerVIP2".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBoxForVIP2.fxml"));
            scene = new Scene(loader.load(), 400, 200);
        }
        else if("Admin".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("workSpaceForAdmin.fxml"));
            scene = new Scene(loader.load(), 500, 700);
            SetScene.forAdmin.setTitle(title);
            SetScene.forAdmin.setScene(scene);
            SetScene.forAdmin.show();
            return;
        }

        else if("Items".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("myItems.fxml"));
            scene = new Scene(loader.load(), 500,500);
        }

        else if("exception".equals(nameOfScene)) {
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBoxForException.fxml"));
            scene = new Scene(loader.load(), 300, 200);
        }

        window.setTitle(title);
        window.setScene(scene);
        window.showAndWait();

    }

}
