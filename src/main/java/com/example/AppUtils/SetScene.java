package com.example.AppUtils;

import com.example.demo.view.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface SetScene {
    Stage stage = new Stage();
    Stage forAlert = new Stage();
    Stage forAdmin = new Stage();

    Scene getScene (String nameOfScene) throws IOException;
    void getScene (String nameOfScene, String title) throws IOException;

}
