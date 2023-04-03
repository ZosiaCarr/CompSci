package edu.ib.compsciia.businesslogic;

import java.util.Date;

public abstract class LifeForm  implements java.io.Serializable{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public abstract boolean hasBirthDay();

    public abstract int getColor();

    public abstract int getIcon();

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }


    private String species;
    private String name;
    private String description;

    private Date birthDay;


}
