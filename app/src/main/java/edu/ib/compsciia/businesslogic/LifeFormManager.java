package edu.ib.compsciia.businesslogic;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class LifeFormManager  implements java.io.Serializable {
    private static LifeFormManager lifeFormManagerInstance = null;
    private static LifeFormManager getManager()
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

    public List<LifeForm> getLifeForm () {
        return lifeForms;
    }
    public List<Schedule> getSchedules () {
        return schedules;
    }

    public void addLifeForm(LifeForm lf)
    {
        this.lifeForms.add(lf);
    }
    public void removeLifeForm(LifeForm lf)
    {
        this.lifeForms.remove(lf);
    }
    public void addSchedule(Schedule s)
    {
        this.schedules.add(s);
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
                oos.writeObject(this);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        lifeFormManagerInstance = new LifeFormManager();
        return lifeFormManagerInstance;
    }
}

