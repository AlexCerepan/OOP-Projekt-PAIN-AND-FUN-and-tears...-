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

import static com.example.User.UserDatabase.users;
import static com.example.demo.controler.AdminController.addAdmin;
import static com.example.demo.controler.AdminController.removeAdmin;

public class LogInControler implements Notify{

    SetScene scene = new Scenes();

    public static boolean paying = false;

    private TrieTree tree = new TrieTree();
    private IFrame _window;
    public static String generalName;
    public static  User currUser;
    private Notify notify;

    public LogInControler(IFrame window){
        this._window = window;
    }
    public LogInControler(){}

    /**
     * @param name meno ktoré si používateľ zvolí
     *
     * @param password heslo ktoré si používateľ zvolí
     *
     * @param Email mail pod ktorým sa používateľ prihlasuje
     *
     * daná metóda zachytáva registrácie používateľov a pridáva ich do databázi ako aj do
     * tree štruktúry aby sa mohli vyhodnocovať login akcie
     * */

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
/**tato funkcia overuje spravnost prihlasenia pouzivatela
 *
 * @param name meno pouzivatela
 *
 * @param Password heslo uzivatela
 *
 * @throws IOException ak sa nenajde fxml subor
 *
 * */
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

            if(currUser.ban){
              scene.getScene("banAlert", "Banned");
            }
            else {
                currUser.online = true;
                UserDatabase.currActiveUsers.add(currUser);
                if (currUser.myID == IDs.Bacis) {

                    ((LoginView) _window).actualStage.setScene(scene.getScene("workScene"));

                      if(removeAdmin){
                        notify = new AdminController();
                        notify.notifyPls();
                        removeAdmin = false;
                    }
                }
                else if (currUser.myID == IDs.VIPs){

                    ((LoginView) _window).actualStage.setScene(scene.getScene("workSceneForVIP"));

                    if(addAdmin){
                        System.out.println("malo by vyskocit okno");
                        notify = new AdminController();
                        notify.notifyPls();
                        addAdmin = false;
                    }
                }
                    // skusam mu spravit samostatne okno
                else if (currUser.myID == IDs.Admin)
                    scene.getScene("Admin", "hello");

            }

        }
        else {
            ((LoginView) _window).infoLabel.setText("Wrong password");
        }

    }
    ((LoginView) _window).infoLabel.setVisible(true);

}
 /**
  *
  * v tejto funkcii sa pouzivatel moze dostat do okna v ktorom si po vyplneni udajov je schopny zakupit
  * VIP clenstvo
  *
  * @param name meno uzivateľa ktora si chce kupit VIP clenstvo
  *
  * @param password jeho heslo na overenie ze to je on
  * **/
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
            if (currUser.ban) {
                scene.getScene("banAlert", "Banned");
            }
            else if (currUser.VIP)
                ((LoginView) _window).infoLabel3.setText("You already have VIP");

            else {
                currUser.online = true;
                UserDatabase.currActiveUsers.add(currUser);
                System.out.println("Current user: " + currUser.name + " ID " + currUser.myID);
                ((LoginView) _window).actualStage.setScene(scene.getScene("payment"));

            }
        }
        else {
            ((LoginView) _window).infoLabel3.setText("Wrong password");
        }

    }
    ((LoginView) _window).infoLabel3.setVisible(true);
}

/**
 * funkcia na vykonanie bezpecneho downcastu aby sme mali istotu ze pracujeme so spravnym oknom
 *
 * @return vracia ci je dana premenna instancia pre okno s ktorym chcem pracovat
 *
 * */
private boolean SaveDownCast() {
        return this._window instanceof LoginView;
    }

/**
 * v danej funkci overujem ci bol user povyseny na VIP status ak ano priradim mu novu penazenku do ktorej
 * vlozim ciastku ktoru mal v predoslej
 * */
    @Override
    public void notifyPls() {
        for (User u : users){
            if(Objects.equals(u.name, generalName))
                currUser = u;
        }
        currUser.VIP = true;
        float addToWall = currUser.wallet.value;
        currUser.myID = IDs.VIPs;
        currUser.wallet = new VIPWallet();
        currUser.wallet.value += addToWall;

        System.out.println("current user: " + currUser.name + " ID " + currUser.myID);
    }
/**
 * vytvorenie admina
 * **/
    public void createAdmin(){
        User admin = new User("Alex", "OOPisBest", "zahradka@juchu.fx", IDs.Admin );
        admin.admin = true;
        users.add(admin);
        tree.insert(admin.name);
    }
}
