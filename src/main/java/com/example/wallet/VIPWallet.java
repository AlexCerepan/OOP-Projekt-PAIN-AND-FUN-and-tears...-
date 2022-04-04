package com.example.wallet;

public class VIPWallet extends Wallet{

   public void addToWallet(float num){
        this.value += num*0.98f;
    }
}
