package com.example.wallet;

/**
 * tato class drzi hodnotu penazi ktore ma user na ucte
 * má 2 typy a to basic a VIP
 * **/
public abstract class Wallet {
    public float value;


    /**
     * funkcia na vypocet vlozenych penazi do penazenky
     *
     * @param num ak vkladam peniaze tak tato premenna zistuje aku ciastku
     * **/
    public void addToWallet(float num){}

    public float getValue(){return value;}
}
