package com.example.kokin.encryption;

/**
 * Created by Kokin on 2017-03-11.
 */
public class NewAlgorithm {

    public String enc (String text, int [] k){
        StringBuilder encrypt = new StringBuilder();
        //szyfrowanie polega na dodawniu do wartosci ascii (textu) kolejngo znaku szyfrowanego,
        // liczby z tablicy K z odpowiedniej pozycji
        int i =0;
        char cryptChar;
        for(char c : text.toCharArray()){
            cryptChar=(char)((c+k[i])%127);
            i++;
            i=i%(k.length);


            encrypt.append(cryptChar);
        }
        return encrypt.toString();
    }
}
