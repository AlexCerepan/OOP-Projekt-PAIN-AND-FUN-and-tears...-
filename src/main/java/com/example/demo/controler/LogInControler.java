package com.example.demo.controler;

import com.example.AppUtils.IDs;
import com.example.demo.Scenes;
import com.example.AppUtils.SetScene;
import com.example.RegisterControl.TrieTree;
import com.example.User.User;
import com.example.User.UserDatabase;
import com.example.demo.view.IFrame;
import com.example.demo.view.LoginView;
import com.example.wallet.VIPWallet;

import java.io.IOException;
import java.util.Objects;

import static com.example.User.UserDatabase.currActiveUsers;
import static com.example.User.UserDatabase.users;

public class LogInControler implements Notify{

    SetScene scene = new Scenes();

    public static boolean paying = false;

    private TrieTree tree = new TrieTree();
    private IFrame _window;
    public static String generalName;
    User currUser;

    public LogInControler(IFrame window){
        this._window = window;
    }
    public LogInControler(){}

public void addToDatabase (String name, String password, String Email){

    if(SaveDownCast()){
            // kontrolovanie mena ci uz bolo pouzite pomocou trie datovej štruktury
            if((!(tree.search(name)) && (!(tree.search(Email))) )) {
                // musim mat zdane heslo, meno, a email aby som sa uspesne mohol zaregistrovat
                if (Email.equals("") || password.equals("") || name.equals("")) {
                    ((LoginView) _window).infoLabel2.setText("You forgot to write ur Email or name or password");
                }
                // heslo musi mat aspon 5 znakov aby bolo dostatocne
                else if (password.length() < 5) {
                    ((LoginView) _window).infoLabel2.setText("Password must have at least 5 characters");

                }// ak splnam vsetky podmienky tak sa vykona registracia a ulozenie do mojej fake "databazy"
                else {
                    tree.insert(name);
                    tree.insert(Email);
                    users.add(new User(name, password, Email, IDs.Bacis));
                    ((LoginView) _window).infoLabel2.setText("Succesful registration");
                }
            }
            // ak je meno pouzite alebo email tak viem ze musim pouzit iny alebo ine
            else {
                ((LoginView) _window).infoLabel2.setText("Name or Email already used");
            }
    }
        // len vypisujem ze co sa deje na okne
    ((LoginView) _window).infoLabel2.setVisible(true);

}

public void logIn(String name, String Password) throws IOException {
    for( User u : users){
            if(Objects.equals(u.name, name))
                 currUser =  u;
        }

    if(currUser == null){
        ((LoginView) _window).infoLabel.setText("This profile does not exist u should click on Register");
    }

    else{
        if(Objects.equals(currUser.password, Password)) {
                currUser.online = true;
            UserDatabase.currActiveUsers.add(currUser);
            if(currUser.myID == IDs.Bacis)
                ((LoginView) _window).actualStage.setScene(scene.getScene("workScene"));

            else if (currUser.myID == IDs.VIPs)
                ((LoginView) _window).actualStage.setScene(scene.getScene("workSceneForVIP"));

            else if (currUser.myID == IDs.Admin)
                ((LoginView) _window).actualStage.setScene(scene.getScene("workSceneForAdmin"));


        }
        else {
            ((LoginView) _window).infoLabel.setText("Wrong password");
        }

    }
    ((LoginView) _window).infoLabel.setVisible(true);

}

public void payforVIP(String name, String password) throws IOException{

    generalName = name;

    for( User u : users){
        if(Objects.equals(u.name, name))
           currUser =  u;
    }
    if(currUser == null){
        ((LoginView) _window).infoLabel3.setText("This profile does not exist u should click on Register");
    }

    else{
        if(Objects.equals(currUser.password, password)) {
            currUser.online = true;
            UserDatabase.currActiveUsers.add(currUser);
            System.out.println("Current user: " + currUser.name + " ID " + currUser.myID);
            ((LoginView) _window).actualStage.setScene(scene.getScene("payment"));
        }
        else {
            ((LoginView) _window).infoLabel3.setText("Wrong password");
        }

    }
    ((LoginView) _window).infoLabel3.setVisible(true);
}


private boolean SaveDownCast() {
        return this._window instanceof LoginView;
    }


    @Override
    public void notifyPls() {
        for (User u : users){
            if(Objects.equals(u.name, generalName))
                currUser = u;
        }
        currUser.VIP = true;
        currUser.myID = IDs.VIPs;
        currUser.wallet = new VIPWallet();

        System.out.println("current user: " + currUser.name + " ID " + currUser.myID);
    }

    public void createAdmin(){
        User admin = new User("Alex", "OOPisBest", "zahradka@juchu.fx", IDs.Admin );
        admin.admin = true;
        users.add(admin);
        tree.insert(admin.name);
    }
}
