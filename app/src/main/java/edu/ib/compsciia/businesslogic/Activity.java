package edu.ib.compsciia.businesslogic;

public class Activity   {

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String ToString() {
        if(description.length() >20)
            return description.substring(0,19) + "...";
        return description;
    }
}
