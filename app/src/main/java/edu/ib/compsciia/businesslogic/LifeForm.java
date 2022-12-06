package edu.ib.compsciia.businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class LifeForm  implements java.io.Serializable{
    public LifeForm() {
        activities = new ArrayList<Activity>();
    }

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

    private List<Activity> activities;
    public List<Activity> getActivities () {
        return activities;
    }
    public void addActivity(Activity ac)
    {
        this.activities.add(ac);
    }
    public void removeActivity(Activity ac)
    {
        this.activities.remove(ac);
    }


}
