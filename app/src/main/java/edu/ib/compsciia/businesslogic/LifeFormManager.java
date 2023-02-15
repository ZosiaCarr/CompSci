package edu.ib.compsciia.businesslogic;

import android.content.Context;

import androidx.recyclerview.widget.SortedList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;


public class LifeFormManager  implements java.io.Serializable {
    private static LifeFormManager lifeFormManagerInstance = null;
    public static LifeFormManager getManager()
    {
        return lifeFormManagerInstance;
    }
    public LifeFormManager()
    {
        lifeForms = new ArrayList<LifeForm>();
        schedules = new ArrayList<Schedule>();
    }
    private static final String FILENAME = "LifeForms.data";
    private List<LifeForm> lifeForms;
    private List<Schedule> schedules;
    private static Context _context;
    private static void setContext(Context c) {_context = c;}
    public List<LifeForm> getLifeForms () {
        return lifeForms;
    }
    public List<Schedule> getSchedules () {
        return schedules;
    }


    public void addLifeForm(LifeForm lf)
    {
        this.lifeForms.add(lf);
        this.persist();
    }
    public void persist()
    {
        this.save(_context);
    }
    public void removeLifeForm(LifeForm lf)
    {
        this.lifeForms.remove(lf);
        Stack<Schedule> removeSchedule = new Stack<Schedule>();
        for(Schedule s : schedules)
        {
            Stack<Activity> removeActivity = new Stack<Activity>();
            for(Activity a: s.getActivities())
            {
                a.removeLifeForm(lf);
                if(a.getLifeForms().size() ==0)
                {
                    removeActivity.push(a);
                }
            }
            while(!removeActivity.isEmpty())
            {
                Activity a= removeActivity.pop();
                s.removeActivity(a);
            }
            if(s.getActivities().size() == 0) {
                removeSchedule.push(s);
            }
        }
        while(!removeSchedule.isEmpty())
        {
            Schedule s = removeSchedule.pop();
            this.removeSchedule(s);
        }
    }
    public void addSchedule(Schedule s)
    {
        if(!this.schedules.contains(s))
        {
            this.schedules.add(s);
        }

    }
    public List<ScheduleRunDate> getScheduleRunDates(Calendar start, Calendar end)
    {
        List<ScheduleRunDate> ret = new ArrayList<ScheduleRunDate>();
        for(Schedule schedule : schedules)
        {
            List<Calendar> dates = schedule.getRunsBetweenDates(start,end);
            for(Calendar d : dates)
            {
                ret.add(new ScheduleRunDate(schedule,d));
            }
        }
        Collections.sort(ret);
        return ret;
    }
    public void removeSchedule(Schedule s)
    {
        this.schedules.remove(s);
    }
    public void save(Context context)
    {
        try {
            try {
                FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(lifeFormManagerInstance);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static LifeFormManager load(Context context)
    {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();

            if(readObject != null && readObject instanceof LifeFormManager) {
                lifeFormManagerInstance =  (LifeFormManager) readObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
            lifeFormManagerInstance = new LifeFormManager();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lifeFormManagerInstance = new LifeFormManager();
        }
        lifeFormManagerInstance.setContext(context);
        return lifeFormManagerInstance;
    }
}

