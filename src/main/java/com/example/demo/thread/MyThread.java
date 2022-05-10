package com.example.demo.thread;

import com.example.Items.ItemDatabase;
import com.example.Items.Items;
import java.util.Random;

public class MyThread extends Thread{

    public int n;

    public MyThread (int n){
        this.n = n;
    }

    public void create_Items(){
        String[] names_for_retiazka = new String[7];
        names_for_retiazka[0] = "Zlata ";
        names_for_retiazka[1] = "Strieborna ";
        names_for_retiazka[2] = "Platinova ";
        names_for_retiazka[3] = "Diamantova ";
        names_for_retiazka[4] = "Smaragdova ";
        names_for_retiazka[5] = "Rubinova ";
        names_for_retiazka[6] = "retiazka";
        String[] names_for_nahrdelnik = new String[7];
        names_for_nahrdelnik[0] = "Zlaty ";
        names_for_nahrdelnik[1] = "Strieborny ";
        names_for_nahrdelnik[2] = "Platinovy ";
        names_for_nahrdelnik[3] = "Diamantovy ";
        names_for_nahrdelnik[4] = "Smaragdovy ";
        names_for_nahrdelnik[5] = "Rubinovy ";
        names_for_nahrdelnik[6] = "nahrdelnik";
        String[] names_for_suter = new String[7];
        names_for_suter[0] = "Diamant";
        names_for_suter[1] = "Safir";
        names_for_suter[2] = "Smaragd";
        names_for_suter[3] = "Zemiak";
        names_for_suter[4] = "Ortut";
        names_for_suter[5] = "Zirkon";
        names_for_suter[6] = "Alobal od Elvisa";
    for(int i = 0; i < 7; i++ ){
        ItemDatabase.itemData.add(new Items(names_for_nahrdelnik[new Random().nextInt(6)].concat(names_for_nahrdelnik[6]),new Random().nextInt(5000)));
        ItemDatabase.itemData.add(new Items(names_for_retiazka[new Random().nextInt(6)].concat(names_for_retiazka[6]),new Random().nextInt(2000)));
        ItemDatabase.itemData.add(new Items(names_for_suter[new Random().nextInt(7)],new Random().nextInt(10000)));
    }



    }
}
