package edu.ib.compsciia.businesslogic;

import java.util.ArrayList;
import java.util.List;

public class Schedule  extends BaseObject{
    public Schedule(LifeFormManager lfm) {
        super(lfm);
        activities = new ArrayList<Activity>();
        daysOfTheWeek = new ArrayList<daysOfWeek>();
    }

    private List<Activity> activities;
   private List<daysOfWeek> daysOfTheWeek;
    private int alertTimeBefore;
    private int time;


    public List<Activity> getActivities () {
        return activities;
    }
 //   public List<daysOfWeak> getDaysOfTheWeek () {
  //      return daysOfTheWeek;
   // }


    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getAlertTimeBefore() {
        return alertTimeBefore;
    }
    public void setAlertTimeBefore(int alertTimeBefore) {
        this.alertTimeBefore = alertTimeBefore;
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
