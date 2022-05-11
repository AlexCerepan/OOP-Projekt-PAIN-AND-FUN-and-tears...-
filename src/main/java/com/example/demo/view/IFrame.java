package com.example.demo.view;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface IFrame {
    GridPane gridPane1 =  new GridPane();
    GridPane gridPane2 =  new GridPane();
    GridPane gridPane3 = new GridPane();
    Scene scene1  = new Scene(gridPane1, 400, 400);
    Scene scene2  = new Scene(gridPane2, 400, 400);
    Scene scene3  = new Scene(gridPane3, 400, 400);

    /**
     * vytvorenie prveho view(Login)
     * **/
    void setAndeInitialize1();
    /**
     * vytvorenie druheho view(Register)
     * */
    void setAndeInitialize2();
    /**
     * vytvorenie tretieho view (platba za VIP clenstvo)
     * */
    void setAndeInitialize3();
    /**
     * priradenie kontroleru pre dane sceny
     * */
    void Initializecontroller();

}
