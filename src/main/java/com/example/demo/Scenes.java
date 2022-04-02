package com.example.demo;

import com.example.AppUtils.SetScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Scenes implements SetScene {


    public Scene getScene(String nameOfScene) throws IOException {

        if("workScene".equals(nameOfScene)) {
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("workSpace.fxml"));
            return new Scene(loader.load(), 400, 400);
        }
        else if ("payment".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("payment.fxml"));
            return new Scene(loader.load(), 400, 400);
        }
        else if("workSceneForVIP".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("workSpaceForVIP.fxml"));
            return new Scene(loader.load(), 400, 400);
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
       else if ("alertBoxForVIP".equals(nameOfScene)){
            FXMLLoader loader = new FXMLLoader(Scenes.class.getResource("alertBoxForVIP.fxml"));
            scene = new Scene(loader.load(), 200, 200);

        }

        window.setTitle(title);
        window.setScene(scene);
        window.showAndWait();

    }

}
