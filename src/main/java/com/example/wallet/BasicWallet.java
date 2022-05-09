package com.example.wallet;

public class BasicWallet extends Wallet{

    public void addToWallet(float num){
        this.value += num*0.95f;
    }
    public float getValue(){
        return this.value;
    }
}
