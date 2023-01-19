package edu.ib.compsciia.businesslogic;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    public Schedule() {
        activities = new ArrayList<Activity>();
        daysOfTheWeek = new ArrayList<daysOfWeek>();
    }

    private final List<Activity> activities;
    private String shortDescription;
    private final List<daysOfWeek> daysOfTheWeek;
    private int alertTimeBefore;
    private String time;




    public List<Activity> getActivities () {
        return activities;
    }
    public boolean hasDayOfWeek (daysOfWeek d) {
          return daysOfTheWeek.contains(d);
    }
    public void removeDayOfTheWeek (daysOfWeek d) {
         daysOfTheWeek.remove(d);
    }
    public boolean addDayOfWeek (daysOfWeek d) {
        return daysOfTheWeek.add(d);
    }


    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getAlertTimeBefore() {
        return alertTimeBefore;
    }
    public void setAlertTimeBefore(int alertTimeBefore) {
        this.alertTimeBefore = alertTimeBefore;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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
