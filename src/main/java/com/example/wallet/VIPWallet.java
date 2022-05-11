package com.example.wallet;
/**
 * 8
 * */
public class VIPWallet extends Wallet{

   public void addToWallet(float num){
        this.value += num*0.98f;
    }

    @Override
    public float getValue() {
        return this.value;
    }
}
