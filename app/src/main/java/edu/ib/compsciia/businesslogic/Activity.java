package edu.ib.compsciia.businesslogic;

import java.util.ArrayList;
import java.util.List;

public class Activity   {

    public Activity()
    {
        lifeForms = new ArrayList<>();
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LifeForm> getLifeForms() {
        return lifeForms;
    }

    public void addLifeForm(LifeForm lifeForm) {
        if(!this.lifeForms.contains(lifeForm))
            this.lifeForms.add(lifeForm);
    }

    public void removeLifeForm(LifeForm lifeForm) {
        if(this.lifeForms.contains(lifeForm))
            this.lifeForms.remove(lifeForm);
    }

    private List<LifeForm> lifeForms;
    private String description;

    public String ToString() {
        if(description.length() >20)
            return description.substring(0,19) + "...";
        return description;
    }
}
