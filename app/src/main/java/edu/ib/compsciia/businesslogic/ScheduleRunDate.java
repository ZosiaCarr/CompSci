package edu.ib.compsciia.businesslogic;

import java.util.Calendar;

public class ScheduleRunDate  implements Comparable<ScheduleRunDate>{
    public ScheduleRunDate(Schedule s, Calendar c)
    {
        schedule = s;
        date = c;
    }
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    private Schedule schedule;
    private Calendar date;

    @Override
    public int compareTo(ScheduleRunDate u) {
        return date.compareTo(u.date);
    }

}
