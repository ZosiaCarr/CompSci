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

    // static variables
    // for the singleton pattern
    private static final String FILENAME = "LifeForms.data";
    private static LifeFormManager lifeFormManagerInstance = null;
    // needed when saving file
    private static Context _context;
    private static void setContext(Context c) {_context = c;}

    public static LifeFormManager getManager()
    {
        return lifeFormManagerInstance;
    }

    //Loads data from storage, or crates new instance if not found
    public static LifeFormManager load(Context context)
    {
        try {
            //get the file from local storage of device
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();

            //if file is found, and object read, load to our object
            if(readObject instanceof LifeFormManager) {
                lifeFormManagerInstance =  (LifeFormManager) readObject;
            }
        } catch (IOException e) {
            //file didn't read correctly (corrupt, missing) create new instance of the data
            e.printStackTrace();
            lifeFormManagerInstance = new LifeFormManager();
        } catch (ClassNotFoundException e) {
            lifeFormManagerInstance = new LifeFormManager();
        }
        //context is needed for saving the file later
        setContext(context);
        return lifeFormManagerInstance;
    }


    public LifeFormManager()
    {
        lifeForms = new ArrayList<LifeForm>();
        schedules = new ArrayList<Schedule>();
    }

    //instance variables
    private List<LifeForm> lifeForms;
    private List<Schedule> schedules;
    private String additionalInstructions;

    // For notification events
    private transient List<ScheduleEventHandler> listeners;

    //getters and setters
    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.additionalInstructions = additionalInstructions;
        this.persist();
    }

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

    // removing a life form
    // needs remove it from schedule and activities
    // and remove empty schedules as needed
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
            for(ScheduleEventHandler h :listeners)
            {
                h.ScheduleNextEvent(s.getNextRunDate());
                break;
            }
        }
    }

    public void removeSchedule(Schedule s)
    {
        this.schedules.remove(s);
        for(ScheduleEventHandler h : listeners)
        {
            h.ScheduleRemoved(s.getUniqueId());
        }
    }

    //notification event stuff
    public void addListener(ScheduleEventHandler toAdd) {
        if(listeners == null)
        {
            listeners = new ArrayList<ScheduleEventHandler>();
        }
        listeners.add(toAdd);
    }

    public void ScheduleNotificationAgain(int id)
    {
        Schedule thisOne = null;
        for(Schedule s : schedules)
        {
            if(s.getUniqueId() == id)
            {
                thisOne = s;
                break;
            }
        }
        if(thisOne != null)
        {
            for(ScheduleEventHandler h :listeners)
            {
                h.ScheduleNextEvent(thisOne.getNextRunDate());
                break;
            }
        }
    }

    //schedule run date routines
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
    public List<ScheduleRunDate> getNextScheduleRunDate()
    {
        List<ScheduleRunDate> ret = new ArrayList<ScheduleRunDate>();
        for(Schedule schedule : schedules)
        {
            ScheduleRunDate date = schedule.getNextRunDate();
            ret.add(date);
        }
        return ret;
    }

    //saving changes to storage
    public void persist()
    {
        this.save(_context);
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
}

