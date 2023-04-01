package edu.ib.compsciia.businesslogic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import edu.ib.compsciia.R;

public class Schedule implements Serializable {
    public Schedule() {
        activities = new ArrayList<Activity>();
        daysOfTheWeek = new ArrayList<Integer>();
        Random rand = new Random();
        id = rand.nextInt(10000);
    }

    private final List<Activity> activities;
    private String shortDescription;
    private final List<Integer> daysOfTheWeek;
    private int alertTimeBefore;
    private int id;
    private String time;

    public List<Activity> getActivities () {
        return activities;
    }
    public boolean hasDayOfWeek (int d) {
          return daysOfTheWeek.contains(d);
    }
    public void removeDayOfTheWeek (int i) {
        Integer intObj = new Integer(i);
         daysOfTheWeek.remove(intObj);
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
    public int getUniqueId() {
        return id;
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
        int test = startDate.compareTo(endDate);
        while (startDate.compareTo(endDate) < 0)
        {
            if(daysOfTheWeek.contains(startDate.get(Calendar.DAY_OF_WEEK)))
            {
                returnData.add((Calendar) startDate.clone());
            }
            startDate.add(Calendar.DATE,1);
        }
        return returnData;
    }
    public ScheduleRunDate getNextRunDate()
    {
        Calendar date = Calendar.getInstance();
        String[] runTime = time.split(":");
        Calendar today= Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY,Integer.parseInt(runTime[0]));
        date.set(Calendar.MINUTE,Integer.parseInt(runTime[1]));
        while (true)
        {
            if(daysOfTheWeek.contains(date.get(Calendar.DAY_OF_WEEK)) && today.compareTo(date) <0)
            {
                SimpleDateFormat f = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                String d = f.format(date.getTime());
                return new ScheduleRunDate(this,date);
            }
            date.add(Calendar.DATE,1);
        }
    }

    public String toString() {
            String res = this.shortDescription;
            String sep = " ";
            for(Activity a : this.activities)
            {
                for(LifeForm l : a.getLifeForms()) {
                    res += sep + l.getName();
                    sep = ", ";
                }
            }
            return res;
        }

}


