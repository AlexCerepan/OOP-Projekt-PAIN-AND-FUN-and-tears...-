package com.example.demo;

import com.example.AppUtils.SetScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Scenes implements SetScene {

    public static boolean pay = false;

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
        else if("workSceneForAdmin".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("workSpaceForAdmin.fxml"));
            return new Scene(loader.load(), 500, 500);
        }
        else if("addMoney".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("addMoney.fxml"));
            pay = true;
            return new Scene(loader.load(), 400,400);

        }

        else return null;


    }

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

        window.setTitle(title);
        window.setScene(scene);
        window.showAndWait();

    }

}
