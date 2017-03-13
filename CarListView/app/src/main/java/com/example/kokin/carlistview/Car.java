package com.example.kokin.carlistview;

/**
 * Created by Kokin on 2017-03-11.
 */

public class Car {

    private String made;
    private Boolean isUsed;
    private Integer id;
    private static Integer idTemp =1;

//    List<Car> cars = new LinkedList<>(); // mozna by ale nie ma dokładnie opisanego ID

    public Car(String made, Boolean isUsed) {
        this.made = made;
        this.isUsed = isUsed;
        this.id=idTemp;
        idTemp++;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
