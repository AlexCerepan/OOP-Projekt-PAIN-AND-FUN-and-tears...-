package com.example.demo.view;

import com.example.AppUtils.IDs;
import com.example.AppUtils.SetScene;
import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import com.example.User.User;
import com.example.User.UserDatabase;
import com.example.demo.Scenes;
import com.example.demo.controler.LogInControler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.example.AppUtils.SetScene.stage;


public class LoginView extends Application implements IFrame {

    public static Stage actualStage = null;

    public Button logInButton = new Button("Log in");
    public Button registerButton = new Button("Register");
    public Button registerButton2 = new Button("Register");
    public Button getRegistretion = new Button("Start Register");
    public Button backToLogin = new Button("Return");
    public Button backToLogin2 = new Button("Return");
    public Button pay = new Button("Get VIP");
    public Button goToVIP = new Button("GET VIP");
    public Button goToVIP2 = new Button("GET VIP");
    public Button start = new Button("START");
    public Label userNameLabel = new Label("Name");
    public Label userNameLabel2 = new Label("Name");
    public Label userNameLabel3 = new Label("Name");
    public Label userPasswordLabel = new Label("Password");
    public Label userPasswordLabel2 = new Label("Password");
    public Label userPasswordLabel3 = new Label("Password");
    public Label infoLabel = new Label();
    public Label infoLabel2 = new Label();
    public Label infoLabel3 = new Label();
    public Label EmailLabel = new Label ("Email");
    public PasswordField password = new PasswordField();
    public PasswordField password2 = new PasswordField();
    public PasswordField password3 = new PasswordField();
    public TextField text = new TextField();
    public TextField text2 = new TextField();
    public TextField text3 = new TextField();
    public TextField Email = new TextField();
    public TextField Email2 = new TextField();



    // toto je len na testovanie
    SetScene scene = new Scenes();

    public static LogInControler controler = null;


   @Override
    public void start(Stage stage) throws IOException {
       // len na skusanie potom vymazat
       ItemDatabase.itemData.add(new Items("prasa", 558.5F));
       UserDatabase.users.add(new User("a","aaaaa", "a", IDs.Bacis));
       LogInControler.currUser = UserDatabase.users.get(0);
       // potialto
       actualStage = SetScene.stage;
       actualStage.setScene(scene.getScene("workScene"));
       setAndeInitialize1();
       setAndeInitialize2();
       setAndeInitialize3();
       Initializecontroller();
       controler.createAdmin();

         getRegistretion.setOnAction(e -> actualStage.setScene(scene2));
         backToLogin.setOnAction(e -> actualStage.setScene(scene1));
         registerButton.setOnAction(e -> controler.addToDatabase(text2.getText(), password2.getText(),
               Email.getText()));


            backToLogin2.setOnAction(e -> actualStage.setScene(scene1));

             registerButton2.setOnAction(e -> controler.addToDatabase(text3.getText(), password3.getText(),
               Email2.getText()));

             backToLogin2.setOnAction(e -> actualStage.setScene(scene1));


         logInButton.setOnAction(e -> {
           try {
               controler.logIn(text.getText(), password.getText());
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       });
       goToVIP.setOnAction(e -> actualStage.setScene(scene3));

       goToVIP2.setOnAction(e -> actualStage.setScene(scene3));

       pay.setOnAction(e -> {
           try {
               controler.payforVIP(text3.getText(), password3.getText());
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       });

       actualStage.show();
    }



    @Override
   public void setAndeInitialize1() {
        actualStage.setTitle("Log in");
         // nastavenie okna
        gridPane1.setPadding(new Insets(5,5,5,5));
        gridPane1.setVgap(8);
        gridPane1.setHgap(5);
        GridPane.setConstraints(userNameLabel, 5, 4);
        GridPane.setConstraints(text,5,5);
        text.setPromptText("Enter name");
        GridPane.setConstraints(userPasswordLabel, 5,6);
        GridPane.setConstraints(password, 5, 7);
        password.setPromptText("Enter password");
        GridPane.setConstraints(logInButton, 5, 8);
        GridPane.setConstraints(getRegistretion, 5, 9);
        infoLabel.setVisible(false);
        GridPane.setConstraints(infoLabel,5,10);
        GridPane.setConstraints(goToVIP,5,11);
        gridPane1.getChildren().addAll(text, password, userNameLabel, userPasswordLabel,
                logInButton, getRegistretion, infoLabel, goToVIP);
    }

    public void setAndeInitialize2 (){
        actualStage.setTitle("Register");
        // nastavenie okna
        gridPane2.setPadding(new Insets(5,5,5,5));
        gridPane2.setVgap(10);
        gridPane2.setHgap(5);
        GridPane.setConstraints(EmailLabel, 5, 2);
        GridPane.setConstraints(Email, 5, 3);
        Email.setPromptText("Enter Email");
        GridPane.setConstraints(userNameLabel2, 5, 4);
        GridPane.setConstraints(text2,5,5);
        text2.setPromptText("Enter name");
        GridPane.setConstraints(userPasswordLabel2, 5,6);
        GridPane.setConstraints(password2, 5, 7);
        password2.setPromptText("Enter password at least 5 characters");
        GridPane.setConstraints(registerButton, 5, 8);
        infoLabel2.setVisible(false);
        GridPane.setConstraints(infoLabel2,5,9);
        GridPane.setConstraints(backToLogin, 5,10);
        GridPane.setConstraints(goToVIP2,5,11);
        gridPane2.getChildren().addAll(text2, password2, userNameLabel2, userPasswordLabel2,
                 registerButton, infoLabel2, Email, EmailLabel, backToLogin, goToVIP2);
    }

    @Override
    public void setAndeInitialize3() {
        actualStage.setTitle("Register for VIP");
        // nastavenie okna
        gridPane3.setPadding(new Insets(5,5,5,5));
        gridPane3.setVgap(10);
        gridPane3.setHgap(5);
        GridPane.setConstraints(userNameLabel3, 5, 4);
        GridPane.setConstraints(text3,5,5);
        text3.setPromptText("Enter name");
        GridPane.setConstraints(userPasswordLabel3, 5,6);
        GridPane.setConstraints(password3, 5, 7);
        password3.setPromptText("Enter password");
        GridPane.setConstraints(pay,5,8);
        infoLabel3.setVisible(false);
        GridPane.setConstraints(infoLabel3,5,9);
        GridPane.setConstraints(backToLogin2, 5,10);
        gridPane3.getChildren().addAll(text3, password3, userNameLabel3, userPasswordLabel3,
                infoLabel3, backToLogin2, pay);
    }



    @Override
    public void Initializecontroller() {
        controler  = new LogInControler(this);
    }

    public static void main(String[] args) {



        launch();

    }


}

