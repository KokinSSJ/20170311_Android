package com.example.kokin.encryption;

/**
 * Created by Kokin on 2017-03-11.
 */

public class Adapter implements MyAlgorithm {

    private NewAlgorithm newAlgorithm = new NewAlgorithm();


    private int[] convertToNums(String key){
        int[] keyInt = new int [key.length()];

        for(int i = 0; i<key.length(); i++){
            keyInt[i] = Integer.parseInt(key.charAt(i)+""); // nie rzutowanie (int) bo wtedy dostaniemy reprezentacje z ascii!

        }
        return keyInt;
    }

    @Override
    public String encrypt(String text, String key) {
        return  newAlgorithm.enc(text, convertToNums(key));
    }
}
