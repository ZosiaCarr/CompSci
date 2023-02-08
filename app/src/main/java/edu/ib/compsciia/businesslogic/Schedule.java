package edu.ib.compsciia.businesslogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Schedule implements Serializable {
    public Schedule() {
        activities = new ArrayList<Activity>();
        daysOfTheWeek = new ArrayList<Integer>();
    }

    private final List<Activity> activities;
    private String shortDescription;
    private final List<Integer> daysOfTheWeek;
    private int alertTimeBefore;
    private String time;

    public List<Activity> getActivities () {
        return activities;
    }
    public boolean hasDayOfWeek (int d) {
          return daysOfTheWeek.contains(d);
    }
    public void removeDayOfTheWeek (int d) {
         daysOfTheWeek.remove(d);
    }
    public boolean addDayOfWeek (int d) {
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
    public List<Calendar> getRunsBetweenDates(Calendar startDate,Calendar endDate)
    {
        List<Calendar> returnData = new ArrayList<Calendar>();
        String[] runTime = time.split(":");

        startDate.set(Calendar.HOUR_OF_DAY,Integer.parseInt(runTime[0]));
        startDate.set(Calendar.MINUTE,Integer.parseInt(runTime[1]));
        while (startDate.compareTo(endDate) != 0)
        {
            if(daysOfTheWeek.contains(startDate.get(Calendar.DAY_OF_WEEK)))
            {
                returnData.add(startDate);
            }
            startDate.add(Calendar.DATE,1);
        }
        return returnData;
    }
    public Calendar getNextRunDate()
    {
        Calendar date = Calendar.getInstance();
        String[] runTime = time.split(":");

        date.set(Calendar.HOUR_OF_DAY,Integer.parseInt(runTime[0]));
        date.set(Calendar.MINUTE,Integer.parseInt(runTime[1]));
        while (true)
        {
            if(daysOfTheWeek.contains(date.get(Calendar.DAY_OF_WEEK)))
            {
                return date;
            }
        }
    }

}


